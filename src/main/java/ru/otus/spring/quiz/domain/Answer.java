package ru.otus.spring.quiz.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
    private final String text;
    private final boolean correct;
    private boolean selected = false;

    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }
}
