package com.galvanize.trivia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriviaController {

    @PostMapping("/api/trivia/question")
    @ResponseStatus(HttpStatus.CREATED)
    public void addQuestion(){

    }
}
