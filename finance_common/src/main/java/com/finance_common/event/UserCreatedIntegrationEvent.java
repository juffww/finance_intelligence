package com.finance_common.event;

import java.time.Instant;
import java.util.UUID;

// finance_common
public record UserCreatedIntegrationEvent(
        UUID eventId,
        Instant occurredOn,
        UUID userId,
        String username
) implements IntegrationEvent {}
