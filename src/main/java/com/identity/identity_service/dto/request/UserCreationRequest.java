package com.identity.identity_service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserCreationRequest(
        @NotNull @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String username,
        @NotNull @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters") String password,
        String firstName,
        String lastName,
        LocalDate dob
){}
