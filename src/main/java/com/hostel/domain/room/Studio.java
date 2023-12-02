package com.hostel.domain.room;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
@Component
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Studio")
@Data
@Entity

public class Studio extends Room{
    public Studio() {
        super();
    }
}
