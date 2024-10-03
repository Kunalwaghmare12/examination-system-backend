package com.kunal.exam_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kunal.exam_portal.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
