package com.identity.identity_service.exception;

import com.identity.identity_service.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<Void>> HandlingUncategorizedException(Exception exception){
        ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
        ApiResponse<Void> apiResponse = new ApiResponse<>(errorCode.getCode(), errorCode.getMessage(), null);
        return ResponseEntity.internalServerError().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<Void>> HandlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse<Void> apiResponse = new ApiResponse<>(errorCode.getCode(), errorCode.getMessage(), null);
        return ResponseEntity.badRequest().body(apiResponse);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Void>> HandlingMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
        var fieldError = exception.getFieldError();
        if (fieldError != null && fieldError.getDefaultMessage() != null) {
            try {
                errorCode = ErrorCode.valueOf(fieldError.getDefaultMessage());
            } catch (IllegalArgumentException ignored) {
                // Keep the fallback when the validation message is not an ErrorCode.
            }
        }
        ApiResponse<Void> apiResponse = new ApiResponse<>(errorCode.getCode(), errorCode.getMessage(), null);
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
