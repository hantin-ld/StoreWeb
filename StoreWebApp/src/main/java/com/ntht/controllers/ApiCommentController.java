/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.controllers;

import com.ntht.pojos.Comment;
import com.ntht.pojos.User;
import com.ntht.services.CommentService;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Acer
 */
@RestController
public class ApiCommentController {
    @Autowired
    private CommentService commentService;
    
    @PostMapping(path = "/api/add-comment", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Comment> addComment(@RequestBody Map<String, String> params, HttpSession session){
        User u = (User) session.getAttribute("currentUser");
        if(u != null){
            try {
                String content = params.getOrDefault("content", "");
                int productId = Integer.parseInt(params.get("productId")); 

                Comment c = this.commentService.addComment(content, productId, u);
                return new ResponseEntity<>(c, HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
