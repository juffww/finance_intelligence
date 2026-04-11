package com.user.domain.entity;

import com.finance_core.enums.Subscription_Tier;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Ẩn constructor rỗng
@AllArgsConstructor
@Builder
public class User {

    private UUID id;
    private String email;
    private String fullName;
    private String password;

    @Builder.Default
    private Subscription_Tier subscriptionTier = Subscription_Tier.free;

    @Builder.Default
    private Boolean isActive = true;

    private OffsetDateTime createdAt;

    @Setter
    private UserSettings settings;

    // --- Các hàm xử lý nghiệp vụ (Rich Domain Model) ---

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }

    public void upgradeTier(Subscription_Tier newTier) {
        this.subscriptionTier = newTier;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

}