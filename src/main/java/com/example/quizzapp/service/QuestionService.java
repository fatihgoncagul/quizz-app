package com.example.quizzapp.service;

import com.example.quizzapp.dao.QuestionDao;
import com.example.quizzapp.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionDao questionDao;

    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public ResponseEntity<List<Question>> getAllQuestions() {

        try {
            return  new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByDiffLevel(String difficultylevel) {



       try {
           return new ResponseEntity<>(questionDao.findByDifficultylevel(difficultylevel),HttpStatus.OK);
       }catch (Exception e){
           e.printStackTrace();
       }
       return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
    }


    public ResponseEntity<String> addQuestion(Question question) {

        try {
            questionDao.save(question);
            return new ResponseEntity<>("Succes",HttpStatus.CREATED);//201
        }catch (Exception e){
            e.printStackTrace();

        }
        return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);


    }
}
