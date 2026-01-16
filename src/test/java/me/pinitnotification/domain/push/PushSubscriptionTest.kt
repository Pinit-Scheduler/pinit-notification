package me.pinitnotification.domain.push

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset.UTC

internal class PushSubscriptionTest {
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
    fun updateToken() {
        //given
        val subscription = getSamplePushSubscription()

        //when
        subscription.updateToken("new-sample-token")

        //then
        Assertions.assertThat(subscription.token).isEqualTo("new-sample-token")
    }
}