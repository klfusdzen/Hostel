package com.hostel.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
@Entity(name = "payment")
public class Payment {
    @Id
    @SequenceGenerator(name = "seq_payment", sequenceName = "payment_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq_payment", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "user_id")
    private String user;

    @Column(name = "payment")
    private Boolean payment;
}
