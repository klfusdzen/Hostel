package com.hostel.controller;

import com.hostel.domain.Booking;
import com.hostel.domain.LoyaltyProgram;
import com.hostel.service.BookingService;
import com.hostel.service.LoyaltyProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/loyalty_program")
public class LoyaltyProgramController {
    public final LoyaltyProgramService loyaltyProgramService;
    @PostMapping
    public ResponseEntity<HttpStatus> createLoyaltyProgram(@RequestBody LoyaltyProgram loyaltyProgram){
        return new ResponseEntity<>(loyaltyProgramService.createLoyaltyProgram(loyaltyProgram) ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }
}
