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

    public Boolean createPayment(Payment payment) {
        try {
            paymentRepository.save(payment);
            log.info(String.format("payment %s was created", payment.getId()));
        } catch (Exception e){
            log.warn(String.format("have problem with creating payment %s have error %s", payment.getId(), e));
            return false;
        }
        return true;
    }
}
