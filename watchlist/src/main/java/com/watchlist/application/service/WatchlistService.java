package com.watchlist.application.service;

import com.watchlist.application.dto.request.WatchlistCreationRequest;
import com.watchlist.application.dto.response.WatchlistResponse;
import com.watchlist.application.mapper.WatchlistMapper;
import com.watchlist.domain.entity.Watchlist;
import com.watchlist.domain.repository.WatchlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WatchlistService {
    private final WatchlistRepository watchlistRepository;
    private final WatchlistMapper watchlistMapper;

    // Tạo các danh sách theo dõi (watchlist)
    public WatchlistResponse createWatchlist(UUID userId, WatchlistCreationRequest request)
    {
        Watchlist watchlist = watchlistMapper.toWatchlist(request);
        watchlist.setUserId(userId);
        List<Watchlist> existingWatchlists = watchlistRepository.findByUserId(userId);
        watchlist.setDefault(existingWatchlists.isEmpty());

        //save vao db
        return watchlistMapper.toWatchlistResponse(watchlistRepository.save(watchlist));
    }

    //Lấy danh sách theo dõi (watchlist) của user theo userId
    public List<WatchlistResponse> getUserWatchlists(UUID userId)
    {
        return watchlistRepository.findByUserId(userId)
                .stream()
                .map(watchlistMapper::toWatchlistResponse)
                .toList();
    }

    public void createDefaultWatchlist(UUID userId) {
        Watchlist defaultWatchlist = Watchlist.builder()
                .userId(userId)
                .name("Danh sách theo dõi chính")
                .isDefault(true)
                .build();
        watchlistRepository.save(defaultWatchlist);
    }

}
