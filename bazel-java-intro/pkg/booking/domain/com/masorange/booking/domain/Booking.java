package com.masorange.booking.domain;

import java.util.UUID;

public final class Booking {
    private final UUID id;
    private final RoomId roomId;
    private final TimeSlot timeSlot;

    public Booking(UUID id, RoomId roomId, TimeSlot timeSlot) {
        this.id = id;
        this.roomId = roomId;
        this.timeSlot = timeSlot;
    }

    public static Booking create(RoomId roomId, TimeSlot timeSlot) {
        return new Booking(UUID.randomUUID(), roomId, timeSlot);
    }

    public RoomId roomId() { return roomId; }
    public TimeSlot timeSlot() { return timeSlot; }
}