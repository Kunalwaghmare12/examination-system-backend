package com.kunal.exam_portal.controller;

import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.ArrayList;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kunal.exam_portal.entity.Question;
import com.kunal.exam_portal.entity.Quiz;
import com.kunal.exam_portal.service.QuestionService;
import com.kunal.exam_portal.service.QuizService;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return ResponseEntity.ok(questionService.addQuestion(question));
    }

    
    @GetMapping("/{quesId}")
    public ResponseEntity<Question> getQuestion(@PathVariable("quesId") Long quesId){
        return ResponseEntity.ok(questionService.getQuestion(quesId));
    }

    
    @GetMapping("/")
    public ResponseEntity<?> getAllQuestion(){
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/quiz/{qId}")
    public ResponseEntity<?> getAllQuestionOfQuiz(@PathVariable("quizId") Long quizId){
      // getting all thee quiz questions
        // Quiz quiz=new Quiz();
        // quiz.setQId(qId);
        // return ResponseEntity.ok(questionService.getQuestionOfQuiz(quiz));

        Quiz quiz = quizService.getQuiz(quizId);
        Set<Question> question = quiz.getQuestion();

        List<Question> questionlist=new ArrayList<>(question);
        int questionlimit = Integer.parseInt(quiz.getNumberOfQuestion());
        List<Question> limitedQuestion=questionlist.stream().limit(questionlimit).collect(Collectors.toList());
        Collections.shuffle(limitedQuestion);
        return ResponseEntity.ok(limitedQuestion);
        
    }


    
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(Question question){
        return ResponseEntity.ok(questionService.updateQuestion(question));
    }

    @DeleteMapping("/{quesId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("quesId") Long quesId){
        questionService.deleteQuestion(quesId);
        return ResponseEntity.ok("question deleted Sucessfully..");
    }


    // evaluating quiz

    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
        double marksGot =0;
        int correctAnswers=0;
        int attempted =0;
        for(Question q:questions){
            Question question= questionService.getQuestionDetails(q.getQuesId());
            if(question.getAnswer().equals(q.getGivenAnswer())){
                correctAnswers++;
                double marksSingle= Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
                marksGot=marksGot+marksSingle;
            }
            if(q.getGivenAnswer().equals("")|| q.getGivenAnswer()==null){
                attempted++;
            }

        }

        Map<String,Object> map =Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
        return ResponseEntity.ok(map);
    }





}
