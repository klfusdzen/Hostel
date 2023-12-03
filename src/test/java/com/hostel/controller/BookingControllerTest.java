package com.hostel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hostel.domain.Booking;
import com.hostel.security.filter.JwtAuthenticationFilter;
import com.hostel.service.BookingService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BookingController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BookingControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtAuthenticationFilter jaf;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    BookingService bookingService;
    static List<Booking> bookingList = null;
    static Booking booking = null;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeAll
    static void beforeAll() {
        bookingList = new ArrayList<>();
        booking = new Booking();
        booking.setId((long) 10);
        booking.setBookingDate(Timestamp.valueOf("12.12.23"));
        bookingList.add(booking);
    }
    @Test
    void getAll() throws Exception {
        when(bookingService.getAll()).thenReturn(bookingList);
        mockMvc.perform(get("/booking"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(10)));
    }
}
