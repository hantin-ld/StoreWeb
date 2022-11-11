/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.services.Impl;

import com.ntht.pojos.Cart;
import com.ntht.repositorys.OrderRepository;
import com.ntht.services.OrderSevice;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Acer
 */
@Service
public class OrderSeviceImpl implements OrderSevice{

    @Autowired
    private OrderRepository orderRepository;
    
    @Override
    public boolean addReceipt(Map<Integer, Cart> cart) {
        if(cart != null){
            return this.orderRepository.addReceipt(cart);
        }
        return false;
    }
    
}
