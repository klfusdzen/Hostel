package com.hostel.security.repository;

import com.hostel.security.domain.SecurityCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SecurityCredentialsRepository extends JpaRepository<SecurityCredentials, Long> {
    Optional<SecurityCredentials> getByEmail(String email);

    @Query(
            nativeQuery = true,
            value = "SELECT user_id FROM security WHERE email = ?1")
    Long findUserIdByLogin(String login);
}
