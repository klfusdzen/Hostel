package com.hostel.repository;

import com.hostel.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OptionRepository extends JpaRepository<Option, Long> {
    @Modifying
    @Query("update option c set c.option = :option where c.id = :id")
    void updateLOption(String level, Long id);
}
