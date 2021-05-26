package example;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static example.ClockType.current;
import static example.ClockType.fixed;

public class DateContext {

    static Clock clock;
    static String type;
    static String value;

    // 最初に now()が呼ばれた時に初期化
    static {
        initialize();
    }

    // テストでも使う
    static void initialize() {
        setTypeAndValue();
        ClockType clockType = ClockType.valueOf(type);

        if (clockType.is(fixed)) {
            toFixedDate();
            return;
        }

        if (clockType.is(current)) {
            toCurrentDate();
            return;
        }

        toCurrentDate();
    }

    private static void setTypeAndValue() {
        type = System.getProperty("clock.type");
        value = System.getProperty("clock.value");

        if (type == null || type.isBlank()) type = current.name();
        if (value == null || value.isBlank()) value = LocalDate.now().toString();
    }

    private static void toFixedDate() {
        LocalDate date = LocalDate.parse(value);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = date.atStartOfDay(zone).toInstant();
        clock = Clock.fixed(instant, zone);
    }

    private static void toCurrentDate() {
        clock = Clock.systemDefaultZone();
    }

    public static LocalDate now() {
        return LocalDate.now(clock);
    }
}
