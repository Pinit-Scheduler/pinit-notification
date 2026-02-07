package me.pinitnotification.domain.notification

import me.pinitnotification.infrastructure.persistence.UuidV7Generator
import org.junit.platform.commons.util.ReflectionUtils
import java.time.Instant

fun getSampleUpcomingScheduleNotification(
    id: Long = 1L,
    ownerId: Long = 1L,
    scheduleId: Long = 1L,
    scheduleTitle: String = "sample",
    scheduleStartTime: Instant = Instant.parse("2024-06-01T10:00:00Z"),
    idempotencyKey: String = "",
): UpcomingScheduleNotification {
    val sample = UpcomingScheduleNotification(
        UuidV7Generator.generate(),
        ownerId,
        scheduleId,
        scheduleTitle,
        scheduleStartTime,
        idempotencyKey
    )
    ReflectionUtils.findFields(
        UpcomingScheduleNotification::class.java,
        { field -> field.name == "legacyId" },
        ReflectionUtils.HierarchyTraversalMode.TOP_DOWN
    ).forEach { field ->
        field.isAccessible = true
        field.set(sample, id)
    }
    return sample
}
