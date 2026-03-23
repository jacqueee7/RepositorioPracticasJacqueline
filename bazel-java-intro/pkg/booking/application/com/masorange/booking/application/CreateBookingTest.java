package com.masorange.booking.application;

import com.masorange.booking.domain.RoomId;
import com.masorange.booking.domain.TimeSlot;
import com.masorange.booking.infrastructure.InMemoryBookingRepository;
import org.junit.Test;
import java.time.LocalDateTime;

public class CreateBookingTest {

    @Test(expected = IllegalStateException.class)
    public void shouldNotAllowOverlappingBookings() {
        InMemoryBookingRepository repository = new InMemoryBookingRepository();
        CreateBooking createBooking = new CreateBooking(repository);
        RoomId roomId = RoomId.generate();

        LocalDateTime start = LocalDateTime.of(2024, 5, 10, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 5, 10, 11, 0);

        // Primera reserva: 10:00 a 11:00
        createBooking.execute(roomId, new TimeSlot(start, end));

        // Segunda reserva solapada: 10:30 a 11:30 -> Debe lanzar excepcion
        createBooking.execute(roomId, new TimeSlot(start.plusMinutes(30), end.plusMinutes(30)));
    }
}