package me.pinitnotification.interfaces;

import me.pinitnotification.application.push.PushService;
import me.pinitnotification.domain.member.MemberId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/push")
public class PushNotificationController {
    private final PushService pushService;

    public PushNotificationController(PushService pushService) {
        this.pushService = pushService;
    }

    @GetMapping("/vapid")
    public String getVapidPublicKey() {
        return pushService.getVapidPublicKey();
    }

    @PostMapping("/subscribe")
    public void subscribe(@MemberId Long memberId, String token) {
        pushService.subscribe(memberId, token);
    }

    @PostMapping("/unsubscribe")
    public void unsubscribe(@MemberId Long memberId, String token) {
        pushService.unsubscribe(memberId, token);
    }
}
