package com.board.server.exception;

import com.board.server.common.api.ApiResponse;
import com.board.server.common.api.ResponseStatus;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class MemberRestExceptionHandler {

    // 공통된 ApiResponse 생성 로직을 메서드로 추출
    private <T> ResponseEntity<ApiResponse<T>> createBadRequestResponse(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(ResponseStatus.BAD_REQUEST.getCode(), message, data);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

//    // ConstraintViolationException 처리 (Bean Validation)
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ApiResponse<String>> handleConstraintViolation(ConstraintViolationException ex) {
//        return createBadRequestResponse("Validation failed: " + ex.getMessage(), null);
//    }

    // MethodArgumentNotValidException 처리 (Spring Validation: @Valid 검사 실패 시)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)  // 오류 메시지 추출
                .collect(Collectors.toList());
        return createBadRequestResponse("하나 이상의 필드에 대한 검증 실패", errorMessages);
    }
}
