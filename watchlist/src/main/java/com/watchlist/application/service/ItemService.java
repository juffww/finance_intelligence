package com.watchlist.application.service;

import com.finance_common.exception.AppException;
import com.finance_common.exception.ErrorCode;
import com.watchlist.application.dto.request.ItemCreationRequest;
import com.watchlist.application.dto.response.ItemResponse;
import com.watchlist.application.mapper.ItemMapper;
import com.watchlist.domain.entity.Item;
import com.watchlist.domain.entity.Watchlist;
import com.watchlist.domain.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemResponse addItemToWatchlist(UUID watchlistId, ItemCreationRequest request)
    {
        if(itemRepository.existsByWatchlistIdAndAssetId(watchlistId, request.getAssetId()))
        {
            throw new AppException(ErrorCode.ASSET_EXISTED_IN_WATCHLIST);
        }

        Item item = itemMapper.toWatchlistItem(request);
        item.setWatchlistId(watchlistId);

        return itemMapper.toWatchlistItemResponse(itemRepository.save(item));
    }

    public List<ItemResponse> getWatchlistItems(UUID watchListId)
    {
        return itemRepository.findByWatchlistId(watchListId)
                .stream()
                .map(itemMapper::toWatchlistItemResponse)
                .toList();
    }

    public String deleteItem(UUID itemId)
    {
        itemRepository.deleteItem(itemId);
        return "Delete successfully";
    }
}
