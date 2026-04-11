package com.watchlist.infrastructure.repository;

import com.watchlist.domain.entity.Watchlist;
import com.watchlist.domain.repository.WatchlistRepository;
import com.watchlist.infrastructure.entity.WatchlistJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WatchlistRepositoryImpl implements WatchlistRepository {
    private final WatchlistJpaRepository watchlistJpaRepository;

    @Override
    public List<Watchlist> findByUserId(UUID userId) {
        return watchlistJpaRepository.findByUserId(userId)
                .stream().map(this::toWatchlistDomain)
                .toList();
    }

    @Override
    public Watchlist save(Watchlist watchlist)
    {
        WatchlistJpaEntity watchlistJpaEntity = toWatchlistEntity(watchlist);
        WatchlistJpaEntity savedEntity = watchlistJpaRepository.save(watchlistJpaEntity);

        return toWatchlistDomain(savedEntity);
    }

    private Watchlist toWatchlistDomain(WatchlistJpaEntity entity) {
        return Watchlist.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .name(entity.getName())
                .description(entity.getDescription())
                .isDefault(entity.isDefault())
                .build();
    }

    private WatchlistJpaEntity toWatchlistEntity(Watchlist watchlist) {
        WatchlistJpaEntity entity = new WatchlistJpaEntity();
        entity.setId(watchlist.getId());
        entity.setUserId(watchlist.getUserId());
        entity.setName(watchlist.getName());
        entity.setDescription(watchlist.getDescription());
        entity.setDefault(watchlist.isDefault());
        return entity;
    }
}
