package com.softuni.Restaurant.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegPage () throws Exception {
        mockMvc.perform(get("/register")).
                andExpect(status().isOk()).
                andExpect(view().name("register"));
    }

    @Test
    void testRegistration () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register").
                param("email", "toni@toni").
                param("username", "toni").
                param("password", "test").
                param("confirmPassword", "test").
                        with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/register"));

    }
}
