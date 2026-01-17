package me.pinitnotification.domain.notification;

import java.util.List;
import java.util.Optional;

public interface UpcomingScheduleNotificationRepository {
    List<UpcomingScheduleNotification> findAll();
    Optional<UpcomingScheduleNotification> findByScheduleIdAndOwnerId(Long scheduleId, Long ownerId);
    boolean existsByScheduleIdAndOwnerId(Long scheduleId, Long ownerId);
    UpcomingScheduleNotification save(UpcomingScheduleNotification notification);
    void deleteByScheduleIdAndOwnerId(Long scheduleId, Long ownerId);
    void deleteAllInBatch(List<UpcomingScheduleNotification> notifications);
}
