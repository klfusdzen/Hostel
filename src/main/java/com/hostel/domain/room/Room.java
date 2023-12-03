package com.hostel.domain.room;

import com.hostel.domain.Option;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "room_type", discriminatorType = DiscriminatorType.STRING)
@Entity (name = "room")
public class Room {
    @Id
    @SequenceGenerator(name = "seq_room", sequenceName = "room_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq_room", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "room_status")
    private Long roomStatus;

    @Column(name = "room_price")
    private Long roomPrice;

    @ManyToOne
    @Column(name = "option")
    private Option option;

    public enum RoomStatus {
        AVAILABLE,
        RESERVED
    }
}
