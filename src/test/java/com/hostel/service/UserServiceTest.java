package com.hostel.service;

import com.hostel.domain.User;
import com.hostel.repository.UserRepository;
import com.hostel.security.service.SecurityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private SecurityService securityService;
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
    static List<User> userList = null;
    static User user = null;
    static Long userId = 10L;

    @BeforeAll
    static void beforeAll() {
        userList = new ArrayList<>();
        user = new User();
        user.setId(userId);
        user.setFirstName("Gena");

        userList.add(user);

        Authentication authenticationMock = mock(Authentication.class);
        SecurityContext securityContextMock = mock(SecurityContext.class);
        when(securityContextMock.getAuthentication()).thenReturn(authenticationMock);
        SecurityContextHolder.setContext(securityContextMock);
    }

    @Test
    void getAllTest() {
        when(userRepository.findAll()).thenReturn(userList);

        List<User> resultList = userService.getALL();
        verify(userRepository, Mockito.times(1)).findAll();
        Assertions.assertNotNull(resultList);
    }

    @Test
    void createTest() {
        Mockito.when(userRepository.save(any())).thenReturn(user);

        Boolean result = userService.createUser(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(any());
        Assertions.assertTrue(result);
    }
}