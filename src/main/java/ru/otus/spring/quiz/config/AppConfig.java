package ru.otus.spring.quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.quiz.dao.QuestionDao;
import ru.otus.spring.quiz.service.InteractionService;
import ru.otus.spring.quiz.service.QuestionService;
import ru.otus.spring.quiz.service.ReportService;
import ru.otus.spring.quiz.service.impl.ConsoleInteractionService;
import ru.otus.spring.quiz.service.impl.ConsoleReportService;
import ru.otus.spring.quiz.service.impl.FixedQuestionService;
import ru.otus.spring.quiz.service.impl.RandomQuestionService;

@Configuration
public class AppConfig {

    @Value("${questions-csv-file:5}")
    private int questionsPerSession;

    @Bean
    public QuestionService fixedQuestionService(QuestionDao questionDao) {
        return new FixedQuestionService(questionDao, questionsPerSession);
    }

    @Bean
    public QuestionService randomQuestionService(QuestionDao questionDao) {
        return new RandomQuestionService(questionDao, questionsPerSession);
    }

    @Bean
    public InteractionService interactionService() {
        return new ConsoleInteractionService();
    }

    @Bean
    public ReportService reportService() {
        return new ConsoleReportService();
    }
}
