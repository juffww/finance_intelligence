package com.watchlist.infrastructure.controller;

import com.finance_common.response.ApiResponse;
import com.watchlist.application.dto.request.WatchlistCreationRequest;
import com.watchlist.application.dto.response.WatchlistResponse;
import com.watchlist.application.service.WatchlistService;
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
        return ApiResponse.ok(watchlistService.createWatchlist(userId, request));
    }

    @GetMapping
    public ApiResponse<List<WatchlistResponse>> getUserWatchlist(
            @PathVariable UUID userId
    ) {
        return ApiResponse.ok(watchlistService.getUserWatchlists(userId));
    }
}