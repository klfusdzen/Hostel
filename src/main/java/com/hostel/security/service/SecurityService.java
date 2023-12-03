package com.hostel.security.service;

import com.hostel.domain.User;
import com.hostel.domain.Role;
import com.hostel.exception.SameUserInDatabaseException;
import com.hostel.repository.UserRepository;
import com.hostel.security.domain.SecurityCredentials;
import com.hostel.security.domain.dto.AuthRequest;
import com.hostel.security.domain.dto.RegistrationDTO;
import com.hostel.security.repository.SecurityCredentialsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SecurityService {
    private final SecurityCredentialsRepository securityCredentialsRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public Optional<String> generateToken(AuthRequest authRequest) {
        Optional<SecurityCredentials> personCredentials =
                securityCredentialsRepository.getByEmail(authRequest.getEmail());
        if (personCredentials.isPresent() &&
                passwordEncoder.matches(authRequest.getPassword(), personCredentials.get().getPassword())) {
            return Optional.of(jwtUtils.generateJwtToken(authRequest.getEmail()));
        }
        return Optional.empty();
    }

    @Transactional(rollbackOn = Exception.class)
    public void registration(RegistrationDTO registrationDTO) {
        Optional<SecurityCredentials> result = securityCredentialsRepository.getByEmail(registrationDTO.getEmail());
        if (result.isPresent()) {
            throw new SameUserInDatabaseException();
        }
        User user = new User();
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        User userInfoResult = userRepository.save(user);

        SecurityCredentials securityCredentials = new SecurityCredentials();
        securityCredentials.setEmail(registrationDTO.getEmail());
        securityCredentials.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        securityCredentials.setRole(Role.USER);
        securityCredentials.setUser_id(userInfoResult.getId());
        securityCredentialsRepository.save(securityCredentials);
    }
    public boolean checkAccessById(Long id) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        String userRole = String.valueOf(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .findFirst()
                .orElse(null));
        Long userId = securityCredentialsRepository.findUserIdByLogin(userLogin);
        return (userId.equals(id) || userRole.equals("ROLE_ADMIN"));
    }
}
