package me.pinitnotification.application.notification.command;

import java.time.OffsetDateTime;

public record UpcomingUpdatedCommand(
        Long ownerId,
        Long scheduleId,
        OffsetDateTime newUpcomingTime,
        String idempotentKey
) {
}
