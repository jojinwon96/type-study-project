package com.board.server.board.dto.response;

import com.board.server.board.common.ResponseCode;
import com.board.server.board.common.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDTO {

    private String code;
    private String message;

    public ResponseDTO(){
        this.code = ResponseCode.SUCCESS;
        this.message = ResponseMessage.SUCCESS;
    }

    // db error
}
