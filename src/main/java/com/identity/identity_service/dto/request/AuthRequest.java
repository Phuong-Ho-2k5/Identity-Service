package com.identity.identity_service.dto.request;

public record AuthRequest(
        String username,
        String password
){}
