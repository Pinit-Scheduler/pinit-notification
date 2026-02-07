package me.pinitnotification.infrastructure.persistence.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UpcomingScheduleNotificationJpaRepository extends JpaRepository<UpcomingScheduleNotificationEntity, UUID> {
    Optional<UpcomingScheduleNotificationEntity> findByScheduleIdAndOwnerId(Long scheduleId, Long ownerId);
    boolean existsByScheduleIdAndOwnerId(Long scheduleId, Long ownerId);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("""
            UPDATE UpcomingScheduleNotificationEntity n
               SET n.scheduleStartTime = :scheduleStartTime,
                   n.idempotentKey = :idempotentKey
             WHERE n.scheduleId = :scheduleId
               AND n.ownerId = :ownerId
            """)
    int updateScheduleStartTimeAndIdempotentKey(
            @Param("scheduleId") Long scheduleId,
            @Param("ownerId") Long ownerId,
            @Param("scheduleStartTime") String scheduleStartTime,
            @Param("idempotentKey") String idempotentKey
    );

    void deleteByScheduleIdAndOwnerId(Long scheduleId, Long ownerId);
}
