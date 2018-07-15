package ru.otus.spring.quiz.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties
@Getter
@Setter
public class AppProperties {

    private General general;
    private Map<String, String> questionFiles = new HashMap<>();

    @Getter
    @Setter
    public static class General {
        private int questionsPerQuiz;
        private String applicationLanguage;
    }
}
