package com.masorange.booking.infrastructure;

import com.masorange.booking.application.BookingRepository;
import com.masorange.booking.domain.Booking;
import com.masorange.booking.domain.RoomId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class InMemoryBookingRepository implements BookingRepository {
    private final List<Booking> bookings = new ArrayList<>();

    @Override
    public void save(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public List<Booking> findByRoomId(RoomId roomId) {
        return bookings.stream()
                .filter(b -> b.roomId().equals(roomId))
                .collect(Collectors.toList());
    }
}