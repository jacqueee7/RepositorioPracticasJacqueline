package com.masorange.booking.infrastructure;

import com.masorange.booking.application.RoomRepository;
import com.masorange.booking.domain.Room;
import com.masorange.booking.domain.RoomId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InMemoryRoomRepository implements RoomRepository {

    private final Map<RoomId, Room> storage = new HashMap<>();

    @Override
    public void save(Room room) {
        storage.put(room.id(), room);
    }

    @Override
    public Optional<Room> findById(RoomId id) {
        return Optional.ofNullable(storage.get(id));
    }
}
