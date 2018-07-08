package ru.otus.spring.quiz.service.impl;

import ru.otus.spring.quiz.dao.QuestionDao;
import ru.otus.spring.quiz.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class FixedQuestionService extends AbstractQuestionService {

    public FixedQuestionService(QuestionDao questionDao, int questionsPerSession) {
        super(questionDao, questionsPerSession);
    }

    @Override
    public List<Question> generate() {
        List<Question> result = new ArrayList<>();
        try {
            List<Question> dbQuestions = questionDao.findAll();
            for (int i = 0; i < questionsPerSession; i++) {
                result.add(dbQuestions.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
