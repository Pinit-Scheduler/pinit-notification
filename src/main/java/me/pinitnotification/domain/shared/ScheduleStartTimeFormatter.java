package me.pinitnotification.domain.shared;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public final class ScheduleStartTimeFormatter {
    private static final DateTimeFormatter UTC_SECONDS_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .withZone(ZoneOffset.UTC);

    private ScheduleStartTimeFormatter() {
    }

    public static String format(Instant instant) {
        return UTC_SECONDS_FORMATTER.format(instant.truncatedTo(ChronoUnit.SECONDS));
    }

    public static String format(OffsetDateTime offsetDateTime) {
        return format(offsetDateTime.toInstant());
    }

    public static String normalize(String rawScheduleStartTime) {
        return format(parse(rawScheduleStartTime));
    }

    public static Instant parse(String rawScheduleStartTime) {
        if (rawScheduleStartTime == null || rawScheduleStartTime.isBlank()) {
            throw new IllegalArgumentException("scheduleStartTime must not be null or blank");
        }

        try {
            return OffsetDateTime.parse(rawScheduleStartTime).toInstant();
        } catch (DateTimeParseException ignored) {
            return Instant.parse(rawScheduleStartTime);
        }
    }
}
