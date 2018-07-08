package ru.otus.spring.quiz.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Question {
    private final String text;
    private final List<Answer> answers = new ArrayList<>();

    public Question(String text) {
        this.text = text;
    }

    public int numberOfRightAnswers() {
        return (int) answers.stream().filter(Answer::isCorrect).count();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }
}
