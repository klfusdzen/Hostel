package com.hostel.repository;

import com.hostel.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT i.payment FROM payment i")
    List<String> checkPayment();
}
