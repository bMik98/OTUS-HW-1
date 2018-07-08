package ru.otus.spring.quiz.service.impl;

import ru.otus.spring.quiz.domain.Answer;
import ru.otus.spring.quiz.domain.Question;
import ru.otus.spring.quiz.service.InteractionService;

import java.util.List;
import java.util.Scanner;

public class ConsoleInteractionService implements InteractionService {

    public String obtainStudentName() {
        String result;
        boolean confirmed = false;
        do {
            System.out.print("Enter your full name, please: ");
            result = new Scanner(System.in).nextLine();
            if (result != null && !result.trim().isEmpty()) {
                confirmed = getConfirmation(String.format("Your name is %s?", result));
            }
        } while (!confirmed);
        return result;
    }

    public void fillAnswers(List<Question> questions) {
        System.out.printf("Given quiz consists %d questions. %n", questions.size());
        System.out.println("There are two kinds of questions: ");
        System.out.println(" 1. having one correct answer. In this case you have to select one option.");
        System.out.println(" 2. having the number of correct answers. To answer you have to select all of them.");
        System.out.println("Type the id of the answer and Enter to select it or nothing and Enter to finish.");
        System.out.println("So, lets begin!");
        questions.forEach(this::ask);
    }

    private void ask(Question question) {
        boolean multiOption = (question.numberOfRightAnswers() > 1);
        Integer selectedIndex;
        do {
            showQuestionText(question, multiOption);
            showPossibleAnswers(question);
            selectedIndex = makeSelection(question);
            inverseAnswerSelection(question, selectedIndex);
        } while (selectedIndex != null);
    }

    private void inverseAnswerSelection(Question question, Integer answerIndex) {
        if (answerIndex != null) {
            inverseAnswerSelection(question.getAnswers().get(answerIndex));
        }
    }

    private void inverseAnswerSelection(Answer answer) {
        boolean currentSelection = answer.isSelected();
        answer.setSelected(!currentSelection);
    }

    private void showQuestionText(Question question, boolean multiOption) {
        System.out.println("--------------------------------------------------");
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
            String selected = (answer.isSelected()) ? "[X]" : "[ ]";
            System.out.printf("%s %2d - %s %n", selected, i, answer.getText());
        }
    }

    private Integer makeSelection(Question question) {
        int maxValue = question.getAnswers().size() - 1;
        boolean done = false;
        Integer result = null;
        while (!done) {
            System.out.printf("Type the answer id (from 0 to %d) or nothing and press Enter: ", maxValue);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input != null && !"".equals(input.trim())) {
                result = getIndexOfAnswer(input, maxValue);
                done = (result != null);
            } else {
                done = getConfirmation("The answer to this question is completed?");
            }
        }
        return result;
    }

    private Integer getIndexOfAnswer(String input, int maxValue) {
        Integer result;
        try {
            Integer value = Integer.valueOf(input);
            result = (value < 0 || value > maxValue) ? null : value;
        } catch (NumberFormatException e) {
            result = null;
        }
        return result;
    }

    private boolean getConfirmation(String message) {
        System.out.print(message + " (y/n) [y]: ");
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine();
        if (confirmation == null || confirmation.isEmpty()) {
            return true;
        }
        return "y".equalsIgnoreCase(confirmation);
    }
}
