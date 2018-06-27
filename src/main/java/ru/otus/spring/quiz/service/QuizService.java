package ru.otus.spring.quiz.service;

import ru.otus.spring.quiz.dao.QuestionDao;
import ru.otus.spring.quiz.domain.Question;

import java.util.List;

public class QuizService {
    private final QuestionDao questionDao;
    private final InteractionService interactionService;
    private final ReportService reportService;

    public QuizService(QuestionDao questionDao, InteractionService interactionService, ReportService reportService) {
        this.questionDao = questionDao;
        this.interactionService = interactionService;
        this.reportService = reportService;
    }

    public void startQuiz() {
        String studentName = interactionService.obtainStudentName();
        List<Question> questions = questionDao.randomSelect();
        interactionService.fillAnswers(questions);
        reportService.showReport(studentName, questions);
    }
}
