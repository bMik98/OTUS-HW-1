package ru.otus.spring.quiz.service;

import ru.otus.spring.quiz.domain.Question;

import java.util.List;

public interface ReportService {

    void showReport(String studentName, List<Question> answeredQuestions);
}
