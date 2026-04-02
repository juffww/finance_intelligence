package com.finance_intelligence.domain.user.service;

import com.finance_intelligence.common.exception.AppException;
import com.finance_intelligence.common.exception.ErrorCode;

import com.finance_intelligence.domain.dto.request.UserCreationRequest;
import com.finance_intelligence.domain.dto.request.UserUpdateRequest;
import com.finance_intelligence.domain.dto.response.UserResponse;
import com.finance_intelligence.domain.mapper.UserMapper;
import com.finance_intelligence.domain.user.entity.User;
import com.finance_intelligence.domain.user.entity.UserSettings;
import com.finance_intelligence.domain.user.repository.UserRepository;
import com.finance_intelligence.domain.user.repository.UserSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserSettingsRepository userSettingsRepository;

    public List<UserResponse> getAllUsers()
    {
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.toUserResponse(user))
                .toList();
    }

    public UserResponse getUser(UUID id)
    {
        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    public UserResponse createUser(UserCreationRequest request)
    {
        if(userRepository.existsByEmail(request.getEmail()))
        {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        User user = userMapper.toUser(request);
        User savedUser = userRepository.save(user);

        UserSettings userSettings = UserSettings.builder()
                .user(savedUser)
                .build();

        userSettingsRepository.save(userSettings);
        return userMapper.toUserResponse(savedUser);
    }

    public UserResponse updateUser(UUID id, UserUpdateRequest request)
    {
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );
        user.setIsActive(request.isActive());
        User updateUser = userRepository.save(user);
        return userMapper.toUserResponse(updateUser);
    }

    public String deleteUser(UUID id)
    {
        if (getUser(id) == null)
        {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        userRepository.deleteById(id);
        return  "Delete user successully";
    }
}
