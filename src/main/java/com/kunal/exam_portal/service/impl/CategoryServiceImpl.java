package com.kunal.exam_portal.service.impl;

import java.util.LinkedHashSet;
// import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kunal.exam_portal.entity.Category;
// import com.kunal.exam_portal.helper.UserNotFoundException;
import com.kunal.exam_portal.repository.CategoryRepository;
import com.kunal.exam_portal.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired 
    CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    // @Override
    // public Category updateCategory(Long categoryId,Category updatedCategory) throws Exception {
    //     // Optional<Category> foundCategory= categoryRepository.findById(categoryId);
    //     // if((!foundCategory.isPresent())){
    //     //     throw new UserNotFoundException();
    //     // }
    //     // Category newCategory=foundCategory.get();
    //     //     newCategory.setTitle(updatedCategory.getTitle());
    //     //     newCategory.setDecription(updatedCategory.getDecription());
    //     //     newCategory.setQuizes(updatedCategory.getQuizes());
    //         return categoryRepository.save(newCategory);
    // }
    

    @Override
    public Set<Category> getCategories() {
        return new LinkedHashSet<>(categoryRepository.findAll());
    }

    @Override
    public Category getCategory(Long categoryId) throws Exception {
        return categoryRepository.findById(categoryId).get();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category updateCategory(Category updatedCategory) {
        return categoryRepository.save(updatedCategory);
    }

}
