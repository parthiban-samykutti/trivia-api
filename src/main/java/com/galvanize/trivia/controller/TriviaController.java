package com.galvanize.trivia.controller;

import com.galvanize.trivia.entity.Question.Question;
import com.galvanize.trivia.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriviaController {
    @Autowired
    private QuestionService service;

    @PostMapping("/api/trivia/question")
    @ResponseStatus(HttpStatus.CREATED)
    public Question addQuestion(@RequestBody Question question) {
        return service.addQuestion(question);
    }
}
