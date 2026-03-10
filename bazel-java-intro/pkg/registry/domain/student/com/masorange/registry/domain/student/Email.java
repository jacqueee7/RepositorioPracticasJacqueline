package com.masorange.registry.domain.student;

import java.util.Objects;

public final class Email {
    private final String value;

    public Email(String value) {
        // Validación: Si el email no está vacío, debe contener un "@"
        if (value != null && !value.isBlank() && !value.contains("@")) {
            throw new IllegalArgumentException("El formato del email no es válido: " + value);
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(value, email.value);
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