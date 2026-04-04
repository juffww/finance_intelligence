package com.finance_intelligence.domain.watchlist.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ItemCreationRequest {
    @NotNull(message = "You must add the asset you want to watch")
    private UUID assetId;
    private String userNotes;
    @Min(value = 1, message = "Display order must be greater than 0")
    private short displayOrder;
}
