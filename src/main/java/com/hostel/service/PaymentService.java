package com.hostel.service;

import com.hostel.domain.Payment;
import com.hostel.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserService userService;

    public void insertPayment(Long id, Long user, String paymentDate, Double paymentAmount, String paymentType) {
        Payment payment = new Payment();

        payment.setId(id);
        payment.setPaymentAmount(paymentAmount);
        payment.setPaymentDate(paymentDate);
        payment.setPaymentType(paymentType);
        payment.setUser(userService.getUserById(user));
        paymentRepository.save(payment);
    }
}
