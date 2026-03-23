package com.masorange.booking.domain;

import java.time.LocalDateTime;

public final class TimeSlot {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end) || start.isEqual(end)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la de fin");
        }
        this.start = start;
        this.end = end;
    }

    public LocalDateTime start() { return start; }
    public LocalDateTime end() { return end; }

    // El corazón del ejercicio: ¿Se solapan?
    public boolean overlapsWith(TimeSlot other) {
        return this.start.isBefore(other.end) && other.start.isBefore(this.end);
    }
}