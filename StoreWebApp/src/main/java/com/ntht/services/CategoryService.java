/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntht.services;

import com.ntht.pojos.Category;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface CategoryService {
    List<Category> getCategorys();
    Category getCategoryById(int categoryId);
}
