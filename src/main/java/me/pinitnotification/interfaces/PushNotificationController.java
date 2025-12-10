package me.pinitnotification.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/push")
public class PushNotificationController {
    @GetMapping("/vapid")
    public String getVapidPublicKey() {
        return "YOUR_VAPID_PUBLIC_KEY";
    }

    @PostMapping("/subscribe")
    public void subscribe() {
        // Handle subscription logic here
    }

}
