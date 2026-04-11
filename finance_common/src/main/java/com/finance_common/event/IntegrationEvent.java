package com.finance_common.event;

import java.time.Instant;
import java.util.UUID;

public interface IntegrationEvent {
    UUID eventId();
    Instant occurredOn();
}
