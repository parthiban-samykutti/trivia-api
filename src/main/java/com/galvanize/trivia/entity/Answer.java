package com.galvanize.trivia.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue
    private int id;

    private int questionId;

}
