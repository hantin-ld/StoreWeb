/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.services.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntht.pojos.Product;
import com.ntht.repositorys.ProductRepository;
import com.ntht.services.ProductService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private Cloudinary cloudinary;
    
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
        try {
            Map r = this.cloudinary.uploader().upload(product.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            product.setImage((String) r.get("secure_url"));
            product.setCreatedDate(new Date());
            return this.productRepository.addOrUpdate(product);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false; 
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
