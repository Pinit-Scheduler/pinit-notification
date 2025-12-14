package me.pinitnotification.domain.push;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_token_memberId",
            columnNames = {"member_id", "token"}
        )
    }
)
@Getter
public class PushSubscription {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="token", nullable = false)
    private String token;
    @Column(name="member_id", nullable = false)
    private Long memberId;
    protected PushSubscription() {}
    public PushSubscription(Long memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }
}
