package com.user.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.finance_core.enums.Language;
import com.finance_core.enums.Theme;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSettingsResponse {
    private Theme theme;
    private Language language;
    private String defaultCurrency;
}
