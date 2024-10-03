package com.kunal.exam_portal.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kunal.exam_portal.entity.Question;
import com.kunal.exam_portal.entity.Quiz;
import com.kunal.exam_portal.repository.QuestionRepository;
import com.kunal.exam_portal.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
       return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Set<Question> getAllQuestions() {
       return new LinkedHashSet<>(questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long questionId) {
        return questionRepository.findById(questionId).get();
    }

    @Override
    public Set<Question> getQuestionOfQuiz(Quiz quiz) {
        return new LinkedHashSet<>(questionRepository.findByQuiz(quiz));
    }

    @Override
    public void deleteQuestion(Long questionId) {
        Question question = new Question();
        question.setQuesId(questionId);
        questionRepository.delete(question);
    }

    @Override
    public Question getQuestionDetails(Long questionId) {
        return questionRepository.findById(questionId).get();
    }

}
