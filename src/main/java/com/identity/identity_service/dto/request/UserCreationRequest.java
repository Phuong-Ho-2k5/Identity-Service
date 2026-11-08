package com.identity.identity_service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserCreationRequest(
        @NotNull(message = "USERNAME_INVALID") @Size(min = 3, max = 50, message = "USERNAME_INVALID") String username,
        @NotNull(message = "PASSWORD_INVALID") @Size(min = 8, max = 20, message = "PASSWORD_INVALID") String password,
        String firstName,
        String lastName,
        LocalDate dob
){}
