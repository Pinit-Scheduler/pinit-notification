package me.pinitnotification.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PushSubscriptionRepository extends JpaRepository<PushSubscription, Long> {
}
