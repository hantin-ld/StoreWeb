/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.services.Impl;

import com.ntht.pojos.Product;
import com.ntht.repositorys.ProductRepository;
import com.ntht.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Acer
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public List<Product> getProducts(String kw, int page) {
        return this.productRepository.getProducts(kw, page);
    }

    @Override
    public Product getProductById(int productId) {
        return this.productRepository.getProductById(productId);
    }

    @Override
    public long countProduct() {
        return this.productRepository.countProduct();
    }

    @Override
    public boolean addOrUpdate(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object[]> getHotProducts(int i) {
        return this.productRepository.getHotProducts(i);
    }

    @Override
    public List<Object[]> getHotCommentProducts(int i) {
        return this.productRepository.getHotCommentProducts(i);
    }
    
}
