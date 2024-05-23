package com.example.quizzapp.service;

import com.example.quizzapp.dao.QuestionDao;
import com.example.quizzapp.model.Question;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionDao questionDao;

    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getAllQuestions() {

        return  questionDao.findAll();
    }

    public List<Question> getQuestionsByDiffLevel(String difficultylevel) {
        return questionDao.findByDifficultylevel(difficultylevel);
    }


    public String addQuestion(Question question) {
         questionDao.save(question);
         return "succes";
    }
}
