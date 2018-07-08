package ru.otus.spring.quiz.service;

import ru.otus.spring.quiz.domain.Question;

import java.util.List;

public class QuizService {
    private final QuestionService questionService;
    private final InteractionService interactionService;
    private final ReportService reportService;

    public QuizService(QuestionService questionService, InteractionService interactionService, ReportService reportService) {
        this.questionService = questionService;
        this.interactionService = interactionService;
        this.reportService = reportService;
    }

    public void startQuiz() {
        String studentName = interactionService.obtainStudentName();
        List<Question> questions = questionService.generate();
        interactionService.fillAnswers(questions);
        reportService.showReport(studentName, questions);
    }
}
