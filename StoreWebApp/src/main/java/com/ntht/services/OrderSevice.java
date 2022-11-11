/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntht.services;

import com.ntht.pojos.Cart;
import java.util.Map;

/**
 *
 * @author Acer
 */
public interface OrderSevice {
    boolean addReceipt(Map<Integer, Cart> cart);
}
