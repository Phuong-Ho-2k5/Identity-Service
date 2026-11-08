package com.identity.identity_service.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    USERNAME_EXISTED(1001, "This username has been used. Please choose a different username."),
    USERNAME_INVALID(1003, "Username must be between 3 and 50 characters"),
    PASSWORD_INVALID(1004, "Password must be between 8 and 20 characters")
    ;
    private final int code;
    private final String message;

    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
