package ru.otus.spring.quiz.dao;

import com.opencsv.CSVReader;
import lombok.NonNull;
import ru.otus.spring.quiz.domain.Answer;
import ru.otus.spring.quiz.domain.Question;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvQuestionDao implements QuestionDao {
    private final String sourceFile;

    public CsvQuestionDao(@NonNull String sourceFile) {
        this.sourceFile = sourceFile;
        if (!new File(sourceFile).exists()) {
            throw new IllegalArgumentException("The source file '" + sourceFile + "' must exist");
        }
    }

    @Override
    public List<Question> findAll() throws Exception {
        final List<Question> result = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(sourceFile));
        List<String[]> entries = reader.readAll();
        entries.forEach(entry -> {
            Question question = new Question(entry[0]);
            for (int i = 1; i < entry.length - 1; i = i + 2) {
                Answer answer = new Answer(entry[i], Boolean.valueOf(entry[i + 1]));
                question.addAnswer(answer);
            }
            result.add(question);
        });
        System.out.println("-----------------" + result.size());
        return result;
    }
}
