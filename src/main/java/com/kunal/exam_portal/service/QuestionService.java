package com.kunal.exam_portal.service;

import java.util.Set;

import com.kunal.exam_portal.entity.Question;
import com.kunal.exam_portal.entity.Quiz;

public interface QuestionService {

    public Question addQuestion(Question question);
    public Question updateQuestion(Question question);
    public Set<Question> getAllQuestions();
    public Question getQuestion(Long questionId);
    public Set<Question> getQuestionOfQuiz(Quiz quiz);
    public void deleteQuestion(Long questionId);

    public Question getQuestionDetails(Long questionId);

}
