package com.hostel.service;

import com.hostel.domain.room.Room;
import com.hostel.exception.RoomNotFoundException;
import com.hostel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public Room getRoomByType(String roomType) {
        return roomRepository.findRoomByType(roomType);
    }

    public Room getRoomById(Long id){
        if (roomRepository.findById(id).isPresent()) {
            return roomRepository.findById(id).get();
        }
        throw new RoomNotFoundException();
    }

    public List<Room> getAllAvailableRooms() {
        return roomRepository.findAvailableRoomByStatus(Room.RoomStatus.AVAILABLE);
    }

    public Boolean createRoom(Room room) {
        try {
            roomRepository.save(room);
            log.info(String.format("room with type %s was created", room.getRoomType()));
        } catch (Exception e){
            log.warn(String.format("have problem with creating room with type %s have error %s", room.getRoomType(), e));
            return false;
        }
        return true;
    }

    public Boolean updateStatusRoomById(Room room) {
        try {
            roomRepository.saveAndFlush(room);
            log.info(String.format("room status %s was updated", room.getId()));
        } catch (Exception e){
            log.warn(String.format("have problem with updating room status %s have error %s", room.getId(), e));
            return false;
        }
        return true;
    }

    public Boolean deleteRoomById(Long id) {
        try {
            roomRepository.deleteById(id);
            log.info(String.format("room %s was successfully deleted", id));
        } catch (Exception e){
            log.warn(String.format("have problem with deleting room %s have error %s", id, e));
            return false;
        }
        return true;
    }

}
