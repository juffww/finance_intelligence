package com.watchlist.application.mapper;

import com.watchlist.application.dto.request.ItemCreationRequest;
import com.watchlist.application.dto.response.ItemResponse;
import com.watchlist.domain.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    public Item toWatchlistItem(ItemCreationRequest request);

    public ItemResponse toWatchlistItemResponse(Item item);
}
