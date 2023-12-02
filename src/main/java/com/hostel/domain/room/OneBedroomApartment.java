package com.hostel.domain.room;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
@Component
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("OneBedroomApartment")
@Data
@Entity

public class OneBedroomApartment extends Room{
    public OneBedroomApartment() {
        super();
    }
}

