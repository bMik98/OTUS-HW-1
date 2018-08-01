package ru.otus.spring.quiz.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.spring.quiz.domain.Question;

import java.util.List;

@Service
public class QuizService {
    private final QuestionService questionService;
    private final InteractionService interactionService;
    private final ReportService reportService;

    public QuizService(@Qualifier("fixedQuestionService") QuestionService questionService,
                       InteractionService interactionService,
                       ReportService reportService) {
        this.questionService = questionService;
        this.interactionService = interactionService;
        this.reportService = reportService;
    }

    public void startQuiz() {
        String studentName = interactionService.obtainStudentName();
        startQuiz(studentName);
    }

    public void startQuiz(String studentName) {
        List<Question> questions = questionService.generate();
        interactionService.fillAnswers(questions);
        reportService.showReport(studentName, questions);
    }
}
