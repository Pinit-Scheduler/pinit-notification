package me.pinitnotification.domain.notification;

import lombok.Getter;
import me.pinitnotification.domain.shared.ScheduleStartTimeFormatter;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Getter
public class UpcomingScheduleNotification implements Notification {
    private UUID id;
    private Long ownerId;
    private Long scheduleId;
    private String scheduleTitle;
    private Instant scheduleStartTime;
    private String idempotentKey;

    protected UpcomingScheduleNotification() {}

    public UpcomingScheduleNotification(UUID id, Long ownerId, Long scheduleId, String scheduleTitle, Instant scheduleStartTime, String idempotentKey) {
        this.id = id;
        this.ownerId = ownerId;
        this.scheduleId = scheduleId;
        this.scheduleTitle = scheduleTitle;
        this.scheduleStartTime = scheduleStartTime;
        this.idempotentKey = idempotentKey;
    }

    public UpcomingScheduleNotification(Long ownerId, Long scheduleId, String scheduleTitle, Instant scheduleStartTime, String idempotentKey) {
        this.ownerId = ownerId;
        this.scheduleId = scheduleId;
        this.scheduleTitle = scheduleTitle;
        this.scheduleStartTime = scheduleStartTime;
        this.idempotentKey = idempotentKey;
    }

    @Override
    public Map<String, String> getData() {
        return Map.of("scheduleId", String.valueOf(scheduleId),
                "scheduleTitle", scheduleTitle,
                "scheduleStartTime", ScheduleStartTimeFormatter.format(scheduleStartTime),
                "idempotentKey", idempotentKey);
    }

    public void updateScheduleStartTime(Instant scheduleStartTime, String idempotentKey) {
        this.scheduleStartTime = scheduleStartTime;
        this.idempotentKey = idempotentKey;
    }

    public boolean isDue(Instant now) {
        return scheduleStartTime != null && !scheduleStartTime.isAfter(now);
    }
}
