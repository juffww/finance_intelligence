package com.asset.infrastructure.repository;

import com.asset.domain.entity.enums.AssetType;
import com.asset.infrastructure.entity.AssetJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AssetJpaRepository extends JpaRepository<AssetJpaEntity, UUID> {

    boolean existsBySymbolAndAssetType(String symbol, AssetType assetType);

    @Query("""
        SELECT a FROM AssetJpaEntity a
        WHERE a.active = true
          AND (a.symbol ILIKE %:keyword% OR a.name ILIKE %:keyword%)
        ORDER BY a.symbol ASC
        """)
    List<AssetJpaEntity> searchByWord(@Param("keyword") String keyword);
}