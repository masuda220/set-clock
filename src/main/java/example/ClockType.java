package example;

public enum ClockType {
    fixed,
    current
    ;

    boolean is(ClockType other) {
        return other.equals(this);
    }
}
