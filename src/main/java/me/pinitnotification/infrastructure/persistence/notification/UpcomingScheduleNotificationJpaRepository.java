package me.pinitnotification.infrastructure.persistence.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UpcomingScheduleNotificationJpaRepository extends JpaRepository<UpcomingScheduleNotificationEntity, Long> {
    Optional<UpcomingScheduleNotificationEntity> findByScheduleIdAndOwnerId(Long scheduleId, Long ownerId);

    boolean existsByScheduleIdAndOwnerId(Long scheduleId, Long ownerId);

    void deleteByScheduleIdAndOwnerId(Long scheduleId, Long ownerId);
}
