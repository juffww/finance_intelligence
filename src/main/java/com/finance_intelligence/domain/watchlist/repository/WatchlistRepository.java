package com.finance_intelligence.domain.watchlist.repository;

import com.finance_intelligence.domain.user.entity.User;
import com.finance_intelligence.domain.watchlist.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, UUID> {
    List<Watchlist> findByUserId(UUID userId);

    UUID user(User user);
}
