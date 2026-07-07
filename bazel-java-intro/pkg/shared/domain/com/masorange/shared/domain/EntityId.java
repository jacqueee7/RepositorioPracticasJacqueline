package com.masorange.shared.domain;

import java.util.Objects;
import java.util.UUID;

public abstract class EntityId {
    protected final UUID value;

    // Constructor que valida y parsea el String a UUID
    protected EntityId(String value) {
        this.value = UUID.fromString(Objects.requireNonNull(value, "El ID no puede ser nulo"));
    }

    // Devuelve el valor en formato String tal y como requiere tu CLI y Excel
    public String value() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityId entityId = (EntityId) o;
        return Objects.equals(value, entityId.value);
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