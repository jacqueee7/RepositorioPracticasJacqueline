package com.masorange.registry.domain.student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class StudentIdTest {

    @Test
    public void shouldGenerateUniqueId() {
        StudentId id = StudentId.generate();
        assertNotNull(id.value());
    }

    @Test
    public void shouldCreateFromValidString() {
        StudentId original = StudentId.generate();
        StudentId restored = StudentId.of(original.value());
        assertEquals(original, restored);
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullString() {
        StudentId.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInvalidUuid() {
        StudentId.of("not-a-uuid");
    }

    @Test
    public void shouldBeEqualWhenSameValue() {
        String uuid = StudentId.generate().value();
        StudentId id1 = StudentId.of(uuid);
        StudentId id2 = StudentId.of(uuid);
        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());
    }
}
