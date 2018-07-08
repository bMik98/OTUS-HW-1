package ru.otus.spring.quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.quiz.dao.CsvQuestionDao;
import ru.otus.spring.quiz.dao.QuestionDao;

@Configuration
public class DaoConfig {

    @Value("${questions-csv-file:src/main/resources/questions-db.csv}")
    private String sourceFile;

    @Bean
    public QuestionDao questionDao() {
        return new CsvQuestionDao(sourceFile);
    }
}
