package com.user.domain.entity;

import com.finance_core.enums.Language;
import com.finance_core.enums.Theme;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class UserSettings {

    private UUID userId;

    @Builder.Default
    private Theme theme = Theme.system;

    @Builder.Default
    private Language language = Language.vi;

    @Builder.Default
    private String defaultCurrency = "VND";

    private Short notificationQuietFrom;

    private Short notificationQuietTo;

    public void changeTheme(Theme newTheme) {
        this.theme = newTheme;
    }

    public void changeLanguage(Language newLanguage) {
        this.language = newLanguage;
    }

    public void setQuietHours(Short from, Short to) {
        this.notificationQuietFrom = from;
        this.notificationQuietTo = to;
    }
}