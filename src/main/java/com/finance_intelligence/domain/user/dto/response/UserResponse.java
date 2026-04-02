package com.finance_intelligence.domain.user.dto.response;

import com.finance_intelligence.domain.user.entity.User;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
public class UserResponse {

    private final UUID id;
    private final String email;
    private final String fullName;
    private final String subscriptionTier;
    private final boolean active;
    private final OffsetDateTime createdAt;

    // Constructor nhận thẳng Entity để map — không dùng Lombok @Builder
    // vì đây là output-only, không cần tạo từ bên ngoài
    public UserResponse(User user) {
        this.id               = user.getId();
        this.email            = user.getEmail();
        this.fullName         = user.getFullName();
        this.subscriptionTier = user.getSubscriptionTier().name();
        this.active           = user.getIsActive();
        this.createdAt        = user.getCreatedAt();
        // passwordHash KHÔNG có ở đây — không bao giờ trả về frontend
    }
}
