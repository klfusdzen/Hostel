package com.hostel.service;

import com.hostel.domain.User;
import com.hostel.exception.UserNotFoundException;
import com.hostel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> getALL(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id);
        }
        throw new UserNotFoundException();
    }

    public Boolean createUser(User user) {
        try {
            user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            userRepository.save(user);
            log.info(String.format("user %s created successfully", user.getFirstName()));
        } catch (Exception e){
            log.warn(String.format("have problem with creating user %s have error %s", user.getFirstName(), e));
            return false;
        }
        return true;
    }

    public Boolean updateUser(User user) {
        try {
            userRepository.saveAndFlush(user);
            log.info(String.format("user id %s was updated", user.getId()));
        } catch (Exception e){
            log.warn(String.format("have problem with updating user %s have error %s", user.getId(), e));
            return false;
        }
        return true;
    }

    public Boolean deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
            log.info(String.format("user %s is successfully deleted", id));
        } catch (Exception e){
            log.warn(String.format("have problem with deleting user %s have error %s", id, e));
            return false;
        }
        return true;
    }
}
