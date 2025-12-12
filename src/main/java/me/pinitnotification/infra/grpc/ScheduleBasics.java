package me.pinitnotification.infra.grpc;

public record ScheduleBasics(Long scheduleId, Long ownerId, String scheduleTitle, String designatedStartTime) {
}
