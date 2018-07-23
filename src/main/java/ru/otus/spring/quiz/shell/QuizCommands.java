package ru.otus.spring.quiz.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.quiz.service.QuizService;

import javax.validation.constraints.Size;

@ShellComponent
public class QuizCommands {
    private final QuizService service;

    @Autowired
    public QuizCommands(QuizService service) {
        this.service = service;
    }

    @ShellMethod("Start a quiz")
    public void start() {
        service.startQuiz();
    }

    @ShellMethod("Start a quiz for a defined student")
    public void begin(@ShellOption @Size(min = 1) String studentName) {
        service.startQuiz(studentName);
    }
}
