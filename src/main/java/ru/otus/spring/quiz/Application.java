package ru.otus.spring.quiz;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.spring.quiz.service.QuizService;

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        new Application().run();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    private void run() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Application.class);
        context.refresh();
        QuizService quizService = context.getBean(QuizService.class);
        quizService.startQuiz();
    }
}
