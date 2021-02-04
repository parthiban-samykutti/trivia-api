package com.galvanize.trivia.entity.Question;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Question {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int id;
    private String name;
}
