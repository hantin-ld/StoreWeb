/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.controllers;


import com.ntht.pojos.Product;
import com.ntht.services.CommentService;
import com.ntht.services.ProductService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Acer
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CommentService commentService;
    
    
    @GetMapping("/products/{productId}")
    public String detail(Model model, 
            @PathVariable(value = "productId") int productId, 
            @RequestParam(required = false) Map<String, String> params ){
        model.addAttribute("product", this.productService.getProductById(productId));
        
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        
        model.addAttribute("comments", this.commentService.getCommentsByProductId(productId, page));
        
        model.addAttribute("commentCounter", this.commentService.countComment(productId));
        return "product-detail";
    }
    
    @GetMapping("/admin/add-product")
    public String addProductView(Model model){
        model.addAttribute("product", new Product());
        return "product-add";
    }
    
    @PostMapping("/admin/add-product")
    public String addProduct(Model model, @ModelAttribute(value = "product") Product product){
        if(this.productService.addOrUpdate(product) == true){
            return "redirect:/";
        }
        return "product-add";
    }
}
