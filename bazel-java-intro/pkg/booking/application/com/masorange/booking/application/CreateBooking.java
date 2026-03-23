package com.masorange.booking.application;

import com.masorange.booking.domain.Booking;
import com.masorange.booking.domain.RoomId;
import com.masorange.booking.domain.TimeSlot;
import java.util.List;

public final class CreateBooking {
    private final BookingRepository repository;

    public CreateBooking(BookingRepository repository) {
        this.repository = repository;
    }

    public void execute(RoomId roomId, TimeSlot timeSlot) {
        List<Booking> existingBookings = repository.findByRoomId(roomId);
        
        for (Booking existing : existingBookings) {
            if (existing.timeSlot().overlapsWith(timeSlot)) {
                throw new IllegalStateException("La sala ya está ocupada en ese horario");
            }
        }

        repository.save(Booking.create(roomId, timeSlot));
    }
}