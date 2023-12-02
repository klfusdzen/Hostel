package com.hostel.domain;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.Timestamp;
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

    @ManyToOne
    @Column(name = "user_id")
    private User user;

    @Column(name = "payment")
    private Boolean payment;

    public void setPaymentAmount(Double paymentAmount) {
    }

    public void setPaymentType(String paymentType) {
    }
}
