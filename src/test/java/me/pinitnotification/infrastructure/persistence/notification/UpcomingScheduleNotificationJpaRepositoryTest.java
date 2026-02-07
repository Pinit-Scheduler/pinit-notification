package me.pinitnotification.infrastructure.persistence.notification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UpcomingScheduleNotificationJpaRepositoryTest {
    @Autowired
    private UpcomingScheduleNotificationJpaRepository repository;

    @Test
    void savesAndFindsByScheduleAndOwner() {
        UpcomingScheduleNotificationEntity entity = new UpcomingScheduleNotificationEntity();
        entity.setOwnerId(10L);
        entity.setScheduleId(20L);
        entity.setScheduleTitle("title");
        entity.setScheduleStartTime("2025-01-01T00:00:00Z");
        entity.setIdempotentKey("key-1");

        UpcomingScheduleNotificationEntity saved = repository.save(entity);

        assertThat(saved.getPublicId()).isNotNull();

        Optional<UpcomingScheduleNotificationEntity> loaded =
                repository.findByScheduleIdAndOwnerId(20L, 10L);

        assertThat(loaded).isPresent();
        assertThat(loaded.get().getPublicId()).isNotNull();
    }

    @Test
    void updatesScheduleStartTimeAndIdempotentKey() {
        UpcomingScheduleNotificationEntity entity = new UpcomingScheduleNotificationEntity();
        entity.setOwnerId(10L);
        entity.setScheduleId(20L);
        entity.setScheduleTitle("title");
        entity.setScheduleStartTime("2025-01-01T00:00:00Z");
        entity.setIdempotentKey("key-1");
        repository.save(entity);

        int updatedRows = repository.updateScheduleStartTimeAndIdempotentKey(
                20L,
                10L,
                "2025-01-03T10:30:00Z",
                "key-2"
        );

        assertThat(updatedRows).isEqualTo(1);

        Optional<UpcomingScheduleNotificationEntity> loaded =
                repository.findByScheduleIdAndOwnerId(20L, 10L);

        assertThat(loaded).isPresent();
        assertThat(loaded.get().getScheduleStartTime()).isEqualTo("2025-01-03T10:30:00Z");
        assertThat(loaded.get().getIdempotentKey()).isEqualTo("key-2");
    }
}
