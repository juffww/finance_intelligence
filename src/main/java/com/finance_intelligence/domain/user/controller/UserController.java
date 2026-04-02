package com.finance_intelligence.domain.user.controller;

import com.finance_intelligence.common.response.ApiResponse;

import com.finance_intelligence.domain.dto.request.UserCreationRequest;
import com.finance_intelligence.domain.dto.request.UserUpdateRequest;
import com.finance_intelligence.domain.dto.response.UserResponse;
import com.finance_intelligence.domain.mapper.UserMapper;
import com.finance_intelligence.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) throws Exception {
        return ApiResponse.<UserResponse>builder()
                .message("Create successfully a user in database")
                .data(userService.createUser(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable UUID id, @RequestBody UserUpdateRequest request)
    {
        String message = request.isActive() ? "Active user successfully" : "Deactive user successfully";
        return ApiResponse.<UserResponse>builder()
                .message(message)
                .data(userService.updateUser(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable UUID id)
    {
        return ApiResponse.<String>builder()
                .message(userService.deleteUser(id))
                .build();
    }
}
