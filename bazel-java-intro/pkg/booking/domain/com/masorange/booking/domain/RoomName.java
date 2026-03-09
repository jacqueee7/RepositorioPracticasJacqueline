package com.masorange.booking.domain;

import java.util.Objects;

public final class RoomName {

    private final String value;

    private RoomName(String value) {
        Objects.requireNonNull(value, "RoomName must not be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("RoomName must not be empty");
        }
        this.value = value.trim();
    }

    public static RoomName of(String value) {
        return new RoomName(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomName)) return false;
        RoomName that = (RoomName) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
