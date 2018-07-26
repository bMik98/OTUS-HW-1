package ru.otus.spring.quiz.shell;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.quiz.service.QuizService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class QuizCommandsTest {

    private QuizCommands quizCommands;
    private QuizService quizService;

    @Before
    public void setUp() throws Exception {
        quizService = mock(QuizService.class);
        quizCommands = new QuizCommands(quizService);
    }

    @Test
    public void start() {
        quizCommands.start();
        verify(quizService, times(1)).startQuiz();
    }

    @Test
    public void begin() {
        String studentName = "TEST_STUDENT_NAME";
        quizCommands.begin(studentName);
        verify(quizService, times(1)).startQuiz(studentName);
    }
}