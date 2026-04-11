package com.asset.application.dto.response;

import com.asset.domain.entity.enums.AssetType;
import com.asset.domain.entity.enums.PriceCurrency;
import lombok.*;
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
}
