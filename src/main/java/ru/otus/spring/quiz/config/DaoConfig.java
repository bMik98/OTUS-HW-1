package ru.otus.spring.quiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.quiz.dao.CsvQuestionDao;
import ru.otus.spring.quiz.dao.QuestionDao;

@Configuration
public class DaoConfig {

    private final AppProperties appProperties;

    @Autowired
    public DaoConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public QuestionDao questionDao() {
        String selectedLanguage = appProperties.getGeneral().getApplicationLanguage();
        String file = appProperties.getQuestionFiles().get(selectedLanguage);
        return new CsvQuestionDao(file);
    }
}
