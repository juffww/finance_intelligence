package com.watchlist.domain.repository;

import com.watchlist.domain.entity.Watchlist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WatchlistRepository {
    List<Watchlist> findByUserId(UUID userId);

    Watchlist save(Watchlist watchlist);
}
