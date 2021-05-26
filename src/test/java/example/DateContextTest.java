package example;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetSystemProperty;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

// FIXME メソッドごとのテストはOK、クラスではエラーになる
class DateContextTest {
    @Test
    @SetSystemProperty(key = "clock.type", value = "")
    @SetSystemProperty(key = "clock.value", value = "")
    void nowDefault() {
        DateContext.initialize();
        assertEquals(LocalDate.now(), DateContext.now());
    }

    @Test
    @SetSystemProperty(key = "clock.type", value = "current")
    @SetSystemProperty(key = "clock.value", value = "dummy")
    void nowCurrent() {
        DateContext.initialize();
        assertEquals(LocalDate.now(), DateContext.now());
    }

    @Test
    @SetSystemProperty(key = "clock.type", value = "fixed")
    @SetSystemProperty(key = "clock.value", value = "1970-01-01")
    void nowWithFixedValue() {
        DateContext.initialize();
        String value = System.getProperty("clock.value");
        assertEquals(LocalDate.parse(value) , DateContext.now());
    }
}