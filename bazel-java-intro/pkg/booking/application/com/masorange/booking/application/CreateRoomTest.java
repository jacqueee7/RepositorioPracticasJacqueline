package com.masorange.booking.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.masorange.booking.domain.Room;
import com.masorange.booking.infrastructure.InMemoryRoomRepository;
import org.junit.Test;

public class CreateRoomTest {

    @Test
    public void shouldCreateAndPersistRoom() {
        InMemoryRoomRepository repository = new InMemoryRoomRepository();
        CreateRoom createRoom = CreateRoom.create(repository);

        Room room = createRoom.execute("Aula 101", 30);

        assertEquals("Aula 101", room.name().value());
        assertEquals(30, room.capacity().value());
        assertTrue(repository.findById(room.id()).isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWithEmptyName() {
        InMemoryRoomRepository repository = new InMemoryRoomRepository();
        CreateRoom createRoom = CreateRoom.create(repository);

        createRoom.execute("", 30);
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void shouldFailWithInvalidCapacity() {
        InMemoryRoomRepository repository = new InMemoryRoomRepository();
        CreateRoom createRoom = CreateRoom.create(repository);

        createRoom.execute("Aula 101", 0);
    }*/
}
