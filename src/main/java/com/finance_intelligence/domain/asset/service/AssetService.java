package com.finance_intelligence.domain.asset.service;

import com.finance_intelligence.common.exception.AppException;
import com.finance_intelligence.common.exception.ErrorCode;
import com.finance_intelligence.domain.asset.dto.response.AssetResponse;
import com.finance_intelligence.domain.asset.entity.Asset;
import com.finance_intelligence.domain.asset.mapper.AssetMapper;
import com.finance_intelligence.domain.asset.repository.AssetRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    @Transactional(readOnly = true)
    public AssetResponse getById(UUID id)
    {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ASSET_NOT_EXITSTED));

        return assetMapper.toAssetResponse(asset);
    }

    @Transactional(readOnly = true)
    // Tìm kiếm tài sản theo từ khóa
    public List<AssetResponse> search(String keyword) 
    {
        String pattern = "%" + keyword + "%";
        return assetRepository.searchByWord(pattern)
                .stream().map(assetMapper::toAssetResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public String deactiveAsset(UUID id)
    {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ASSET_NOT_EXITSTED));
        asset.setActive(false);

        return "Deactive asset successfully";
    }
}
