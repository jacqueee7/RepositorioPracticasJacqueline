package com.masorange.booking.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class RoomTest {

    @Test
    public void shouldCreateWithAllFields() {
        RoomId id = RoomId.generate();
        RoomName name = RoomName.of("Aula 101");
        Capacity capacity = Capacity.of(30);

        Room room = Room.create(id, name, capacity);

        assertEquals(id, room.id());
        assertEquals(name, room.name());
        assertEquals(capacity, room.capacity());
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullId() {
        Room.create(null, RoomName.of("Aula 101"), Capacity.of(30));
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullName() {
        Room.create(RoomId.generate(), null, Capacity.of(30));
    }

    @Test(expected = NullPointerException.class)
    public void shouldRejectNullCapacity() {
        Room.create(RoomId.generate(), RoomName.of("Aula 101"), null);
    }

    @Test
    public void shouldBeEqualById() {
        RoomId id = RoomId.generate();
        Room room1 = Room.create(id, RoomName.of("Aula 101"), Capacity.of(30));
        Room room2 = Room.create(id, RoomName.of("Aula 202"), Capacity.of(25));

        assertEquals(room1, room2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentId() {
        Room room1 = Room.create(RoomId.generate(), RoomName.of("Aula 101"), Capacity.of(30));
        Room room2 = Room.create(RoomId.generate(), RoomName.of("Aula 101"), Capacity.of(30));

        assertNotEquals(room1, room2);
    }
}
