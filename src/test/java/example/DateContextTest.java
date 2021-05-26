package example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateContextTest {

    private void setProperty(String type, String value) {
        System.setProperty("clock.type" , type);
        System.setProperty("clock.value" , value);
    }
    @Test
    void nowDefault() {
        setProperty("","");
        DateContext.initialize();
        assertEquals(LocalDate.now(), DateContext.now());
    }

    @Test
    void nowCurrent() {
        setProperty("current","dummy");
        DateContext.initialize();
        assertEquals(LocalDate.now(), DateContext.now());
    }

    @Test
    void nowWithFixedValue() {
        setProperty("fixed", "1970-01-01");
        DateContext.initialize();
        String value = System.getProperty("clock.value");
        assertEquals(LocalDate.parse(value) , DateContext.now());
    }
}