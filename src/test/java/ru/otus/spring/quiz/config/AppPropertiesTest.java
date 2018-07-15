package ru.otus.spring.quiz.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties(value = AppProperties.class)
@ContextConfiguration(classes = AppProperties.class)
public class AppPropertiesTest {

    private static final String SELECTED_LANGUAGE = "en";
    private static final int QUESTIONS_PER_QUIZ = 5;
    private static final int NUMBER_OF_LANGUAGES = 2;

    @Autowired
    private AppProperties appProperties;

    @Test
    public void checkConditions() {
        assertNotNull(appProperties);
    }

    @Test
    public void getGeneral() {
        assertNotNull(appProperties.getGeneral());
        assertEquals(QUESTIONS_PER_QUIZ, appProperties.getGeneral().getQuestionsPerQuiz());
        assertEquals(SELECTED_LANGUAGE, appProperties.getGeneral().getApplicationLanguage());
    }

    @Test
    public void getQuestionFiles() {
        Map<String, String> questionFiles = appProperties.getQuestionFiles();
        assertNotNull(questionFiles);
        assertEquals(NUMBER_OF_LANGUAGES, questionFiles.size());
        questionFiles.values().forEach((path) -> {
            assertNotNull(path);
            assertFalse(path.isEmpty());
        });
    }
}