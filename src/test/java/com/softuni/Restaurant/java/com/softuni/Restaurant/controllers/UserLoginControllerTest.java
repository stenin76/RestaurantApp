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
public class UserLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLogPage () throws Exception {
        mockMvc.perform(get("/login")).
                andExpect(status().isOk()).
                andExpect(view().name("/login"));
    }

    @Test
    void testLogin () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login").
                        param("username", "toni").
                        param("password", "test").
                        with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/menu"));

    }
}
