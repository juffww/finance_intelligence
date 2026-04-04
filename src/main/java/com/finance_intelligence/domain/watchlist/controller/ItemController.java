package com.finance_intelligence.domain.watchlist.controller;

import com.finance_intelligence.common.response.ApiResponse;
import com.finance_intelligence.domain.watchlist.dto.request.ItemCreationRequest;
import com.finance_intelligence.domain.watchlist.dto.response.ItemResponse;
import com.finance_intelligence.domain.watchlist.service.ItemService;
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
        return ApiResponse.<ItemResponse>builder()
                .message("Add item into watchlist successfully")
                .data(itemService.addItemToWatchlist(watchlistId, request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ItemResponse>> getWatchlistItems(
            @PathVariable UUID watchlistId
    ) {
        return ApiResponse.<List<ItemResponse>>builder()
                .message("Get all items in watchlist")
                .data(itemService.getWatchlistItems(watchlistId))
                .build();
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