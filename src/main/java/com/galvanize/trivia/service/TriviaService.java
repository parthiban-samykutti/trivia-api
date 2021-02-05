package com.galvanize.trivia.service;

import com.galvanize.trivia.entity.Answer;
import com.galvanize.trivia.entity.Question;
import com.galvanize.trivia.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TriviaService {
    @Autowired
    private QuestionRepository questionRepository;

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return null;
    }

    public Question getQuestionById(int id) {
        return null;
    }

    public void updateQuestion(Question question) {
    }

    public void deleteQuestion(int id) {
    }

    public Answer addAnswer(Answer answer) {
        return null;
    }

    public List<Answer> getAnswerByQuestionId(int questionId) {
        return null;
    }

    public List<Question> generateQuestions() {
        return null;
    }
}
