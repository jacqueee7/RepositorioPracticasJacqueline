package com.masorange.booking.domain;

import java.util.Objects;

public final class Capacity {

    private final int value;

    private Capacity(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0, got: " + value);
        }
        this.value = value;
    }

    public static Capacity of(int value) {
        return new Capacity(value);
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Capacity)) return false;
        Capacity that = (Capacity) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
