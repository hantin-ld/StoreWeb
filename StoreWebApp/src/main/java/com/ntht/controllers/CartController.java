/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.controllers;

import com.ntht.pojos.Cart;
import com.ntht.utils.Utils;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Acer
 */
@Controller
public class CartController {
    
    @GetMapping("/cart") 
    public String cartView(Model model, HttpSession session){
        // trường hợp trả về null 
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if (cart != null) {
            model.addAttribute("carts", cart.values());
        }else{
            model.addAttribute("carts", null);
        }
       
        model.addAttribute("cartSumStatus", Utils.cartSumStatus(cart));
        
        return "cart-page";
    }
}
