package ru.otus.spring.quiz.service.impl;

import ru.otus.spring.quiz.domain.Answer;
import ru.otus.spring.quiz.domain.Question;
import ru.otus.spring.quiz.service.InteractionService;

import java.util.List;

public class ConsoleInteractionService implements InteractionService {

    public String obtainStudentName() {
        String result = "";
        boolean confirmed = false;
        while ((result != null && result.isEmpty()) || !confirmed) {
            System.out.print("Enter your full name, please: ");
            result = System.console().readLine();
            if (result != null && !"".equals(result.trim())) {
                confirmed = getConfirmation(String.format("Your name is %s? (y/n): ", result));
            }
        }
        return result;
    }

    public void fillAnswers(List<Question> questions) {
        System.out.printf("Given quiz consists %d questions. %n", questions.size());
        System.out.println("There are two kinds of questions: ");
        System.out.println(" 1. having one correct answer. In this case you have to select one option.");
        System.out.println(" 2. having the number of correct answers. To answer you have to select all of them.");
        System.out.println("To answer you should type the id or ids of the questions.");
        System.out.println("So, lets begin!");
        System.out.println("--------------------");
        questions.forEach(this::ask);
    }

    private void ask(Question question) {
        showQuestionTitle(question);
        showAnswerOptions(question);


    }

    private void showQuestionTitle(Question question) {
        System.out.println(question.getText());
        if (question.numberOfRightAnswers() > 1) {
            System.out.println("(select all right answers)");
        } else {
            System.out.println("(select only one right answer)");
        }
    }

    private void showAnswerOptions(Question question) {
        for (int i = 1; i <= question.getAnswers().size(); i++) {
            Answer answer = question.getAnswers().get(i);
            String selected = (answer.isSelected()) ? "(selected) " : "";
            System.out.printf(" %d - %s%s", i, selected, answer);
        }
    }

    private int makeSelection(int maxValue) {
        int result = -1;
        boolean done = false;
        while (!done) {
            System.out.print("Select: ");
            Integer selected = getInteger();
            if (selected != null && selected >= 0 && selected <= maxValue) {
                result = selected;
                done = true;
            }
        }
        return result;
    }

    private Integer getInteger() {
        String input = System.console().readLine();
        if (input == null || "".equals(input.trim())) {
            return null;
        }
        Integer result;
        try {
            result = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            result = null;
        }
        return result;
    }

    private boolean getConfirmation(String message) {
        System.out.print(message);
        String confirmation = System.console().readLine();
        return confirmation.equalsIgnoreCase("y");
    }
}
