package com.galvanize.trivia.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.trivia.entity.Answer;
import com.galvanize.trivia.entity.Question;
import com.galvanize.trivia.service.TriviaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TriviaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private TriviaService service;

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

    @Test
    public void testGetAllQuestions() throws Exception {

        when(service.getAllQuestions()).thenReturn(generateListofQuestions());

        mockMvc.perform(get("/api/trivia/question"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(service, times(1)).getAllQuestions();
    }

    @Test
    public void testGetQuestionById() throws Exception {
        Question expectedQuestion = generateListofQuestions().get(0);
        when(service.getQuestionById(anyInt())).thenReturn(expectedQuestion);

        mockMvc.perform(get("/api/trivia/question/{id}", expectedQuestion.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expectedQuestion.getId()))
                .andExpect(jsonPath("$.name").value(expectedQuestion.getName()));

        verify(service, times(1)).getQuestionById(anyInt());
    }

    @Test
    public void testUpdateQuestion() throws Exception {
        Question question = Question.builder()
                .name("What did Yankee Doodle stick in his cap?")
                .id(1)
                .build();

        doNothing().when(service).updateQuestion(any());
        mockMvc.perform(put("/api/trivia/question")
                .content(mapper.writeValueAsString(question))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).updateQuestion(any());
    }

    @Test
    public void testDeleteQuestion() throws Exception {

        doNothing().when(service).deleteQuestion(anyInt());
        mockMvc.perform(delete("/api/trivia/question/{id}", "1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deleteQuestion(anyInt());
    }

    @Test
    public void testAddAnswer() throws Exception {
        Answer answer = Answer.builder().build();
        Answer answerExpected = Answer.builder()
                .id(1)
                .questionId(1)
                .build();
        when(service.addAnswer(any())).thenReturn(answerExpected);
        mockMvc.perform(post("/api/trivia/question/{id}/answer", 1)
                .content(mapper.writeValueAsString(answer))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.questionId").value(1));
        verify(service, times(1)).addAnswer(any());
    }

    @Test
    public void testGetAnswerByQuestionId() throws Exception {
        List<Answer> answersList = getAnswers();
        when(service.getAnswerByQuestionId(anyInt())).thenReturn(answersList);

        mockMvc.perform(get("/api/trivia/question/{id}/answer", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].questionId").value(1));
        verify(service, times(1)).getAnswerByQuestionId(anyInt());
    }


    @Test
    public void testGenerateTrivia() throws  Exception{
        List<Question> questionList = generateListofQuestions();
        questionList.get(0).setAnswers(getAnswers());
        questionList.get(1).setAnswers(getAnswers());

        when(service.generateQuestions()).thenReturn(questionList);

        mockMvc.perform(get("/api/trivia/quiz"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].answers",hasSize(3)));
        verify(service,times(1)).generateQuestions();
    }



    private List<Question> generateListofQuestions() {

        List<Question> questionList = new ArrayList<>();
        questionList.add(Question.builder().id(1).name("What did Yankee Doodle stick in his cap?").build());
        questionList.add(Question.builder().id(2).name("What word completes the phrase: “Everything but the kitchen”?").build());
        return questionList;
    }

    private List<Answer> getAnswers() {
        List<Answer> answersList = new ArrayList<>();
        answersList.add(Answer.builder()
                .id(1)
                .questionId(1)
                .build());
        answersList.add(Answer.builder()
                .id(2)
                .questionId(1)
                .build());
        answersList.add(Answer.builder()
                .id(3)
                .questionId(1)
                .build());
        return answersList;
    }


}
