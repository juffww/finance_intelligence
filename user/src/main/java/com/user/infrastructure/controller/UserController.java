package com.user.infrastructure.controller;

import com.finance_common.response.ApiResponse;
import com.user.application.dto.request.UserCreationRequest;
import com.user.application.dto.request.UserUpdateRequest;
import com.user.application.dto.response.UserResponse;
import com.user.application.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    //Nhận request HTTP, gọi application service
    private final UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable UUID id)
    {
        return ApiResponse.ok(userService.getUser(id));
    }

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.ok(userService.createUser(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable UUID id, @RequestBody UserUpdateRequest request) {
        return ApiResponse.ok(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable UUID id) {
        return ApiResponse.ok(userService.deleteUser(id));
    }
}