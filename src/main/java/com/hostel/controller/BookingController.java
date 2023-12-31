package com.hostel.controller;

import com.hostel.domain.Booking;
import com.hostel.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    public final BookingService bookingService;
    @PostMapping
    public ResponseEntity<HttpStatus> createBooking(@RequestBody Booking booking){
        return new ResponseEntity<>(bookingService.createBooking(booking) ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }
}
