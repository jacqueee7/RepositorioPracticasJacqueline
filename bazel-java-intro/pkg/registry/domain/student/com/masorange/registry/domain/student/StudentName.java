package com.masorange.registry.domain.student;

import java.util.Objects;

public final class StudentName {

    private final String value;

    private StudentName(String value) {
        Objects.requireNonNull(value, "StudentName must not be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("StudentName must not be empty");
        }
        this.value = value.trim();
    }

    public static StudentName of(String value) {
        return new StudentName(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentName)) return false;
        StudentName that = (StudentName) o;
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
