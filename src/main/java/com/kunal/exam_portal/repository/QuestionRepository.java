package com.kunal.exam_portal.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kunal.exam_portal.entity.Question;
import com.kunal.exam_portal.entity.Quiz;
@Repository
public interface QuestionRepository extends JpaRepository<Question,Long>{
    public Set<Question> findByQuiz(Quiz quiz);
}
