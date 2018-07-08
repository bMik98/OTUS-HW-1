package ru.otus.spring.quiz.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {
    private final MessageSource messageSource;
    private Locale locale;

    public MessageService(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    public String namePrompt() {
        return noArgsMessage("name-prompt");
    }

    public String nameConfirm(String name) {
        return messageSource.getMessage("name-confirm", new String[]{name}, locale);
    }

    public String startExplanation(Integer numberOfQuestions) {
        return messageSource.getMessage("start-explanation", new Object[]{numberOfQuestions}, locale);
    }

    public String selectOne() {
        return noArgsMessage("select-one");
    }

    public String selectMulti() {
        return noArgsMessage("select-multi");
    }

    public String answerPrompt(Integer maxAnswerIndex) {
        return messageSource.getMessage("answer-prompt", new Object[]{maxAnswerIndex}, locale);
    }

    public String answerConfirm() {
        return noArgsMessage("answer-confirm");
    }

    public String reportMessage(String name, Integer correctlyAnswered, Integer totalQuestions) {
        return message("report-message", name, correctlyAnswered, totalQuestions);
    }

    public String yes() {
        return noArgsMessage("yes");
    }

    public String no() {
        return noArgsMessage("no");
    }

    private String noArgsMessage(String code) {
        return messageSource.getMessage(code, new Object[]{}, locale);
    }

    private String message(String code, Object... replacements) {
        return messageSource.getMessage(code, replacements, locale);
    }
}
