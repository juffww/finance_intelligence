package com.user.infrastructure.repository;

import com.user.domain.entity.User;
import com.user.domain.entity.UserSettings;
import com.user.domain.repository.UserRepository;
import com.user.infrastructure.entity.UserJpaEntity;
import com.user.infrastructure.entity.UserSettingsJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
//Đây là lớp thực thi contract (Các công việc cần thiết của domain)
//jpaRepository là lớp giao tiếp với Spring Data JPA, có sẵn các hàm khỏi viết lại
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomainEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(this::toDomainEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public User save(User user) {
        UserJpaEntity savedEntity = jpaRepository.save(toJpaEntity(user));
        return toDomainEntity(savedEntity);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomainEntity).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private User toDomainEntity(UserJpaEntity entity) {
        if (entity == null) return null;

        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .fullName(entity.getFullName())
                .password(entity.getPassword())
                .subscriptionTier(entity.getSubscriptionTier())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                // QUAN TRỌNG: Phải map settings từ Entity sang Domain ở đây
                .settings(mapSettingsToDomain(entity.getSettings()))
                .build();
    }

    private UserJpaEntity toJpaEntity(User domain) {
        // 1. Build UserEntity trước (KHÔNG set settings ở đây)
        UserJpaEntity userEntity = UserJpaEntity.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .fullName(domain.getFullName())
                .subscriptionTier(domain.getSubscriptionTier())
                .password(domain.getPassword())
                .isActive(domain.getIsActive())
                .build();

        // 2. Thiết lập quan hệ 2 chiều (Bidirectional) cho Settings
        if (domain.getSettings() != null) {
            UserSettingsJpaEntity settingsEntity = UserSettingsJpaEntity.builder()
                    .theme(domain.getSettings().getTheme())
                    .language(domain.getSettings().getLanguage())
                    .defaultCurrency(domain.getSettings().getDefaultCurrency())
                    .notificationQuietFrom(domain.getSettings().getNotificationQuietFrom())
                    .notificationQuietTo(domain.getSettings().getNotificationQuietTo())
                    .user(userEntity) // Gán cha cho con
                    .build();

            userEntity.setSettings(settingsEntity); // Gán con ngược lại cho cha
        }

        return userEntity;
    }

    private UserSettings mapSettingsToDomain(UserSettingsJpaEntity settingsEntity) {
        if (settingsEntity == null) return null;

        return UserSettings.builder()
                .userId(settingsEntity.getUser().getId())
                .theme(settingsEntity.getTheme())
                .language(settingsEntity.getLanguage())
                .defaultCurrency(settingsEntity.getDefaultCurrency())
                .notificationQuietFrom(settingsEntity.getNotificationQuietFrom())
                .notificationQuietTo(settingsEntity.getNotificationQuietTo())
                .build();
    }
}
