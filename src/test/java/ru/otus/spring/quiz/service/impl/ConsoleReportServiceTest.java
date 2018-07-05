package ru.otus.spring.quiz.service.impl;

import org.junit.Before;
import org.junit.Test;
import ru.otus.spring.quiz.domain.Answer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConsoleReportServiceTest {

    private ConsoleReportService reportService;

    @Before
    public void setUp() {
        reportService = new ConsoleReportService();
    }

    @Test
    public void checkCorrectSelectedAnswers() {
        List<Answer> list;
        list = Collections.singletonList(createAnswer(true, true));
        assertTrue(reportService.checkAnswers(list));
        list = Arrays.asList(
                createAnswer(true, true),
                createAnswer(true, true)
        );
        assertTrue(reportService.checkAnswers(list));
    }

    @Test
    public void checkCorrectUnselectedAnswers() {
        List<Answer> list;
        list = Collections.singletonList(createAnswer(true, false));
        assertFalse(reportService.checkAnswers(list));
        list = Arrays.asList(
                createAnswer(true, false),
                createAnswer(true, false)
        );
        assertFalse(reportService.checkAnswers(list));
    }

    @Test
    public void checkIncorrectSelectedAnswers() {
        List<Answer> list;
        list = Collections.singletonList(createAnswer(false, true));
        assertFalse(reportService.checkAnswers(list));
        list = Arrays.asList(
                createAnswer(false, true),
                createAnswer(false, true)
        );
        assertFalse(reportService.checkAnswers(list));
    }

    @Test
    public void checkIncorrectUnselectedAnswers() {
        List<Answer> list;
        list = Collections.singletonList(createAnswer(false, false));
        assertTrue(reportService.checkAnswers(list));
        list = Arrays.asList(
                createAnswer(false, false),
                createAnswer(false, false)
        );
        assertTrue(reportService.checkAnswers(list));
    }

    @Test
    public void checkTwoAnswers() {
        List<Answer> list;
        list = Arrays.asList(
                createAnswer(false, false),
                createAnswer(true, true)
        );
        assertTrue(reportService.checkAnswers(list));
        list = Arrays.asList(
                createAnswer(true, false),
                createAnswer(true, true)
        );
        assertFalse(reportService.checkAnswers(list));
        list = Arrays.asList(
                createAnswer(false, false),
                createAnswer(true, false)
        );
        assertFalse(reportService.checkAnswers(list));
        list = Arrays.asList(
                createAnswer(true, false),
                createAnswer(false, false)
        );
        assertFalse(reportService.checkAnswers(list));
    }

    private Answer createAnswer(boolean correct, boolean selected) {
        Answer result = new Answer("answer", correct);
        result.setSelected(selected);
        return result;
    }
}