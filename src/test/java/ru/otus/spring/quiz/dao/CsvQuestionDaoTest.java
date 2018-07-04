package ru.otus.spring.quiz.dao;

import org.junit.Before;
import org.junit.Test;
import ru.otus.spring.quiz.domain.Question;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CsvQuestionDaoTest {

    private CsvQuestionDao dao;

    @Before
    public void setUp() {
        String file = this.getClass().getClassLoader().getResource("questions-db.csv").getFile();
        dao = new CsvQuestionDao(file);
    }

    @Test
    public void findAll() throws Exception {
        List<Question> list = dao.findAll();
        assertEquals(10, list.size());
    }
}