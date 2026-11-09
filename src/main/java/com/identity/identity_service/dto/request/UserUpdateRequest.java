package com.identity.identity_service.dto.request;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserUpdateRequest(
        @Size(min = 8, max = 20, message = "PASSWORD_INVALID") String password,
        String firstName,
        String lastName,
        LocalDate dob
){}
