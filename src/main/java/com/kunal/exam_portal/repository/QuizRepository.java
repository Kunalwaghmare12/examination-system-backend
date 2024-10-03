package com.kunal.exam_portal.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kunal.exam_portal.entity.Category;
import com.kunal.exam_portal.entity.Quiz;
@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long>{
    public List<Quiz> findBycategory(Category category);

    public List<Quiz> findByActive(boolean b);
    public List<Quiz> findByCategoryAndActive(Category c,boolean b);

}
