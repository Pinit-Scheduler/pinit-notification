package me.pinitnotification.application.notification.command;

public record ScheduleDeletedCommand(
        Long ownerId,
        Long scheduleId,
        String idempotentKey
) {
}
