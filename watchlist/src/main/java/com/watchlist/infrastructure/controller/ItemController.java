package com.watchlist.infrastructure.controller;

import com.finance_common.response.ApiResponse;
import com.watchlist.application.dto.request.ItemCreationRequest;
import com.watchlist.application.dto.response.ItemResponse;
import com.watchlist.application.service.ItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/watchlists/{watchlistId}/items")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ApiResponse<ItemResponse> addItem(
            @PathVariable UUID watchlistId,
            @Valid @RequestBody ItemCreationRequest request
    ) {
        return ApiResponse.ok(itemService.addItemToWatchlist(watchlistId, request));
    }

    @GetMapping
    public ApiResponse<List<ItemResponse>> getWatchlistItems(
            @PathVariable UUID watchlistId
    ) {
        return ApiResponse.ok(itemService.getWatchlistItems(watchlistId));
    }

//    @DeleteMapping("/{itemId}")
//    public ApiResponse<String> deleteItem(
//            @PathVariable UUID watchlistId,
//            @PathVariable UUID itemId
//    ) {
//        return ApiResponse.<String>builder()
//                .message(itemService.deleteItem(watchlistId, itemId))
//                .build();
//    }
}