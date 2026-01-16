package me.pinitnotification.domain.push

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class PushSubscriptionTest {

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