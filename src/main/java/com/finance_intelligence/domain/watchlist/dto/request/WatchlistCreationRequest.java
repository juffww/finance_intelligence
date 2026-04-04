package com.finance_intelligence.domain.watchlist.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class WatchlistCreationRequest {
    @NotBlank(message = "You must name the watchlist")
    private String name;
    private String description;
    private boolean isDefault;
}