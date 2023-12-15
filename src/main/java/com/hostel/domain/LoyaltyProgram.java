package com.hostel.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Entity(name = "loyaltyProgram")
public class LoyaltyProgram {
    @Id
    @SequenceGenerator(name = "seq_loyaltyProgram", sequenceName = "loyaltyProgram_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq_loyaltyProgram", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_id")
    private String user;

    @Column(name = "level")
    private String level;
}
