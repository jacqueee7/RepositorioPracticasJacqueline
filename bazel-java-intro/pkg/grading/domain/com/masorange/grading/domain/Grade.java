package com.masorange.grading.domain;

import java.util.Objects;

public final class Grade {

    private static final double MIN_VALUE = 0.0;
    private static final double MAX_VALUE = 10.0;

    private final double value;

    private Grade(double value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException(
                    "Grade must be between " + MIN_VALUE + " and " + MAX_VALUE + ", got: " + value);
        }
        this.value = value;
    }

    public static Grade of(double value) {
        return new Grade(value);
    }

    public double value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grade)) return false;
        Grade that = (Grade) o;
        return Double.compare(that.value, value) == 0;
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
