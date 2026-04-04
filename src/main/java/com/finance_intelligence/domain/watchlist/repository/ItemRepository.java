package com.finance_intelligence.domain.watchlist.repository;

import com.finance_intelligence.domain.watchlist.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findByWatchlistIdOrderByDisplayOrderAsc(UUID watchlistId);

    List<Item> findByWatchlistId(UUID watchlistId);

    boolean existsByWatchlistIdAndAssetId(UUID watchlistId, UUID assetId);
}
