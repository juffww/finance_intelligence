package com.finance_intelligence.domain.watchlist.controller;

import com.finance_intelligence.common.response.ApiResponse;
import com.finance_intelligence.domain.watchlist.dto.request.WatchlistCreationRequest;
import com.finance_intelligence.domain.watchlist.dto.response.WatchlistResponse;
import com.finance_intelligence.domain.watchlist.service.WatchlistService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/watchlists")
@AllArgsConstructor
public class WatchlistController {
    private final WatchlistService watchlistService;

    @PostMapping
    public ApiResponse<WatchlistResponse> createWatchlist(
            @PathVariable UUID userId,
            @Valid @RequestBody WatchlistCreationRequest request
    ) {
        return ApiResponse.<WatchlistResponse>builder()
                .message("Create watchlist successfully")
                .data(watchlistService.createWatchlist(userId, request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<WatchlistResponse>> getUserWatchlists(
            @PathVariable UUID userId
    ) {
        return ApiResponse.<List<WatchlistResponse>>builder()
                .message("Get all watchlists of user")
                .data(watchlistService.getUserWatchlists(userId))
                .build();
    }
}