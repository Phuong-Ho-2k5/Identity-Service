package com.identity.identity_service.dto.response;

import java.time.LocalDate;

public record UserResponse(
        String id,
        String username,
        String firstName,
        String lastName,
        LocalDate dob
) {}
