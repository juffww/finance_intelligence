package com.watchlist.application.mapper;

import com.watchlist.application.dto.request.WatchlistCreationRequest;
import com.watchlist.application.dto.response.WatchlistResponse;
import com.watchlist.domain.entity.Watchlist;
import com.watchlist.infrastructure.entity.WatchlistJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WatchlistMapper {
    public Watchlist toWatchlist(WatchlistCreationRequest request);

    public WatchlistResponse toWatchlistResponse(Watchlist watchlist);
}
