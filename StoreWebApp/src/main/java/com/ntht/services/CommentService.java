/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntht.services;

import com.ntht.pojos.Comment;
import com.ntht.pojos.User;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface CommentService {
    Comment addComment(String content, int productId, User creator);
    Long countComment(int productId);
    List<Comment> getCommentsByProductId(int productId, int page);
}
