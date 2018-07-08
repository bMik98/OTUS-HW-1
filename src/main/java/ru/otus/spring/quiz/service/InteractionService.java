package ru.otus.spring.quiz.service;

import ru.otus.spring.quiz.domain.Question;

import java.util.List;

public interface InteractionService {
    String obtainStudentName();

    void fillAnswers(List<Question> questions);
}
