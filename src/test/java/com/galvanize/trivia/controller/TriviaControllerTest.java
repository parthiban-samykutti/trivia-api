package com.galvanize.trivia.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TriviaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddQuestion() throws Exception {
        mockMvc.perform(post("/api/trivia/question")).andExpect(status().isCreated());
    }
}
