package com.masorange.booking.application;

import com.masorange.booking.domain.Capacity;
import com.masorange.booking.domain.Room;
import com.masorange.booking.domain.RoomId;
import com.masorange.booking.domain.RoomName;

public final class CreateRoom {

    private final RoomRepository repository;

    private CreateRoom(RoomRepository repository) {
        this.repository = repository;
    }

    public static CreateRoom create(RoomRepository repository) {
        return new CreateRoom(repository);
    }

    public Room execute(String name, int capacity) {
        RoomId id = RoomId.generate();
        RoomName roomName = RoomName.of(name);
        Capacity roomCapacity = Capacity.of(capacity);
        Room room = Room.create(id, roomName, roomCapacity);
        repository.save(room);
        return room;
    }
}
