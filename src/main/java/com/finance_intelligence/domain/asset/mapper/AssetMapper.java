package com.finance_intelligence.domain.asset.mapper;

import com.finance_intelligence.domain.asset.dto.response.AssetResponse;
import com.finance_intelligence.domain.asset.entity.Asset;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface AssetMapper {
    public AssetResponse toAssetResponse(Asset asset);


}
