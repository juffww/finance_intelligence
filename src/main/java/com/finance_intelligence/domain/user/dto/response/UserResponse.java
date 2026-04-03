package com.finance_intelligence.domain.user.dto.response;

import com.finance_intelligence.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserResponse {

    private final UUID id;
    private final String email;
    private final String fullName;
    private final String subscriptionTier;
    private final boolean active;
    private final OffsetDateTime createdAt;
}
