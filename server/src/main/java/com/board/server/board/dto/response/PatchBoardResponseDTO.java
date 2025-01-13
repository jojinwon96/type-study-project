package com.board.server.board.dto.response;

import com.board.server.board.common.ResponseCode;
import com.board.server.board.common.ResponseMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchBoardResponseDTO extends ResponseDTO{

    private PatchBoardResponseDTO(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PatchBoardResponseDTO> success(){
        PatchBoardResponseDTO result = new PatchBoardResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
