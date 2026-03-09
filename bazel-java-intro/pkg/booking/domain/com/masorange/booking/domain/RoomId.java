package com.masorange.booking.domain;

import java.util.Objects;
import java.util.UUID;

public final class RoomId {

    private final UUID value;

    private RoomId(UUID value) {
        Objects.requireNonNull(value, "RoomId value must not be null");
        this.value = value;
    }

    public static RoomId of(String value) {
        Objects.requireNonNull(value, "RoomId string must not be null");
        return new RoomId(UUID.fromString(value));
    }

    public static RoomId generate() {
        return new RoomId(UUID.randomUUID());
    }

    public String value() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomId)) return false;
        RoomId that = (RoomId) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
