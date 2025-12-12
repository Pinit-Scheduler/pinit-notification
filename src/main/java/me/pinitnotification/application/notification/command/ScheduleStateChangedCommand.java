package me.pinitnotification.application.notification.command;

public record ScheduleStateChangedCommand(
        Long ownerId,
        Long scheduleId,
        String beforeState,
        String idempotentKey
) {
}
