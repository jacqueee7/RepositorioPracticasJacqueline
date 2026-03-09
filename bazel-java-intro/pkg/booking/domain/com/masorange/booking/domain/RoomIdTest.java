package com.masorange.booking.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class RoomIdTest {

    @Test
    public void shouldGenerateUniqueId() {
        RoomId id = RoomId.generate();

        assertNotNull(id.value());
    }

    @Test
    public void shouldCreateFromValidString() {
        RoomId original = RoomId.generate();
        RoomId restored = RoomId.of(original.value());

        assertEquals(original, restored);
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullString() {
        RoomId.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInvalidUuid() {
        RoomId.of("not-a-uuid");
    }

    @Test
    public void shouldBeEqualWhenSameValue() {
        String uuid = RoomId.generate().value();
        RoomId id1 = RoomId.of(uuid);
        RoomId id2 = RoomId.of(uuid);

        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());
    }
}
