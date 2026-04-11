package com.watchlist.application.eventHandler;

import com.finance_common.event.UserCreatedIntegrationEvent;
import com.watchlist.application.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

// watchlist/src/main/java/com/watchlist/application/eventhandler/UserCreatedIntegrationEventHandler.java
@Component
@RequiredArgsConstructor
@Slf4j
public class UserCreatedIntegrationEventHandler {

    private final WatchlistService watchlistService;

    @EventListener
    public void handle(UserCreatedIntegrationEvent event) {
        log.info("Received UserCreatedIntegrationEvent for user id: {}", event.userId());
        try {
            watchlistService.createDefaultWatchlist(event.userId());
            log.info("Successfully created default watchlist for user id: {}", event.userId());
        } catch (Exception e) {
            log.error("Failed to create default watchlist...", e);
        }
    }
}


