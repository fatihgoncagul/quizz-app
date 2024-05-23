package com.example.quizzapp.controller;

import com.example.quizzapp.model.Question;
import com.example.quizzapp.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {

        return questionService.getAllQuestions();
    }

    @GetMapping("difficultylevel/{difficultylevel}")
    public ResponseEntity<List<Question>> getQuestionsByDiffLevel(@PathVariable String difficultylevel) {

        return questionService.getQuestionsByDiffLevel(difficultylevel);

    }
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);


    }
    /*
    *
    *
*
{
"id": 2,
"questionTitle": "Which keyword is used to inherit a class in Java?",
"option1": "extends",
"option2": "implements",
"option3": "inherits",
"option4": "creates",
"rightAnswer": "extends",
"difficultylevel": "Medium"
}
*
    *
    * */

}
