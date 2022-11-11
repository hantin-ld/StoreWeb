/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntht.repositorys;

import com.ntht.pojos.Product;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface ProductRepository {
    // lấy danh sách các sản phẩm và tìm kiếm nếu có chuyền từ khóa + phân trang
    List<Product> getProducts(String kw, int page); 
    // lấy những sản phẩm hot nhất 
    List<Object[]> getHotProducts(int mun);
    // lấy dánh sách sản phẩm được bình luận nhiều nhất
    List<Object[]> getHotCommentProducts(int mun);
    // lấy sản phẩm theo id
    Product getProductById(int productId);
    // đếm số lượng sản phẩm phục vụ cho việc phân trang
    long countProduct();
    boolean addOrUpdate(Product product);
}
