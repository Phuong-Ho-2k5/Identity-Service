package com.identity.identity_service.dto.response;

public record ApiResponse <T>(
        int code,
        String message,
        T result
){
    public ApiResponse(T result){
        this(1000, "Success", result);
    }
}
