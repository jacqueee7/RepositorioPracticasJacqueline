package com.masorange.registry.domain.teacher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TeacherIdTest {

    @Test
    public void shouldGenerateUniqueId() {
        TeacherId id = TeacherId.generate();
        assertNotNull(id.value());
    }

    @Test
    public void shouldCreateFromValidString() {
        TeacherId original = TeacherId.generate();
        TeacherId restored = TeacherId.of(original.value());
        assertEquals(original, restored);
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullString() {
        TeacherId.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInvalidUuid() {
        TeacherId.of("not-a-uuid");
    }

    @Test
    public void shouldBeEqualWhenSameValue() {
        String uuid = TeacherId.generate().value();
        TeacherId id1 = TeacherId.of(uuid);
        TeacherId id2 = TeacherId.of(uuid);
        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());
    }
}
