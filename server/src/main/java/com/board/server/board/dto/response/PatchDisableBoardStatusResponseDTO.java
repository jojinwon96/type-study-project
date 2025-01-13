package com.board.server.board.dto.response;

import com.board.server.board.common.ResponseCode;
import com.board.server.board.common.ResponseMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class PatchDisableBoardStatusResponseDTO extends ResponseDTO{

    private int resultRow;

    private PatchDisableBoardStatusResponseDTO(int resultRow){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.resultRow = resultRow;
    }

    public static ResponseEntity<PatchDisableBoardStatusResponseDTO> success(int resultRow){
        PatchDisableBoardStatusResponseDTO result = new PatchDisableBoardStatusResponseDTO(resultRow);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
