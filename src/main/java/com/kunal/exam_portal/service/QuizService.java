package com.kunal.exam_portal.service;

import java.util.Set;

import java.util.List;

import com.kunal.exam_portal.entity.Category;
import com.kunal.exam_portal.entity.Quiz;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);
    public Quiz updateQuiz(Quiz quiz);
    public Set<Quiz> getQuizes();
    public Quiz getQuiz(Long quizId);
    public void deleteQuiz(Long quizId);

    public List<Quiz> getQuizzesByCategory(Category category);

    public List<Quiz> getActiveQuizzes();

    public List<Quiz> getActiveQuizzesOfCategory(Category c);
}
