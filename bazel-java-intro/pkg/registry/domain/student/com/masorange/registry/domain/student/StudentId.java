package com.masorange.registry.domain.student;

import com.masorange.shared.domain.EntityId;
import java.util.UUID;

public final class StudentId extends EntityId {

    private StudentId(String value) {
        super(value);
    }

    public static StudentId of(String value) {
        return new StudentId(value);
    }

    public static StudentId generate() {
        return new StudentId(UUID.randomUUID().toString());
    }
}