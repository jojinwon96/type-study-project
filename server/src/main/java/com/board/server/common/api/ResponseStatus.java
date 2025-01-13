package com.board.server.common.api;

import lombok.Getter;

@Getter
public enum ResponseStatus {

    SUCCESS("SU", "response success"),
    ERROR("ER", "response error"),
    NOT_FOUND("NF", "Member not found"),
    BAD_REQUEST("BR", "Bad Request");

    private final String code;
    private final String message;

    ResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
