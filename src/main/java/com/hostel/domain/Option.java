package com.hostel.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
@Entity(name = "option")
public class Option {
    @Id
    @SequenceGenerator(name = "seq_option", sequenceName = "option_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq_option", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "option")
    private String option;
}
