package com.finance_intelligence.domain.watchlist.mapper;

import com.finance_intelligence.domain.watchlist.dto.request.WatchlistCreationRequest;
import com.finance_intelligence.domain.watchlist.dto.response.WatchlistResponse;
import com.finance_intelligence.domain.watchlist.entity.Watchlist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WatchlistMapper {
    public Watchlist toWatchlist(WatchlistCreationRequest request);

    public WatchlistResponse toWatchlistResponse(Watchlist watchlist);
}
