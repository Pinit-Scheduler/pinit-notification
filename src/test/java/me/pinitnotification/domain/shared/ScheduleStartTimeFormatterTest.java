package me.pinitnotification.domain.shared;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ScheduleStartTimeFormatterTest {

    @Test
    void normalize_addsSecondsWhenInputHasNoSeconds() {
        String normalized = ScheduleStartTimeFormatter.normalize("2024-06-01T10:00Z");

        assertThat(normalized).isEqualTo("2024-06-01T10:00:00Z");
    }

    @Test
    void normalize_convertsOffsetTimeToUtc() {
        String normalized = ScheduleStartTimeFormatter.normalize("2024-06-01T19:00:00+09:00");

        assertThat(normalized).isEqualTo("2024-06-01T10:00:00Z");
    }

    @Test
    void format_formatsInstantWithUtcSeconds() {
        String formatted = ScheduleStartTimeFormatter.format(Instant.parse("2024-06-01T10:00:00.123Z"));

        assertThat(formatted).isEqualTo("2024-06-01T10:00:00Z");
    }

    @Test
    void format_formatsOffsetDateTimeWithUtcSeconds() {
        String formatted = ScheduleStartTimeFormatter.format(OffsetDateTime.parse("2024-06-01T19:00:00+09:00"));

        assertThat(formatted).isEqualTo("2024-06-01T10:00:00Z");
    }

    @Test
    void parse_parsesMinutePrecisionIso() {
        Instant parsed = ScheduleStartTimeFormatter.parse("2024-06-01T10:00Z");

        assertThat(parsed).isEqualTo(Instant.parse("2024-06-01T10:00:00Z"));
    }

    @Test
    void parse_parsesOffsetIso() {
        Instant parsed = ScheduleStartTimeFormatter.parse("2024-06-01T19:00:00+09:00");

        assertThat(parsed).isEqualTo(Instant.parse("2024-06-01T10:00:00Z"));
    }
}
