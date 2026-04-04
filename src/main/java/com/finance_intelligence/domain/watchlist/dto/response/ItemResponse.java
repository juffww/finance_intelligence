package com.finance_intelligence.domain.watchlist.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class ItemResponse {
    private UUID id;
    private UUID assetId;
    private short displayOrder;
    private String userNotes;
}
