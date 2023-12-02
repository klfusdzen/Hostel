package com.hostel.domain.room;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
@Component
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("CommonRoom")
@Data
@Entity

public class CommonRoom extends Room{
    public CommonRoom() {
        super();
    }
}

