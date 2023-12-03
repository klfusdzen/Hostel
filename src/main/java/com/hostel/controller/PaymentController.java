package com.hostel.controller;

import com.hostel.domain.Booking;
import com.hostel.domain.Payment;
import com.hostel.service.BookingService;
import com.hostel.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {
    public final PaymentService paymentService;
    @PostMapping
    public ResponseEntity<HttpStatus> createPayment(@RequestBody Payment payment){
        return new ResponseEntity<>(paymentService.createPayment(payment) ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }
}
