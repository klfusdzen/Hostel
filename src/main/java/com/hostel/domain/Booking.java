package com.hostel.domain;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
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

    @ManyToOne
    @Column(name = "user_id")
    private User user;

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
}