package com.board.server.board;

import com.board.server.board.dto.request.PatchDisableBoardStatusRequestDTO;
import com.board.server.board.dto.request.PostBoardPaginationRequestDTO;
import com.board.server.board.dto.request.PatchBoardRequestDTO;
import com.board.server.board.dto.request.PostBoardRequestDTO;
import com.board.server.board.dto.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    /**
     * 게시글 전체 조회
     */
    @GetMapping("/list")
    public ResponseEntity<GetBoardListResponseDTO> getBoardList() {
        ResponseEntity<GetBoardListResponseDTO> result = boardService.getBoardList();
        return result;
    }

    /**
     * 게시글 등록
     */
    @PostMapping("/write")
    public ResponseEntity<PostBoardResponseDto> postBoard(@RequestBody PostBoardRequestDTO boardRequestDto) {
        ResponseEntity<PostBoardResponseDto> result = boardService.postBoard(boardRequestDto);
        return result;
    }

    /**
     * 게시글 수정
     */
    @PatchMapping("/edit/{id}")
    public ResponseEntity<PatchBoardResponseDTO> updateBoard(@PathVariable Long id, @RequestBody PatchBoardRequestDTO board) {
        ResponseEntity<PatchBoardResponseDTO> result = boardService.updateBoard(id, board);
        return result;
    }

    /**
     * 게시글 상세 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetBoardResponseDTO> getBoard(@PathVariable Long id) {
        ResponseEntity<GetBoardResponseDTO> result = boardService.getBoard(id);
        return result;
    }

    /**
     * 게시글 비활성화
     */
    @PatchMapping("/list")
    public ResponseEntity<PatchDisableBoardStatusResponseDTO> updateBoardStatusToDelete(@RequestBody PatchDisableBoardStatusRequestDTO request) {
        ResponseEntity<PatchDisableBoardStatusResponseDTO> result = boardService.updateBoardStatusToDelete(request);
        return result;
    }

    /**
     * 게시글 페이징 조회
     */
    @PostMapping("/page")
    public ResponseEntity<PostBoardPaginationResponseDTO> getBoardPagination(@RequestBody PostBoardPaginationRequestDTO request) {
        ResponseEntity<PostBoardPaginationResponseDTO> result = boardService.getBoardPagination(request);
        return result;
    }
}
