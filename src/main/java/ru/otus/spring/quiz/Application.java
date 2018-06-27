package ru.otus.spring.quiz;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.quiz.service.QuizService;

public class Application {

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizService quizService = context.getBean(QuizService.class);
        quizService.startQuiz();
        System.out.println("Finish");
    }
}
