package com.asset.domain.repository;

import com.asset.domain.entity.Asset;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssetRepository {
    List<Asset> findAll();
    Optional<Asset> findById(UUID id);
    List<Asset> searchByWord(String keyword);
}
