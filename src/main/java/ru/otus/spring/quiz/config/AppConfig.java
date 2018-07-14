package ru.otus.spring.quiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class AppConfig {

    private final AppProperties appProperties;

    @Autowired
    public AppConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public QuestionService fixedQuestionService(QuestionDao questionDao) {
        return new FixedQuestionService(questionDao, appProperties.getGeneral().getQuestionsPerQuiz());
    }

    @Bean
    public QuestionService randomQuestionService(QuestionDao questionDao) {
        return new RandomQuestionService(questionDao, appProperties.getGeneral().getQuestionsPerQuiz());
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
