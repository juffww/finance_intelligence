package com.watchlist.infrastructure.repository;

import com.watchlist.infrastructure.entity.ItemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemJpaRepository extends JpaRepository<ItemJpaEntity, UUID> {
    boolean existsByWatchlistIdAndAssetId(UUID watchlistId, UUID assetId);
    List<ItemJpaEntity> findByWatchlistId(UUID watchlistId);
}
