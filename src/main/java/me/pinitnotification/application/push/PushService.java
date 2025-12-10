package me.pinitnotification.application.push;

public interface PushService {
    String getVapidPublicKey();
    void subscribe(String endpoint, String p256dh, String auth);
    void sendPushMessage(String token, String title, String body);
}
