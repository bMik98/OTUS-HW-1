package ru.otus.spring.quiz.service.impl;

import lombok.NonNull;
import ru.otus.spring.quiz.dao.QuestionDao;
import ru.otus.spring.quiz.domain.Question;

import java.util.Collections;
import java.util.List;

public class RandomQuestionService extends AbstractQuestionService {

    public RandomQuestionService(@NonNull QuestionDao questionDao, int questionsPerSession) {
        super(questionDao, questionsPerSession);
    }

    @Override
    public List<Question> generate() {
        return Collections.emptyList();
    }
}
