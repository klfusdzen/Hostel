package com.hostel.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Component
@Data
public class BookingCreationDTO {
    private String bookingNumber;
    private String user;
    private String payment;
    private String checkIn;
    private String eviction;
}
