package com.hostel.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.Data;
@Component
@Data
@Entity(name = "booking")

public class Booking {
    @Id
    @SequenceGenerator(name = "seq_booking", sequenceName = "booking_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq_booking", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "booking_number")
    private String investmentNumber;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "number_of_guests")
    private int numberOfGuests;

    @Column(name = "check_in")
    private String checkIn;

    @Column(name = "eviction")
    private String eviction;

    @Column(name = "payment")
    private Boolean payment;

    @Column(name = "booking_date")
    private Timestamp bookingDate;

    public void setUser(Long userById) {
    }
}