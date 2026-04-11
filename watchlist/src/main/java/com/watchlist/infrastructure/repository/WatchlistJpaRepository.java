package com.watchlist.infrastructure.repository;

import com.watchlist.infrastructure.entity.WatchlistJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WatchlistJpaRepository extends JpaRepository<WatchlistJpaEntity, UUID> {
    List<WatchlistJpaEntity> findByUserId(UUID userId);
}
