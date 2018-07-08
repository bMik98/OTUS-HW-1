package ru.otus.spring.quiz.service.impl;

import lombok.NonNull;
import ru.otus.spring.quiz.dao.QuestionDao;
import ru.otus.spring.quiz.service.QuestionService;

public abstract class AbstractQuestionService implements QuestionService {
    protected final QuestionDao questionDao;
    protected final int questionsPerSession;

    public AbstractQuestionService(QuestionDao questionDao, int questionsPerSession) {
        this.questionDao = questionDao;
        this.questionsPerSession = questionsPerSession;
        if (questionsPerSession < 1) {
            throw new IllegalArgumentException("The number of the questions per session is less than 1");
        }
    }
}
