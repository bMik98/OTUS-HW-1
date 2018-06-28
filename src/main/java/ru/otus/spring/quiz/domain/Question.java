package ru.otus.spring.quiz.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Question {
    private String text;
    private List<Answer> answers;

    public int numberOfRightAnswers() {
        return (int) answers.stream().filter(Answer::isCorrect).count();
    }
}
