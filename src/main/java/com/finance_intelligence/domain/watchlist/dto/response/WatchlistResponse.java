package com.finance_intelligence.domain.watchlist.dto.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class WatchlistResponse {
    private UUID id;
    private String name;
    private String description;
    private boolean isDefault;
    List<ItemResponse> items;
}
