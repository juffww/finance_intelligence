package com.finance_intelligence.domain.watchlist.mapper;

import com.finance_intelligence.domain.watchlist.dto.request.ItemCreationRequest;
import com.finance_intelligence.domain.watchlist.dto.response.ItemResponse;
import com.finance_intelligence.domain.watchlist.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    public Item toWatchlistItem(ItemCreationRequest request);

    @Mapping(target = "assetId", source = "asset.id")
    public ItemResponse toWatchlistItemResponse(Item item);
}
