package com.finance_intelligence.domain.watchlist.service;

import com.finance_intelligence.common.exception.AppException;
import com.finance_intelligence.common.exception.ErrorCode;
import com.finance_intelligence.domain.asset.entity.Asset;
import com.finance_intelligence.domain.asset.repository.AssetRepository;
import com.finance_intelligence.domain.watchlist.dto.request.ItemCreationRequest;
import com.finance_intelligence.domain.watchlist.dto.response.ItemResponse;
import com.finance_intelligence.domain.watchlist.entity.Watchlist;
import com.finance_intelligence.domain.watchlist.entity.Item;
import com.finance_intelligence.domain.watchlist.mapper.ItemMapper;
import com.finance_intelligence.domain.watchlist.repository.ItemRepository;
import com.finance_intelligence.domain.watchlist.repository.WatchlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final WatchlistRepository watchlistRepository;
    private final AssetRepository assetRepository;

    public ItemResponse addItemToWatchlist(UUID watchlistId, ItemCreationRequest request)
    {
        Watchlist watchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new AppException(ErrorCode.WATCHLIST_NOT_EXISTED));

        if(itemRepository.existsByWatchlistIdAndAssetId(watchlistId, request.getAssetId()))
        {
            throw new AppException(ErrorCode.ASSET_EXISTED_IN_WATCHLIST);
        }

        Asset asset = assetRepository.findById(request.getAssetId())
                .orElseThrow(() -> new AppException(ErrorCode.ASSET_NOT_EXITSTED));

        Item item = itemMapper.toWatchlistItem(request);
        item.setWatchlist(watchlist);
        item.setAsset(asset);

        itemRepository.save(item);

        return itemMapper.toWatchlistItemResponse(item);
    }

    public List<ItemResponse> getWatchlistItems(UUID watchListId)
    {
        List<Item> items = itemRepository.findByWatchlistIdOrderByDisplayOrderAsc(watchListId);

        return items.stream()
                .map(itemMapper::toWatchlistItemResponse)
                .toList();
    }

    public String deleteItem(UUID itemId)
    {
        itemRepository.deleteById(itemId);
        return "Delete successfully";
    }
}
