package com.masorange.registry.domain.student;

import java.util.Objects;
import java.util.UUID;

public final class StudentId {

    private final UUID value;

    private StudentId(UUID value) {
        Objects.requireNonNull(value, "StudentId value must not be null");
        this.value = value;
    }

    public static StudentId of(String value) {
        Objects.requireNonNull(value, "StudentId string must not be null");
        return new StudentId(UUID.fromString(value));
    }

    public static StudentId generate() {
        return new StudentId(UUID.randomUUID());
    }

    public String value() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentId)) return false;
        StudentId that = (StudentId) o;
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
