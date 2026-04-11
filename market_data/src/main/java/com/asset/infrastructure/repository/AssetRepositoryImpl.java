package com.asset.infrastructure.repository;

import com.asset.domain.entity.Asset;
import com.asset.domain.repository.AssetRepository;
import com.asset.infrastructure.entity.AssetJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AssetRepositoryImpl implements AssetRepository {
    private final AssetJpaRepository assetJpaRepository;

    @Override
    public List<Asset> findAll() {
        return assetJpaRepository.findAll()
                .stream()
                .map(this::toDomainEntity)
                .toList();
    }

    @Override
    public Optional<Asset> findById(UUID id)
    {
        return assetJpaRepository.findById(id).map(this::toDomainEntity);
    }

    @Override
    public List<Asset> searchByWord(String keyword)
    {
        return assetJpaRepository.searchByWord(keyword)
                .stream()
                .map(this::toDomainEntity)
                .toList();
    }

    private Asset toDomainEntity(AssetJpaEntity entity)
    {
        return new Asset(
                entity.getId(),
                entity.getSymbol(),
                entity.getName(),
                entity.getAssetType(),
                entity.getPriceCurrency(),
                entity.isActive()
        );
    }
}
