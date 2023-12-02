package com.hostel.service;

import com.hostel.domain.Booking;
import com.hostel.exception.BookingNotFoundException;
import com.hostel.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserService userService;

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id){
        if (bookingRepository.findById(id).isPresent()) {
            return bookingRepository.findById(id).get();
        }
        throw new BookingNotFoundException();
    }

    public void insertBooking(Long id, Long user, String bookingDate, Integer numberOfGuests,
                              String checkIn, String eviction, Boolean payment) {
        Booking booking = new Booking();

        booking.setId(id);
        booking.setUser(userService.getUserById(user).getId());
        booking.setBookingDate(Timestamp.valueOf(bookingDate));
        booking.setNumberOfGuests(numberOfGuests);
        booking.setCheckIn(checkIn);
        booking.setEviction(eviction);
        booking.setPayment(payment);
        bookingRepository.save(booking);
    }

    public void deleteBookingById(Long id){
        try {
            bookingRepository.deleteById(id);
            log.info(String.format("booking %s was successfully deleted", id));
        } catch (Exception e){
            log.warn(String.format("have problem with deleting booking %s have error %s", id, e));
        }
    }
}
