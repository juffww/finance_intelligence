package com.watchlist.infrastructure.repository;

import com.watchlist.domain.entity.Item;
import com.watchlist.domain.repository.ItemRepository;
import com.watchlist.infrastructure.entity.ItemJpaEntity;
import com.watchlist.infrastructure.entity.WatchlistJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {
    private final ItemJpaRepository itemJpaRepository;

    @Override
    public boolean existsByWatchlistIdAndAssetId(UUID watchlistId, UUID assetId) {
        return itemJpaRepository.existsByWatchlistIdAndAssetId(watchlistId, assetId);
    }

    @Override
    public List<Item> findByWatchlistId(UUID watchlistId) {
        return itemJpaRepository.findByWatchlistId(watchlistId)
                .stream().map(this::toItemDomain)
                .toList();
    }

    @Override
    public Item save(Item item) {
        ItemJpaEntity entity = toItemJpaEntity(item);
        ItemJpaEntity savedEntity = itemJpaRepository.save(entity);

        return toItemDomain(savedEntity);
    }

    @Override
    public void deleteItem(UUID itemId) {
        itemJpaRepository.deleteById(itemId);
    }

    private Item toItemDomain(ItemJpaEntity entity)
    {
        return Item.builder()
                .id(entity.getId())
                .assetId(entity.getAssetId())
                .watchlistId(entity.getWatchlist().getId())
                .displayOrder(entity.getDisplayOrder())
                .userNotes(entity.getUserNotes())
                .build();
    }

//    private ItemJpaEntity toItemJpaEntity(Item item)
//    {
//        return ItemJpaEntity.builder()
//                .id(item.getId())
//                .assetId(item.getAssetId())
//                .displayOrder(item.getDisplayOrder())
//                .userNotes(item.getUserNotes())
//                .build();
//    }
private ItemJpaEntity toItemJpaEntity(Item item) {
    // Tạo object Watchlist proxy để Hibernate map Foreign Key
    WatchlistJpaEntity watchlistRef = new WatchlistJpaEntity();
    watchlistRef.setId(item.getWatchlistId());

    return ItemJpaEntity.builder()
            .id(item.getId())
            .watchlist(watchlistRef) // Truyền proxy vào đây
            .assetId(item.getAssetId())
            .displayOrder(item.getDisplayOrder())
            .userNotes(item.getUserNotes())
            .build();
}
}
