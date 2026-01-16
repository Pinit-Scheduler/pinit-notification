package me.pinitnotification.domain.push

import org.junit.platform.commons.util.ReflectionUtils
import java.time.Instant

fun getSamplePushSubscription(
    id: Long = 1L,
    memberId: Long = 1L,
    deviceId: String = "sample-device-id",
    token: String = "sample-token",
    modifiedAt: Instant = Instant.EPOCH,
): PushSubscription {
    val sample = PushSubscription(memberId, deviceId, token)
    ReflectionUtils.findFields(
        PushSubscription::class.java,
        { field -> field.name == "id" },
        ReflectionUtils.HierarchyTraversalMode.TOP_DOWN
    ).forEach { field ->
        field.isAccessible = true
        field.set(sample, id)
    }
    ReflectionUtils.findFields(
        PushSubscription::class.java,
        { field -> field.name == "modifiedAt" },
        ReflectionUtils.HierarchyTraversalMode.TOP_DOWN
    ).forEach { field ->
        field.isAccessible = true
        field.set(sample, modifiedAt)
    }
    return sample
}