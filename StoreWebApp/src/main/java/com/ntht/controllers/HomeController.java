/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.controllers;

import com.ntht.pojos.Cart;
import com.ntht.pojos.Category;
import com.ntht.services.CategoryService;
import com.ntht.services.ProductService;
import com.ntht.utils.Utils;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Acer
 */
@Controller
@ControllerAdvice
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    
    @ModelAttribute
    public void commonAttribute(Model model, HttpSession session){
        model.addAttribute("categories", this.categoryService.getCategorys());
        model.addAttribute("cartCounter", Utils.countCart((Map<Integer, Cart>) session.getAttribute("cart")));
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
    }
    
    @GetMapping("/")
    public String homeView(Model model, @RequestParam(required = false) Map<String, String> params){
        String kw = params.getOrDefault("kw", null);
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        
        String cateId = params.get("CateId");
        if(cateId == null){
            model.addAttribute("products", this.productService.getProducts(kw, page));
        }else{
            Category c = this.categoryService.getCategoryById(Integer.parseInt(cateId));
            model.addAttribute("products", c.getProductCollection());
        }
        // phân trang product // lấy số lượng tổng sản phẩm
        model.addAttribute("productCounter", this.productService.countProduct());
        // lấy 6 sản phẩm hot nhất
        model.addAttribute("hotProduct", this.productService.getHotProducts(6));
        
        model.addAttribute("hotCommentProduct", this.productService.getHotCommentProducts(6));
        return "home-page";
    }
    
}
