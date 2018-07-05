package ru.otus.spring.quiz.dao;

import org.junit.Before;
import org.junit.Test;
import ru.otus.spring.quiz.domain.Answer;
import ru.otus.spring.quiz.domain.Question;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CsvQuestionDaoTest {

    private CsvQuestionDao dao;

    @Before
    public void setUp() {
        String file = this.getClass().getClassLoader().getResource("test-questions-db.csv").getFile();
        dao = new CsvQuestionDao(file);
    }

    @Test
    public void findAll() throws Exception {
        List<Question> list = dao.findAll();
        assertEquals(6, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertEquals("Q " + i, list.get(i).getText());
            List<Answer> answers = list.get(i).getAnswers();
            for (int j = 0; j < answers.size(); j++) {
                assertEquals("A " + i + j, answers.get(j).getText());
                assertFalse(answers.get(j).isSelected());
            }
        }
        assertEquals(2, list.get(0).getAnswers().size());
        assertEquals(2, list.get(1).getAnswers().size());
        assertEquals(3, list.get(2).getAnswers().size());
        assertEquals(3, list.get(3).getAnswers().size());
        assertEquals(4, list.get(4).getAnswers().size());
        assertEquals(4, list.get(5).getAnswers().size());

        assertFalse(list.get(0).getAnswers().get(0).isCorrect());
        assertTrue(list.get(0).getAnswers().get(1).isCorrect());

        assertTrue(list.get(1).getAnswers().get(0).isCorrect());
        assertFalse(list.get(1).getAnswers().get(1).isCorrect());

        assertFalse(list.get(2).getAnswers().get(0).isCorrect());
        assertTrue(list.get(2).getAnswers().get(1).isCorrect());
        assertFalse(list.get(2).getAnswers().get(2).isCorrect());

        assertTrue(list.get(3).getAnswers().get(0).isCorrect());
        assertFalse(list.get(3).getAnswers().get(1).isCorrect());
        assertTrue(list.get(3).getAnswers().get(2).isCorrect());

        assertFalse(list.get(4).getAnswers().get(0).isCorrect());
        assertFalse(list.get(4).getAnswers().get(1).isCorrect());
        assertFalse(list.get(4).getAnswers().get(2).isCorrect());
        assertTrue(list.get(4).getAnswers().get(3).isCorrect());

        assertTrue(list.get(5).getAnswers().get(0).isCorrect());
        assertTrue(list.get(5).getAnswers().get(1).isCorrect());
        assertTrue(list.get(5).getAnswers().get(2).isCorrect());
        assertFalse(list.get(5).getAnswers().get(3).isCorrect());
    }
}