package com.hostel.domain.room;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
@Component
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("DeluxeApartment")
@Data
@Entity

public class DeluxeApartment extends Room{
    public DeluxeApartment() {
        super();
    }
}
