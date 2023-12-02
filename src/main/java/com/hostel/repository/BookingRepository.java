package com.hostel.repository;

import com.hostel.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>{
    @Query("SELECT i.checkIn FROM booking i")
    List<String> checkCheckInDate();

    @Query("SELECT i.eviction FROM booking i")
    List<String> checkEvictionDate();

    @Query("SELECT i.payment FROM booking i")
    List<String> checkPayment();

    @Query("SELECT i.numberOfGuests FROM booking i")
    List<String> checkNumberOfGuests();
}
