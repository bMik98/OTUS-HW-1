package ru.otus.spring.quiz.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.quiz.dao.CsvQuestionDao;
import ru.otus.spring.quiz.dao.QuestionDao;

import java.util.Locale;

@Configuration
public class DaoConfig {

    @Bean
    public QuestionDao questionDao(MessageSource settings, Locale locale) {
        return new CsvQuestionDao(settings.getMessage("questions-csv-file", new Object[]{}, locale));
    }
}
