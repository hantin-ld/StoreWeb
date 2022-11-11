/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntht.services;

import com.ntht.pojos.Product;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface ProductService {
    List<Product> getProducts(String kw, int page); 
    List<Object[]> getHotProducts(int i);
    List<Object[]> getHotCommentProducts(int i);
    Product getProductById(int productId);
    long countProduct();
    boolean addOrUpdate(Product product);
}
