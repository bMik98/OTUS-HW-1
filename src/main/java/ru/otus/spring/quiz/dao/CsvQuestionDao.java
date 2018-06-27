package ru.otus.spring.quiz.dao;

import lombok.NonNull;
import ru.otus.spring.quiz.domain.Question;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class CsvQuestionDao implements QuestionDao {
    private final int questionsPerSession;
    private final String sourceFile;

    public CsvQuestionDao(int questionsPerSession, @NonNull String sourceFile) {
        this.questionsPerSession = questionsPerSession;
        if(questionsPerSession < 1) {
            throw new IllegalArgumentException("The number of the questions per session is less than 1");
        }
        this.sourceFile = sourceFile;
        if(new File(sourceFile).exists()) {
            throw new IllegalArgumentException("The source file '" + sourceFile + "' must exist");
        }
    }

    public List<Question> randomSelect() {
        return Collections.emptyList();
    }
}
