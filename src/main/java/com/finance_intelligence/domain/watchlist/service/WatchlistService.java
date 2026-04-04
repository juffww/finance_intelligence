package com.finance_intelligence.domain.watchlist.service;

import com.finance_intelligence.common.exception.AppException;
import com.finance_intelligence.common.exception.ErrorCode;
import com.finance_intelligence.domain.user.entity.User;
import com.finance_intelligence.domain.user.repository.UserRepository;
import com.finance_intelligence.domain.watchlist.dto.request.WatchlistCreationRequest;
import com.finance_intelligence.domain.watchlist.dto.response.WatchlistResponse;
import com.finance_intelligence.domain.watchlist.entity.Watchlist;
import com.finance_intelligence.domain.watchlist.mapper.WatchlistMapper;
import com.finance_intelligence.domain.watchlist.repository.WatchlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WatchlistService {
    private final WatchlistRepository watchlistRepository;
    private final WatchlistMapper watchlistMapper;
    private final UserRepository userRepository;

    // Tạo các danh sách theo dõi (watchlist)
    public WatchlistResponse createWatchlist(UUID userId, WatchlistCreationRequest request)
    {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Watchlist watchlist = watchlistMapper.toWatchlist(request);
        watchlist.setUser(user);

        watchlistRepository.save(watchlist);

        return watchlistMapper.toWatchlistResponse(watchlist);
    }

    //Lấy danh sách theo dõi (watchlist) của user theo userId
    public List<WatchlistResponse> getUserWatchlists(UUID userId)
    {
        List<Watchlist> watchlists = watchlistRepository.findByUserId(userId);
        return watchlists.stream()
                .map(watchlistMapper::toWatchlistResponse)
                .toList();
    }
}
