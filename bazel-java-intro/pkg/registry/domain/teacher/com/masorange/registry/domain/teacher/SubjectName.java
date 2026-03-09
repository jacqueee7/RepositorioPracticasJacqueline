package com.masorange.registry.domain.teacher;

import java.util.Objects;

public final class SubjectName {

    private final String value;

    private SubjectName(String value) {
        Objects.requireNonNull(value, "SubjectName must not be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("SubjectName must not be empty");
        }
        this.value = value.trim();
    }

    public static SubjectName of(String value) {
        return new SubjectName(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubjectName)) return false;
        SubjectName that = (SubjectName) o;
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
