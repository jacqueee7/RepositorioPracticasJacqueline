package com.masorange.registry.domain.teacher;

import java.util.Objects;

public final class TeacherName {

    private final String value;

    private TeacherName(String value) {
        Objects.requireNonNull(value, "TeacherName must not be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("TeacherName must not be empty");
        }
        this.value = value.trim();
    }

    public static TeacherName of(String value) {
        return new TeacherName(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherName)) return false;
        TeacherName that = (TeacherName) o;
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
