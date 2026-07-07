package com.masorange.registry.domain.teacher;

import com.masorange.shared.domain.EntityId;
import java.util.UUID;

public final class TeacherId extends EntityId {

    private TeacherId(String value) {
        super(value);
    }

    public static TeacherId of(String value) {
        return new TeacherId(value);
    }

    public static TeacherId generate() {
        return new TeacherId(UUID.randomUUID().toString());
    }
}