package ru.otus.spring.quiz.dao;

import lombok.NonNull;
import ru.otus.spring.quiz.domain.Question;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class CsvQuestionDao implements QuestionDao {
    private final String sourceFile;

    public CsvQuestionDao(@NonNull String sourceFile) {
        this.sourceFile = sourceFile;
        if (new File(sourceFile).exists()) {
            throw new IllegalArgumentException("The source file '" + sourceFile + "' must exist");
        }
    }

    @Override
    public List<Question> findAll() {
        return Collections.emptyList();
    }
}
