package ru.otus.spring.quiz.dao;

import com.opencsv.CSVReader;
import lombok.NonNull;
import org.springframework.core.io.ClassPathResource;
import ru.otus.spring.quiz.domain.Answer;
import ru.otus.spring.quiz.domain.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvQuestionDao implements QuestionDao {
    private final String sourceFile;

    public CsvQuestionDao(@NonNull String sourceFile) {
        this.sourceFile = sourceFile;
    }

    @Override
    public List<Question> findAll() throws Exception {
        final List<Question> result = new ArrayList<>();
        ClassPathResource classPathResource = new ClassPathResource(sourceFile);
        InputStream inputStream = classPathResource.getInputStream();
        CSVReader reader = new CSVReader(new InputStreamReader(inputStream, "UTF-8"));
        List<String[]> entries = reader.readAll();
        entries.forEach(entry -> {
            Question question = new Question(entry[0].trim());
            for (int i = 1; i < entry.length - 1; i = i + 2) {
                Answer answer = new Answer(entry[i].trim(), Boolean.valueOf(entry[i + 1].trim()));
                question.addAnswer(answer);
            }
            result.add(question);
        });
        return result;
    }
}
