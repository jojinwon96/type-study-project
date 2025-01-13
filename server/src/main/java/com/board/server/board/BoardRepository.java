package com.board.server.board;

import com.board.server.board.dto.object.BoardListItem;
import com.board.server.board.dto.request.PatchBoardRequestDTO;
import com.board.server.board.dto.request.PostBoardRequestDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 게시글 전체 조회
     */
    public List<BoardListItem> findAll() {
        String sql = "SELECT id, title, content, create_date FROM tbl_board ORDER BY create_date DESC";

        RowMapper<BoardListItem> rowMapper = (rs, rowNum) -> new BoardListItem(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("create_date")
        );

        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * 단일 게시글 삽입
     */
    public int save(PostBoardRequestDTO board) {
        String sql = "INSERT INTO tbl_board (title, content, create_date) VALUES (?, ?, ?)";

        LocalDateTime currentTime = LocalDateTime.now();

        return jdbcTemplate.update(sql, board.getTitle(), board.getContent(), currentTime);
    }

    /**
     * 단일 게시글 수정
     */
    public int update(Long id, PatchBoardRequestDTO board) {
        String sql = "UPDATE tbl_board SET title = ?, content = ? WHERE id = ?";

        LocalDateTime currentTime = LocalDateTime.now();

        return jdbcTemplate.update(sql, board.getTitle(), board.getContent(), id);
    }

    /**
     * 단일 게시글 조회
     */
    public BoardListItem findById(Long id) {
        String sql = "SELECT id, title, content, create_date FROM tbl_board WHERE id = ?";

        RowMapper<BoardListItem> rowMapper = (rs, rowNum) -> new BoardListItem(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("create_date")
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    /**
     * 게시글 비활성화
     */
    public int updateStatusToDeleted(List<Long> ids) {
        String sql = "UPDATE tbl_board SET status = -1 WHERE id IN (?)";

        String idList = String.join(",", ids.stream().map(String::valueOf).toArray(String[]::new));

        return jdbcTemplate.update(sql, idList);
    }

    /**
     * 게시글 페이징 처리
     */
    public List<BoardListItem> findAllByPage(int page, int size) {
        int offset = (page - 1) * size;
        String sql = "SELECT id, title, content, DATE_FORMAT(create_date, '%Y/%m/%d') AS create_date FROM tbl_board WHERE status = 1 LIMIT ? OFFSET ?";
        RowMapper<BoardListItem> rowMapper = (rs, rowNum) -> new BoardListItem(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("create_date")
        );
        return jdbcTemplate.query(sql, rowMapper, size, offset);
    }

    /**
     * 게시글 전체 갯수
     */
    public int boardCount() {
        String sql = "SELECT count(*) FROM tbl_board";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}
