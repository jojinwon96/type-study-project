package com.board.server.board.dto.response;

import com.board.server.board.common.ResponseCode;
import com.board.server.board.common.ResponseMessage;
import com.board.server.board.dto.object.BoardListItem;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class PostBoardPaginationResponseDTO extends ResponseDTO {
    private int total;
    private List<BoardListItem> boardListItemList;

    private PostBoardPaginationResponseDTO(int total, List<BoardListItem> boardListItemList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.total = total;
        this.boardListItemList = boardListItemList;
    }

    public static ResponseEntity<PostBoardPaginationResponseDTO> success(int total, List<BoardListItem> boardListItemList) {
        PostBoardPaginationResponseDTO result = new PostBoardPaginationResponseDTO(total, boardListItemList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
