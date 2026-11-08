package com.identity.identity_service.exception;

import com.identity.identity_service.dto.request.UserCreationRequest;
import jakarta.validation.Valid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GlobalExceptionHandlerTests {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new TestController())
                .setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    void missingUsernameReturnsUsernameInvalid() throws Exception {
        mockMvc.perform(post("/validation").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\":\"password123\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(1003));
    }

    @Test
    void missingPasswordReturnsPasswordInvalid() throws Exception {
        mockMvc.perform(post("/validation").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"alice\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(1004));
    }

    @Test
    void checkedExceptionReturnsInternalServerError() throws Exception {
        mockMvc.perform(post("/failure"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value(9999));
    }

    @Test
    void unknownValidationMessageReturnsFallback() {
        assertValidationFallback("unknown validation message");
    }

    @Test
    void nullValidationMessageReturnsFallback() {
        assertValidationFallback(null);
    }

    private void assertValidationFallback(String message) {
        var errors = new BeanPropertyBindingResult(new Object(), "request");
        errors.addError(new FieldError("request", "username", message));
        var exception = new MethodArgumentNotValidException(null, errors);
        var response = new GlobalExceptionHandler()
                .HandlingMethodArgumentNotValidException(exception);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(9999, response.getBody().code());
    }

    @Test
    void missingFieldErrorReturnsFallback() {
        var errors = new BeanPropertyBindingResult(new Object(), "request");
        var exception = new MethodArgumentNotValidException(null, errors);
        var response = new GlobalExceptionHandler()
                .HandlingMethodArgumentNotValidException(exception);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(9999, response.getBody().code());
    }

    @RestController
    static class TestController {
        @PostMapping("/validation")
        String validate(@Valid @RequestBody UserCreationRequest request) {
            return "ok";
        }

        @PostMapping("/failure")
        String fail() throws Exception {
            throw new Exception("internal details");
        }
    }
}
