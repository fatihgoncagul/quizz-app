package com.example.quizzapp.controller;

import com.example.quizzapp.model.Question;
import com.example.quizzapp.model.QuestionWrapper;
import com.example.quizzapp.model.Response;
import com.example.quizzapp.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String difficultylevel, @RequestParam int num,@RequestParam String title ){

        return quizService.createQuiz(difficultylevel,num,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
       return quizService.getQuizQuestions(id);

    }
    //cliewnt will send quiz id, question id and response of the qeustion

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses ){
        return quizService.calculateResult(id,responses );



    }


}
