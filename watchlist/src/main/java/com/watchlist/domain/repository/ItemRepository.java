package com.watchlist.domain.repository;

import com.watchlist.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository {
//    List<Item> findByWatchlistIdOrderByDisplayOrderAsc(UUID watchlistId);

    List<Item> findByWatchlistId(UUID watchlistId);

    boolean existsByWatchlistIdAndAssetId(UUID watchlistId, UUID assetId);

    Item save(Item item);

    void deleteItem(UUID itemId);
}
