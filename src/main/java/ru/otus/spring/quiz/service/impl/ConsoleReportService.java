package ru.otus.spring.quiz.service.impl;

import ru.otus.spring.quiz.domain.Answer;
import ru.otus.spring.quiz.domain.Question;
import ru.otus.spring.quiz.service.MessageService;
import ru.otus.spring.quiz.service.ReportService;

import java.util.List;

public class ConsoleReportService implements ReportService {

    private final MessageService messageService;

    public ConsoleReportService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void showReport(String studentName, List<Question> answeredQuestions) {
        int numberOfCorrectAnswers = 0;
        for (Question question : answeredQuestions) {
            if (checkAnswers(question.getAnswers())) {
                numberOfCorrectAnswers++;
            }
        }
        System.out.println("==================================================");
        System.out.println(messageService.reportMessage(studentName, numberOfCorrectAnswers, answeredQuestions.size()));
        System.out.println("==================================================");
    }

    boolean checkAnswers(List<Answer> answers) {
        for (Answer answer : answers) {
            boolean isOk = (answer.isCorrect() == answer.isSelected());
            if (!isOk) {
                return false;
            }
        }
        return true;
    }
}
