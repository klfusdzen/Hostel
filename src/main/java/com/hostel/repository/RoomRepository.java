package com.hostel.repository;

import com.hostel.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
    @Query("SELECT c.roomStatus FROM room c")
    List<String> checkRoomStatus();

    @Query("SELECT c.roomPrice FROM room c")
    List<String> checkRoomPrice();

    @Query("select roomType from room where roomStatus = :free")
    String findFreeRoom(String roomType);

    Room findRoomByType(String roomName);
}
