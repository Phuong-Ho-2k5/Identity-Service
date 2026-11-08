package com.identity.identity_service.controller;

import com.identity.identity_service.dto.request.UserCreationRequest;
import com.identity.identity_service.dto.request.UserUpdateRequest;
import com.identity.identity_service.dto.response.ApiResponse;
import com.identity.identity_service.entity.User;
import com.identity.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@Valid @RequestBody UserCreationRequest request){
        return new ApiResponse<>(userService.createUser(request));
    }

    @GetMapping
    List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable String userId){
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return "User deleted";
    }
}
