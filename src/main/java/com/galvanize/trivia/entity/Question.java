package com.galvanize.trivia.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@Entity
public class Question {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "questionId")
    List<Answer> answers;
}
