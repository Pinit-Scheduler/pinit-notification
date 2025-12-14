package me.pinitnotification.application.notification;

import me.pinitnotification.domain.notification.UpcomingScheduleNotification;
import me.pinitnotification.domain.notification.UpcomingScheduleNotificationRepository;
import me.pinitnotification.domain.push.PushSubscription;
import me.pinitnotification.domain.push.PushSubscriptionRepository;
import me.pinitnotification.application.push.PushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class NotificationDispatchScheduler {
    private static final Logger log = LoggerFactory.getLogger(NotificationDispatchScheduler.class);

    private final UpcomingScheduleNotificationRepository notificationRepository;
    private final PushSubscriptionRepository pushSubscriptionRepository;
    private final PushService pushService;
    private final Clock clock;

    public NotificationDispatchScheduler(UpcomingScheduleNotificationRepository notificationRepository,
                                         PushSubscriptionRepository pushSubscriptionRepository,
                                         PushService pushService,
                                         Clock clock) {
        this.notificationRepository = notificationRepository;
        this.pushSubscriptionRepository = pushSubscriptionRepository;
        this.pushService = pushService;
        this.clock = clock;
    }

    @Scheduled(cron = "0 */10 * * * *")
    @Transactional
    public void dispatchDueNotifications() {
        OffsetDateTime now = OffsetDateTime.now(clock);
        List<UpcomingScheduleNotification> dueNotifications = notificationRepository.findAll().stream()
                .filter(notification -> notification.isDue(now))
                .toList();

        if (dueNotifications.isEmpty()) {
            return;
        }

        dueNotifications.forEach(this::sendNotificationToOwner);
        notificationRepository.deleteAllInBatch(dueNotifications);
    }


    private void sendNotificationToOwner(UpcomingScheduleNotification notification) {
        List<PushSubscription> subscriptions = pushSubscriptionRepository.findAllByMemberId(notification.getOwnerId());
        if (subscriptions.isEmpty()) {
            log.info("No push tokens for owner; skip sending. ownerId={}, scheduleId={}", notification.getOwnerId(), notification.getScheduleId());
            return;
        }

        subscriptions.forEach(subscription -> pushService.sendPushMessage(subscription.getToken(), notification));
    }
}
