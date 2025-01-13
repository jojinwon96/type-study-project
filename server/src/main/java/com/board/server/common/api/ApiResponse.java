package com.board.server.common.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@ToString
public class ApiResponse<T> {

    private final String timestamp = LocalDateTime.now(ZoneId.of("Asia/Seoul")).toString();
    private final UUID txId = UUID.randomUUID();

    private String code;
    private String message;
    private T data;

    public ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        ApiResponse<T> result = new ApiResponse<>(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getMessage(), data);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static <T> ResponseEntity<ApiResponse<T>> memberNotFound() {
        ApiResponse<T> result = new ApiResponse<>(ResponseStatus.NOT_FOUND.getCode(), ResponseStatus.NOT_FOUND.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
}
