package com.identity.identity_service.dto.request;

import java.time.LocalDate;

public record UserCreationRequest(
        String username,
        String password,
        String firstName,
        String lastName,
        LocalDate dob
){}
