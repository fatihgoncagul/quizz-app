package com.example.quizzapp.service;

import com.example.quizzapp.dao.QuestionDao;
import com.example.quizzapp.dao.QuizDao;
import com.example.quizzapp.model.Question;
import com.example.quizzapp.model.QuestionWrapper;
import com.example.quizzapp.model.Quiz;
import com.example.quizzapp.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizDao quizDao;

    private final QuestionDao questionDao;

    public QuizService(QuizDao quizDao, QuestionDao questionDao) {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
    }



    public ResponseEntity<String> createQuiz(String difficultylevel, int num, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByDifficultylevel(difficultylevel,num);


        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Succes", HttpStatus.CREATED);


    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());

            questionsForUser.add(qw);
        }


        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();

        List<Question> questions = quiz.getQuestions();
        
        int right = 0;
        int i =0;
        for (Response response : responses){

            if (response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;


        }
        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}
