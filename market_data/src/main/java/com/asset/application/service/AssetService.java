package com.asset.application.service;

import com.asset.application.dto.response.AssetResponse;
import com.asset.application.mapper.AssetMapper;
import com.asset.domain.entity.Asset;
import com.asset.domain.repository.AssetRepository;
import com.finance_common.exception.AppException;
import com.finance_common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    public List<AssetResponse> getAll()
    {
        return assetRepository.findAll()
                .stream().map(assetMapper::toAssetResponse)
                .toList();
    }

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
