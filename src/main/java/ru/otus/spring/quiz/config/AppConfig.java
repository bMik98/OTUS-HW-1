package ru.otus.spring.quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.quiz.dao.QuestionDao;
import ru.otus.spring.quiz.service.InteractionService;
import ru.otus.spring.quiz.service.MessageService;
import ru.otus.spring.quiz.service.QuestionService;
import ru.otus.spring.quiz.service.ReportService;
import ru.otus.spring.quiz.service.impl.ConsoleInteractionService;
import ru.otus.spring.quiz.service.impl.ConsoleReportService;
import ru.otus.spring.quiz.service.impl.FixedQuestionService;
import ru.otus.spring.quiz.service.impl.RandomQuestionService;

@Configuration
@PropertySource("classpath:/application.properties")
public class AppConfig {

    @Value("${questions-per-quiz}")
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
    public InteractionService interactionService(MessageService messageService) {
        return new ConsoleInteractionService(messageService);
    }

    @Bean
    public ReportService reportService(MessageService messageService) {
        return new ConsoleReportService(messageService);
    }
}
