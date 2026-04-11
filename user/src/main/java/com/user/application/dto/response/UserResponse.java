package com.user.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.user.domain.entity.UserSettings;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private final UUID id;
    private final String email;
    private final String fullName;
    private final String subscriptionTier;
    private final boolean isActive;
    private final OffsetDateTime createdAt;

    private final UserSettings settings;
}
