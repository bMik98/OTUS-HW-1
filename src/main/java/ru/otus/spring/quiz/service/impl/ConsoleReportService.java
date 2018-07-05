package ru.otus.spring.quiz.service.impl;

import ru.otus.spring.quiz.domain.Answer;
import ru.otus.spring.quiz.domain.Question;
import ru.otus.spring.quiz.service.ReportService;

import java.util.List;

public class ConsoleReportService implements ReportService {
    public void showReport(String studentName, List<Question> answeredQuestions) {
        int numberOfCorrectAnswers = 0;
        for (Question question : answeredQuestions) {
            if (checkAnswers(question.getAnswers())) {
                numberOfCorrectAnswers++;
            }
        }
        System.out.println("==================================================");
        System.out.printf("%s correctly answered %d question(s) out of %d %n",
                studentName, numberOfCorrectAnswers, answeredQuestions.size());
        System.out.println("==================================================");
    }

    private boolean checkAnswers(List<Answer> answers) {
        for (Answer answer : answers) {
            boolean isOk = (answer.isCorrect() == answer.isSelected());
            if (!isOk) {
                return false;
            }
        }
        return true;
    }
}
