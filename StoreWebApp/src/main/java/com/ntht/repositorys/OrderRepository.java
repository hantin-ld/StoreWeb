/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntht.repositorys;

import com.ntht.pojos.Cart;
import java.util.Map;

/**
 *
 * @author Acer
 */
public interface OrderRepository {
    boolean addReceipt(Map<Integer, Cart> cart);
}
