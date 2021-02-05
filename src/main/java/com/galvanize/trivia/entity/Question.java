package com.galvanize.trivia.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Question {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int id;
    private String name;
    List<Answer> answers;
}
