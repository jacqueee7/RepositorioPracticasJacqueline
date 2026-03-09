package com.masorange.registry.domain.classgroup;

import java.util.Objects;

public final class ClassGroupName {

    private final String value;

    private ClassGroupName(String value) {
        Objects.requireNonNull(value, "ClassGroupName must not be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("ClassGroupName must not be empty");
        }
        this.value = value.trim();
    }

    public static ClassGroupName of(String value) {
        return new ClassGroupName(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassGroupName)) return false;
        ClassGroupName that = (ClassGroupName) o;
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
