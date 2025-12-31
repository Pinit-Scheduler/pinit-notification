package me.pinitnotification.interfaces.notification.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "푸시 토큰 요청 바디")
public record PushTokenRequest(
        @Schema(description = "사용자의 디바이스 식별자. UUID 형식으로 제공됩니다.", example = "123e4567-e89b-12d3-a456-426614174000")
        String deviceId,
        @Schema(description = "클라이언트에서 발급받은 FCM 푸시 토큰", example = "fcm-token-example")
        String token
) {
}
