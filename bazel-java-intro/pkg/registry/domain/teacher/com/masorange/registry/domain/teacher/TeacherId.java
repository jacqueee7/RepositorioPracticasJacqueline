package com.masorange.registry.domain.teacher;

import java.util.Objects;
import java.util.UUID;

public final class TeacherId {

    private final UUID value;

    private TeacherId(UUID value) {
        Objects.requireNonNull(value, "TeacherId value must not be null");
        this.value = value;
    }

    public static TeacherId of(String value) {
        Objects.requireNonNull(value, "TeacherId string must not be null");
        return new TeacherId(UUID.fromString(value));
    }

    public static TeacherId generate() {
        return new TeacherId(UUID.randomUUID());
    }

    public String value() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherId)) return false;
        TeacherId that = (TeacherId) o;
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
