 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.services.Impl;

import com.ntht.pojos.Comment;
import com.ntht.pojos.Product;
import com.ntht.pojos.User;
import com.ntht.repositorys.CommentRepository;
import com.ntht.repositorys.ProductRepository;
import com.ntht.repositorys.UserRepository;
import com.ntht.services.CommentService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Acer
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired    
    private CommentRepository commentRepository;
    
    @Override
    public Comment addComment(String content, int productId, User creator) {
        Product p = this.productRepository.getProductById(productId);
       // User u = this.userRepository.getUserById(7);
        
        Comment c = new Comment();
        c.setContent(content);
        c.setProduct(p);
        c.setUser(creator);
        c.setCreatedDate(new Date());
        
        return this.commentRepository.addComment(c);
    }

    @Override
    public List<Comment> getCommentsByProductId(int productId, int page) {
        return this.commentRepository.getCommentsByProductId(productId, page);
    }

    @Override
    public Long countComment(int i) {
        return this.commentRepository.countComment(i);
    }
    
}
