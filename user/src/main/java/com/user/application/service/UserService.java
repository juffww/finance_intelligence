package com.user.application.service;

import com.finance_common.event.UserCreatedIntegrationEvent;
import com.finance_common.exception.AppException;
import com.finance_common.exception.ErrorCode;
import com.finance_core.enums.Theme;
import com.user.application.dto.request.UserCreationRequest;
import com.user.application.dto.request.UserUpdateRequest;
import com.user.application.dto.response.UserResponse;

import com.user.application.mapper.UserMapper;
import com.user.domain.entity.User;
import com.user.domain.entity.UserSettings;
import com.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public UserResponse getUser(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    public UserResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        user.setSettings(UserSettings.builder().build());

        // Gắn settings vào user. Khi userRepository.save(user),
        // dữ liệu sẽ được đẩy vào CẢ HAI bảng 'users' và 'user_settings'.
        User savedUser = userRepository.save(user);

        // Bắn Integration Event để thông báo cho các module khác
        UserCreatedIntegrationEvent event = new UserCreatedIntegrationEvent(
                UUID.randomUUID(),
                Instant.now(),
                savedUser.getId(),
                savedUser.getFullName() // Giả sử entity User có field username, nếu khác, hãy báo cho tôi biết
        );
        eventPublisher.publishEvent(event);

        // TRẢ VỀ savedUser, KHÔNG TRẢ VỀ user
        return userMapper.toUserResponse(savedUser);
    }

    public UserResponse updateUser(UUID id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );

        // Dùng hàm nghiệp vụ của Domain thay vì Setters
        if (request.isActive()) {
            user.activate();
        } else {
            user.deactivate();
        }

        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public String deleteUser(UUID id) {
        if (!userRepository.existsById(id)) { // Đổi sang existsById để tối ưu DB
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        userRepository.deleteById(id);
        return "Delete user successfully";
    }

    public void updateTheme(UUID userId, Theme newTheme) {
        // 1. Lấy User cùng với Settings của họ lên
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // 2. Gọi hàm nghiệp vụ ngay trên Domain Object
        // Giả sử class User của bạn có hàm getSettings()
        user.getSettings().changeTheme(newTheme);

        // 3. Lưu lại User. JPA sẽ tự hiểu là bảng user_settings cần update cột 'theme'
        userRepository.save(user);
    }
}