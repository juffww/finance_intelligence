package com.asset.domain.entity;

import com.asset.domain.entity.enums.AssetType;
import com.asset.domain.entity.enums.PriceCurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
    private UUID id;
    private String symbol;
    private String name;
    private AssetType assetType;
    private PriceCurrency priceCurrency;
    private boolean active = true;

    public Asset(UUID id, String symbol, String name, AssetType assetType, boolean active) {
    }

    public void changePriceCurrency (PriceCurrency newPriceCurrency)
    {
        this.priceCurrency = newPriceCurrency;
    }
}
