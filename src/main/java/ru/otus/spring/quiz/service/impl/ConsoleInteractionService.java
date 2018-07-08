package ru.otus.spring.quiz.service.impl;

import ru.otus.spring.quiz.domain.Answer;
import ru.otus.spring.quiz.domain.Question;
import ru.otus.spring.quiz.service.InteractionService;
import ru.otus.spring.quiz.service.MessageService;

import java.util.List;
import java.util.Scanner;

public class ConsoleInteractionService implements InteractionService {
    private final MessageService messageService;

    public ConsoleInteractionService(MessageService messageService) {
        this.messageService = messageService;
    }

    public String obtainStudentName() {
        String result;
        boolean confirmed = false;
        do {
            System.out.print(messageService.namePrompt());
            result = new Scanner(System.in).nextLine();
            if (result != null && !result.trim().isEmpty()) {
                confirmed = getConfirmation(messageService.nameConfirm(result));
            }
        } while (!confirmed);
        return result;
    }

    public void fillAnswers(List<Question> questions) {
        System.out.println(messageService.startExplanation(questions.size()));
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
            System.out.println(messageService.selectMulti());
        } else {
            System.out.println(messageService.selectOne());
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
            System.out.print(messageService.answerPrompt(maxValue));
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                result = getIndexOfAnswer(input, maxValue);
                done = (result != null);
            } else {
                done = getConfirmation(messageService.answerConfirm());
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
        System.out.printf(message + " (1-%s / 0-%s) [1]: ", messageService.yes(), messageService.no());
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine();
        if (confirmation == null || confirmation.isEmpty()) {
            return true;
        }
        return "1".equalsIgnoreCase(confirmation);
    }
}
