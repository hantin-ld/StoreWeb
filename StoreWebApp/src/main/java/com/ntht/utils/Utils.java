/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.utils;

import com.ntht.pojos.Cart;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Acer
 */
public class Utils {
    // đếm số lượng sản phẩm trong giỏ hàng
    public static int countCart(Map<Integer, Cart> cart){
        int q = 0;
        if (cart != null) {
            for(Cart c: cart.values()){
                q += c.getQuantity();
            }
        }
        return q;
    }
    
    public static Map<String, String> cartSumStatus(Map<Integer, Cart> cart){
        Long s = 0l;
        int q = 0;
        if (cart != null) {
            for(Cart c: cart.values()){
                q += c.getQuantity();
                s += c.getQuantity() * c.getPrice();
            }
        }
        Map<String, String> kq = new HashMap<>();
        kq.put("counter", String.valueOf(q));
        kq.put("amount", String.valueOf(s));
        return kq;
    }
}
