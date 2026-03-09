package com.masorange.registry.domain.classgroup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ClassGroupIdTest {

    @Test
    public void shouldGenerateUniqueId() {
        ClassGroupId id = ClassGroupId.generate();
        assertNotNull(id.value());
    }

    @Test
    public void shouldCreateFromValidString() {
        ClassGroupId original = ClassGroupId.generate();
        ClassGroupId restored = ClassGroupId.of(original.value());
        assertEquals(original, restored);
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullString() {
        ClassGroupId.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInvalidUuid() {
        ClassGroupId.of("not-a-uuid");
    }

    @Test
    public void shouldBeEqualWhenSameValue() {
        String uuid = ClassGroupId.generate().value();
        ClassGroupId id1 = ClassGroupId.of(uuid);
        ClassGroupId id2 = ClassGroupId.of(uuid);
        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());
    }
}
