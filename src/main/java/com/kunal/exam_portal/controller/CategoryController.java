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

import com.kunal.exam_portal.entity.Category;
import com.kunal.exam_portal.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //add category
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category category1= categoryService.addCategory(category);
        return ResponseEntity.ok(category1);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Long categoryId) throws Exception{
        Category category = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategories(){

        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PutMapping("/")
    public ResponseEntity<Category> updateCategory(@RequestBody Category updatedCategory){
        return ResponseEntity.ok(categoryService.updateCategory(updatedCategory));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId")Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("user deleted sucessfully");
    }


}
