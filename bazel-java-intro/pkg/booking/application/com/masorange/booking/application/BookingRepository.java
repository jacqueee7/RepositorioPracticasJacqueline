package com.masorange.booking.application;

import com.masorange.booking.domain.Booking;
import com.masorange.booking.domain.RoomId;
import java.util.List;

public interface BookingRepository {
    void save(Booking booking);
    List<Booking> findByRoomId(RoomId roomId);
}