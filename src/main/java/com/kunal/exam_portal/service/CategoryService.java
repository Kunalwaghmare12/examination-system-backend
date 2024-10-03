package com.kunal.exam_portal.service;

import java.util.Set;

import com.kunal.exam_portal.entity.Category;

public interface CategoryService {
    public Category addCategory(Category category);
    // public Category updateCategory(Long categoryId,Category UpdatedCategory) throws Exception;
    public Category updateCategory(Category updatedCategory);
    public Set<Category> getCategories();
    public Category getCategory(Long categoryId) throws Exception;
    public void deleteCategory(Long categoryId);

}
