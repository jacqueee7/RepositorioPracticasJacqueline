package com.masorange.grading.domain;

import java.util.Objects;

public final class Subject {

    private final String value;

    private Subject(String value) {
        Objects.requireNonNull(value, "Subject name must not be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject name must not be empty");
        }
        this.value = value.trim();
    }

    public static Subject of(String value) {
        return new Subject(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject that = (Subject) o;
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
