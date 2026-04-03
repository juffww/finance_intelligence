package com.finance_intelligence.domain.asset.repository;

import com.finance_intelligence.domain.asset.entity.Asset;
import com.finance_intelligence.domain.asset.entity.enums.AssetType;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AssetRepository extends JpaRepository<Asset, UUID> {
//    Optional<Asset> findSymbolAndAssetType(String symbol, AssetType assetType);

    boolean existsBySymbolAndAssetType(String symbol, AssetType assetType);

    @Query("""
        SELECT a FROM Asset a
        WHERE a.active = true
          AND (a.symbol ILIKE :keyword OR a.name ILIKE :keyword)
        ORDER BY a.symbol ASC
        """)
    List<Asset> searchByWord(@Param("keyword") String keyword);
    // Page<Asset> = kết quả phân trang: có danh sách + tổng số dòng + số trang
    // Pageable    = thông tin phân trang truyền vào: số trang, kích thước trang, sort
}
