package com.hostel.repository;

import com.hostel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Modifying
    @Query("update user c set c.firstName = :firstName where c.id = :id")
    void updateFirstNameById(String firstName, Long id);

    @Modifying
    @Query("update user c set c.lastName = :lastName where c.id = :id")
    void updateLastNameById(String lastName, Long id);
}
