package com.board.server.board.dto.response;

import com.board.server.board.common.ResponseCode;
import com.board.server.board.common.ResponseMessage;
import com.board.server.board.dto.object.BoardListItem;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetBoardResponseDTO extends ResponseDTO {

    private BoardListItem board;

    private GetBoardResponseDTO(BoardListItem board) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.board = board;
    }

    public static ResponseEntity<GetBoardResponseDTO> success(BoardListItem board) {
        GetBoardResponseDTO result = new GetBoardResponseDTO(board);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
