package com.asset.application.mapper;

import com.asset.application.dto.response.AssetResponse;
import com.asset.domain.entity.Asset;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssetMapper {
    public AssetResponse toAssetResponse(Asset asset);
}
