package com.hostel.repository;

import com.hostel.domain.LoyaltyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Long> {
    @Modifying
    @Query("update loyaltyProgram c set c.level = :level where c.id = :id")
    void updateLoyaltyLevel(String level, Long id);
}
