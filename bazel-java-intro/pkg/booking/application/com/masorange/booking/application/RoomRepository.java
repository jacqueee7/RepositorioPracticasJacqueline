package com.masorange.booking.application;

import com.masorange.booking.domain.Room;
import com.masorange.booking.domain.RoomId;
import java.util.Optional;

public interface RoomRepository {

    void save(Room room);

    Optional<Room> findById(RoomId id);
}
