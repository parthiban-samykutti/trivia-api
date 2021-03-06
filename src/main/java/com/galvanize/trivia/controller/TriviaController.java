package com.galvanize.trivia.controller;

import com.galvanize.trivia.entity.Answer;
import com.galvanize.trivia.entity.Question;
import com.galvanize.trivia.service.TriviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TriviaController {
    @Autowired
    private TriviaService service;

    @PostMapping("/api/trivia/question")
    @ResponseStatus(HttpStatus.CREATED)
    public Question addQuestion(@RequestBody Question question) {
        return service.addQuestion(question);
    }

    @GetMapping("/api/trivia/question")
    public List<Question> getAllQuestion(){

        return service.getAllQuestions();
    }

    @GetMapping("/api/trivia/question/{id}")
    public Question getQuestionById(@PathVariable int id){
        return service.getQuestionById(id);
    }

    @PutMapping("/api/trivia/question")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateQuestion(@RequestBody Question question) {
        service.updateQuestion(question);
    }

    @DeleteMapping("/api/trivia/question/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable int id) {
        service.deleteQuestion(id);
    }

    @PostMapping("/api/trivia/question/{id}/answer")
    @ResponseStatus(HttpStatus.CREATED)
    public Answer addAnswer(@PathVariable("id") int questionId,  @RequestBody Answer answer) {
        answer.setQuestionId(questionId);
        return service.addAnswer(answer);
    }

    @GetMapping("/api/trivia/question/{id}/answer")
    @ResponseStatus(HttpStatus.OK)
    public List<Answer> getAnswerByQuestionId(@PathVariable("id") int questionId){
        return service.getAnswerByQuestionId(questionId);
    }

    @GetMapping("/api/trivia/quiz")
    @ResponseStatus(HttpStatus.OK)
    public List<Question> getGenerateTrivia(){
        return service.generateQuestions();
    }
}
