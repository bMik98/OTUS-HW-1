package ru.otus.spring.quiz.service.impl;

import ru.otus.spring.quiz.domain.Answer;
import ru.otus.spring.quiz.domain.Question;
import ru.otus.spring.quiz.service.InteractionService;

import java.util.List;
import java.util.Scanner;

public class ConsoleInteractionService implements InteractionService {

    public String obtainStudentName() {
        String result = "";
        boolean confirmed = false;
        while ((result != null && result.isEmpty()) || !confirmed) {
            System.out.print("Enter your full name, please: ");
            Scanner scanner = new Scanner(System.in);
            result = scanner.nextLine();
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
        System.out.println("Type the id of the answer to select it or 'q' to finish and press Enter.");
        System.out.println("So, lets begin!");
        questions.forEach(this::ask);
    }

    private void ask(Question question) {
        boolean multiOption = (question.numberOfRightAnswers() > 1);
        showQuestionText(question, multiOption);
        showPossibleAnswers(question);
        makeSelection(question, multiOption);
    }

    private void showQuestionText(Question question, boolean multiOption) {
        System.out.println("--------------------");
        System.out.println(question.getText());
        if (multiOption) {
            System.out.println("(select all right answers)");
        } else {
            System.out.println("(select only one right answer)");
        }
    }

    private void showPossibleAnswers(Question question) {
        for (int i = 0; i < question.getAnswers().size(); i++) {
            Answer answer = question.getAnswers().get(i);
            String selected = (answer.isSelected()) ? "(selected) " : "";
            System.out.printf(" %d - %s%s %n", i, selected, answer.getText());
        }
    }

    private void makeSelection(Question question, boolean multiOption) {
        int maxValue = question.getAnswers().size();
        boolean done = false;
        while (!done) {
            System.out.print("Your choice: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input != null && !"".equals(input.trim())) {
                if ("q".equalsIgnoreCase(input)) {
                    done = getConfirmation("Transmit your answer? (y/n): ");
                } else {
                    Integer selectedIndex = getIndexOfAnswer(input, maxValue);
                    if (selectedIndex != null) {
                        boolean currentSelection = question.getAnswers().get(selectedIndex).isSelected();
                        question.getAnswers().get(selectedIndex).setSelected(!currentSelection);
                    }
                }
            }
        }
    }

    private Integer getIndexOfAnswer(String input, int maxValue) {
        Integer result;
        try {
            Integer value = Integer.valueOf(input);
            result = (value > 0 && value <= maxValue) ? value : null;
        } catch (NumberFormatException e) {
            result = null;
        }
        return result;
    }

    private boolean getConfirmation(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine();
        return "y".equalsIgnoreCase(confirmation);
    }
}
