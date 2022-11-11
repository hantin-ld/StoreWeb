/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntht.repositorys;

import com.ntht.pojos.Comment;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface CommentRepository {
    List<Comment> getCommentsByProductId(int productId, int page);
    Long countComment(int producyId); 
    Comment addComment(Comment c);
}
