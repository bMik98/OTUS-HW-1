package ru.otus.spring.quiz.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.verification.VerificationMode;
import ru.otus.spring.quiz.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class QuizServiceTest {
    private static final String STUDENT_NAME = "NO_MATTERS";
    private static final VerificationMode ONCE = times(1);

    private QuestionService questionService;
    private InteractionService interactionService;
    private ReportService reportService;

    private QuizService quizService;

    @Before
    public void setUp() throws Exception {
        questionService = mock(QuestionService.class);
        interactionService = mock(InteractionService.class);
        reportService = mock(ReportService.class);
        quizService = new QuizService(questionService, interactionService, reportService);
    }

    @Test
    public void startQuiz() {
        quizService.startQuiz();
        verify(interactionService, ONCE).obtainStudentName();
    }

    @Test
    public void startQuizWithStudentName() {
        List<Question> questions = new ArrayList<>();
        when(questionService.generate()).thenReturn(questions);
        quizService.startQuiz(STUDENT_NAME);
        verify(questionService,ONCE).generate();
        verify(interactionService, ONCE).fillAnswers(questions);
        verify(reportService, ONCE).showReport(STUDENT_NAME, questions);
    }
}