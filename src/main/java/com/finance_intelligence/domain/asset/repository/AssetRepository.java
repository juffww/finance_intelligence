package com.finance_intelligence.domain.asset.repository;

import com.finance_intelligence.domain.asset.entity.Asset;
import com.finance_intelligence.domain.asset.entity.AssetType;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

public interface AssetRepository extends JpaRepository<Asset, UUID> {
    Optional<Asset> findSymbolAndAssetType(String symbol, AssetType assetType);

    boolean existsBySymbolAndAssetType(String symbol, AssetType assetType);

    @Query("""
        SELECT a FROM Asset a
        WHERE a.isActive = true
          AND (a.symbol ILIKE :keyword OR a.name ILIKE :keyword)
        ORDER BY a.symbol ASC
        """)
    Page<Asset> searchByWord(@Param("keyword") String keyword, Pageable pageable);
    // Page<Asset> = kết quả phân trang: có danh sách + tổng số dòng + số trang
    // Pageable    = thông tin phân trang truyền vào: số trang, kích thước trang, sort
}
