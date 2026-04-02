package com.finance_intelligence.domain.user.entity;

import com.finance_intelligence.domain.user.entity.enums.Language;
import com.finance_intelligence.domain.user.entity.enums.Theme;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "user_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSettings {

    @Id
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "theme", nullable = false, length = 20)
    @Builder.Default
    private Theme theme = Theme.system;

    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false, length = 10)
    @Builder.Default
    private Language language = Language.vi;

    @Column(name = "default_currency", nullable = false, length = 3)
    @Builder.Default
    private String defaultCurrency = "VND";

    @Column(name = "notification_quiet_from")
    private Short notificationQuietFrom;

    @Column(name = "notification_quiet_to")
    private Short notificationQuietTo;
}
