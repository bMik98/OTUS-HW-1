package ru.otus.spring.quiz.dao;

import ru.otus.spring.quiz.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> randomSelect();
}