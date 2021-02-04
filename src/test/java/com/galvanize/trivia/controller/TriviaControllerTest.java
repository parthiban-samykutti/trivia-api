package com.galvanize.trivia.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.trivia.entity.Question.Question;
import com.galvanize.trivia.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TriviaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private QuestionService service;

    @Test
    public void testAddQuestion() throws Exception {
        Question question = Question.builder()
                .name("What did Yankee Doodle stick in his cap?").build();
        Question questionExpected = Question.builder()
                .name(question.getName())
                .id(1)
                .build();
        when(service.addQuestion(any())).thenReturn(questionExpected);
        mockMvc.perform(post("/api/trivia/question")
                .content(mapper.writeValueAsString(question))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(questionExpected.getId()))
                .andExpect(jsonPath("$.name").value(questionExpected.getName()));
        verify(service, times(1)).addQuestion(any());
    }
}