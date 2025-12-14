package me.pinitnotification.domain.push;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PushSubscriptionRepository extends JpaRepository<PushSubscription, Long> {
    List<PushSubscription> findAllByMemberId(Long memberId);

    void deleteByToken(String token);
}
