/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.services.Impl;

import com.ntht.pojos.Category;
import com.ntht.repositorys.CategoryRepository;
import com.ntht.services.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Acer
 */
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Override
    public List<Category> getCategorys() {
        return categoryRepository.getCategorys();
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return categoryRepository.getCategoryById(categoryId);
    }
    
}
