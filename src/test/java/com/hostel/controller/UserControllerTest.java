package com.hostel.controller;

import com.hostel.domain.User;
import com.hostel.repository.UserRepository;
import com.hostel.security.filter.JwtAuthenticationFilter;
import com.hostel.security.service.SecurityService;
import com.hostel.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtAuthenticationFilter jaf;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    UserService usersService;
    @MockBean
    UserRepository usersRepository;
    @MockBean
    SecurityService securityService;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static List<User> userList = null;
    static User user = null;

    @BeforeAll
    static void beforeAll() {
        userList = new ArrayList<>();
        user = new User();
        user.setId((long) 1);
        user.setFirstName("Maxim");
        user.setLastName("Sumar");
        userList.add(user);
    }

    @Test
    void getAll() throws Exception {
        when(usersService.getALL()).thenReturn(userList);
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(1)));
    }

    @Test
    public void testCreateUsers_Success() throws Exception {
        Long id = 1L;

        User users = new User();
        users.setId(id);
        users.setFirstName("Гена");

        when(usersService.createUser(any(User.class))).thenReturn(true);

        mockMvc.perform(post("/user", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(users)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateUser_Conflict() throws Exception {
        Long id = 1L;

        User users = new User();
        users.setId(id);
        users.setFirstName("Maxim");

        when(usersService.createUser(any(User.class))).thenReturn(false);

        mockMvc.perform(post("/user", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(users)))
                .andExpect(status().isConflict());
    }
}