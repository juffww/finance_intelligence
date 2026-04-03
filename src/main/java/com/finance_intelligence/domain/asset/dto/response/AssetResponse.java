package com.finance_intelligence.domain.asset.dto.response;

import com.finance_intelligence.domain.asset.entity.AssetType;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
public class AssetResponse {
    private final UUID id;
    private final String symbol;
    private final String name;
    private final AssetType assetType;
    private final boolean isActive;
    private OffsetDateTime createdAt;
}
