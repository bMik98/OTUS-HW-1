package ru.otus.spring.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring.quiz.service.QuizService;

@SpringBootApplication
@EnableConfigurationProperties
public class Application implements CommandLineRunner {

    private final QuizService quizService;

    @Autowired
    public Application(QuizService quizService) {
        this.quizService = quizService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        quizService.startQuiz();
    }
}
