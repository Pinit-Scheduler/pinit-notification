package me.pinitnotification.domain.notification

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset.UTC

internal class UpcomingScheduleNotificationTest {
    val clock = Clock.fixed(
        LocalDateTime.of(
            2026,
            1,
            1,
            0,
            0,
            0
        ).toInstant(UTC), ZoneId.systemDefault()
    )

    @Test
    fun updateScheduleStartTime() {
        //given
        val notification = getSampleUpcomingScheduleNotification()

        //when
        notification.updateScheduleStartTime(Instant.parse("2024-06-01T10:00:00Z"), "123-idempotency-key")

        //then
        Assertions.assertThat(notification.scheduleStartTime).isEqualTo(Instant.parse("2024-06-01T10:00:00Z"))
        Assertions.assertThat(notification.idempotentKey).isEqualTo("123-idempotency-key")
    }

    @Test
    fun isDue() {
        //given
        val notification = getSampleUpcomingScheduleNotification(
            scheduleStartTime = Instant.parse("2024-06-01T10:00:00Z")
        )

        //when
        val isDue = notification.isDue(Instant.now(clock))

        //then
        Assertions.assertThat(isDue).isTrue()
    }
}
