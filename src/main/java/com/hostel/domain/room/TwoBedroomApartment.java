package com.hostel.domain.room;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
@Component
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("TwoBedroomApartment")
@Data
@Entity

public class TwoBedroomApartment extends Room{
    public TwoBedroomApartment() {
        super();
    }
}