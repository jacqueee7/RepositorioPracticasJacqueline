package com.masorange.booking.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RoomNameTest {

    @Test
    public void shouldCreateWithValidName() {
        RoomName name = RoomName.of("Aula 101");

        assertEquals("Aula 101", name.value());
    }

    @Test
    public void shouldTrimWhitespace() {
        RoomName name = RoomName.of("  Aula 101  ");

        assertEquals("Aula 101", name.value());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNull() {
        RoomName.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectEmpty() {
        RoomName.of("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectBlank() {
        RoomName.of("   ");
    }

    @Test
    public void shouldBeEqualWhenSameValue() {
        RoomName name1 = RoomName.of("Aula 101");
        RoomName name2 = RoomName.of("Aula 101");

        assertEquals(name1, name2);
        assertEquals(name1.hashCode(), name2.hashCode());
    }
}
