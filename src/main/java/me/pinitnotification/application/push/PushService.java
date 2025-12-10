package me.pinitnotification.application.push;

public interface PushService {
    String getVapidPublicKey();
    void subscribe(Long memberId, String token);
    void unsubscribe(Long memberId, String token);
    void sendPushMessage(String token, String title, String body);
}
