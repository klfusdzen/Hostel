package com.hostel.domain;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import lombok.Data;

@Component
@Data
@Entity (name = "user")
public class User {
    @Id
    @SequenceGenerator(name = "seq_user", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq_user", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "created")
    private Timestamp created;
}
