package com.hostel.service;

import com.hostel.domain.Booking;
import com.hostel.exception.BookingNotFoundException;
import com.hostel.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id){
        if (bookingRepository.findById(id).isPresent()) {
            return bookingRepository.findById(id);
        }
        throw new BookingNotFoundException();
    }

    public Boolean createBooking(Booking booking) {
        try {
            bookingRepository.save(booking);
            log.info(String.format("booking %s was created", booking.getId()));
        } catch (Exception e){
            log.warn(String.format("have problem with booking %s have error %s", booking.getId(), e));
            return false;
        }
        return true;
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
