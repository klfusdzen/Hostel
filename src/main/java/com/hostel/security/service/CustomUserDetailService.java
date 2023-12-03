package com.hostel.security.service;

import com.hostel.exception.UserFromDatabaseNotFound;
import com.hostel.security.domain.SecurityCredentials;
import com.hostel.security.repository.SecurityCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    final private SecurityCredentialsRepository credentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SecurityCredentials> userFromDatabase = credentialsRepository.getByEmail(username);
        if (userFromDatabase.isEmpty()) {
            throw new UserFromDatabaseNotFound();
        }
        SecurityCredentials user = userFromDatabase.get();
        return User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();

    }
}
