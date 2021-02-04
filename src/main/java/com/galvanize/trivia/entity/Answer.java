package com.galvanize.trivia.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Answer {
    private int id;
    private int questionId;
}
