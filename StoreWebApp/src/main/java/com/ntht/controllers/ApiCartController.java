/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.controllers;

import com.ntht.pojos.Cart;
import com.ntht.repositorys.OrderRepository;
import com.ntht.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Acer
 */
@RestController
public class ApiCartController {
    @Autowired
    private OrderRepository orderRepository;
    
    @PostMapping("/api/cart")
    public int addToCart(@RequestBody Cart cart, HttpSession session){
        Map<Integer, Cart> carts = (Map<Integer, Cart>) session.getAttribute("cart");
        if (carts == null) {
            carts = new HashMap<>();
        }
        int productId = cart.getProductId();
        if (carts.containsKey(productId) == true) {
            // sản phẩm đã có trong giỏ hàng
            Cart c = carts.get(productId);
            c.setQuantity(c.getQuantity() + 1);
        }else{
            carts.put(productId, cart);
        }
        
        session.setAttribute("cart", carts);
        return Utils.countCart(carts);
    }
    
    @PutMapping("/api/cart")
    public ResponseEntity<Map<String, String>> updateToCart(@RequestBody Cart cart, HttpSession session){
        Map<Integer, Cart> carts = (Map<Integer, Cart>) session.getAttribute("cart");
        if (carts == null) {
            carts = new HashMap<>();
        }
        int productId = cart.getProductId();
        if (carts.containsKey(productId) == true) {
            // sản phẩm đã có trong giỏ hàng
            Cart c = carts.get(productId);
            c.setQuantity(cart.getQuantity());
        }
        session.setAttribute("cart", carts);
        return new ResponseEntity<>(Utils.cartSumStatus(carts), HttpStatus.OK);
    }
    
    @DeleteMapping("/api/cart/{productId}")
    public ResponseEntity<Map<String, String>> delateCart(@PathVariable(value = "productId") int productId, HttpSession session){
        Map<Integer, Cart> carts = (Map<Integer, Cart>) session.getAttribute("cart");
        if(carts != null && carts.containsKey(productId)){
            carts.remove(productId);
            session.setAttribute("cart", carts);
        }
        return new ResponseEntity<>(Utils.cartSumStatus(carts), HttpStatus.OK);
    }
    
    @PostMapping("/api/pay")
    public HttpStatus pay(HttpSession session){
        if(this.orderRepository.addReceipt((Map<Integer, Cart>) session.getAttribute("cart"))){
            session.removeAttribute("cart");
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
