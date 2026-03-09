package com.masorange.booking.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.ImmutableList;
import com.masorange.booking.application.CreateRoom;
import com.masorange.booking.application.RoomRepository;
import com.masorange.booking.domain.Room;
import com.masorange.booking.infrastructure.InMemoryRoomRepository;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        RoomRepository roomRepository = new InMemoryRoomRepository();
        CreateRoom createRoom = CreateRoom.create(roomRepository);

        Room room1 = createRoom.execute("Aula 101", 30);
        Room room2 = createRoom.execute("Aula 202", 25);
        Room room3 = createRoom.execute("Laboratorio 1", 20);

        ImmutableList<Room> rooms = ImmutableList.of(room1, room2, room3);

        List<Map<String, Object>> roomList = rooms.stream()
                .map(room -> {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("id", room.id().value());
                    map.put("name", room.name().value());
                    map.put("capacity", room.capacity().value());
                    return map;
                })
                .collect(Collectors.toList());

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("totalRooms", rooms.size());
        result.put("rooms", roomList);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(mapper.writeValueAsString(result));
    }
}
