package com.finance_intelligence.domain.asset.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finance_intelligence.domain.asset.entity.enums.AssetType;
import com.finance_intelligence.domain.asset.entity.enums.PriceCurrency;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class AssetResponse {
    private final UUID id;
    private final String symbol;
    private final String name;
    private final AssetType assetType;
    private final PriceCurrency priceCurrency;
    private final boolean active;
    private OffsetDateTime createdAt;
}
