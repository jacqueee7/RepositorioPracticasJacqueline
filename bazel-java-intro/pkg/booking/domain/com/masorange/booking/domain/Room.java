package com.masorange.booking.domain;

import java.util.Objects;

public final class Room {

    private final RoomId id;
    private final RoomName name;
    private final Capacity capacity;

    private Room(RoomId id, RoomName name, Capacity capacity) {
        Objects.requireNonNull(id, "Room id must not be null");
        Objects.requireNonNull(name, "Room name must not be null");
        Objects.requireNonNull(capacity, "Room capacity must not be null");
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public static Room create(RoomId id, RoomName name, Capacity capacity) {
        return new Room(id, name, capacity);
    }

    public RoomId id() {
        return id;
    }

    public RoomName name() {
        return name;
    }

    public Capacity capacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room that = (Room) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Room{id=" + id + ", name=" + name + ", capacity=" + capacity + "}";
    }
}
