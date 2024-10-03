package com.kunal.exam_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.kunal.exam_portal.entity.Category;
import com.kunal.exam_portal.entity.Question;
import com.kunal.exam_portal.entity.Quiz;
import com.kunal.exam_portal.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(quizService.addQuiz(quiz));
    }

    @GetMapping("/{qId}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable ("qId")Long qId){
        return ResponseEntity.ok(quizService.getQuiz(qId));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllQuiz(){
        return ResponseEntity.ok(quizService.getQuizes());
    }
    
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz updatedQuiz){
        return ResponseEntity.ok(quizService.updateQuiz(updatedQuiz));
    }

    @DeleteMapping("/{qId}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long qId ){
        quizService.deleteQuiz(qId);
        return ResponseEntity.ok("Quiz has been deleted sucessfully");
    }

    @GetMapping("/category/{cid}")
    public ResponseEntity<?> getQuizzesByCategory(@PathVariable("cid") Long cid){
        Category category = new Category();
        category.setCId(cid);
        return ResponseEntity.ok(quizService.getQuizzesByCategory(category));

    }

    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes(){
        return quizService.getActiveQuizzes();
    }

    @GetMapping("/category/active/{cid}")
    public List<Quiz> getActiveQuizzesOfCategory(@PathVariable("cid") Long cid){
        Category c=new Category();
        c.setCId(cid);
        return quizService.getActiveQuizzesOfCategory(c);
    }


    

}
