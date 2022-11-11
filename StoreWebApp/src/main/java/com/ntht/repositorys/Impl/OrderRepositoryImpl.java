/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.repositorys.Impl;

import com.ntht.pojos.Cart;
import com.ntht.pojos.OrderDetail;
import com.ntht.pojos.SaleOrder;
import com.ntht.repositorys.OrderRepository;
import com.ntht.repositorys.ProductRepository;
import com.ntht.repositorys.UserRepository;
import com.ntht.utils.Utils;
import java.util.Date;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Acer
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository{

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addReceipt(Map<Integer, Cart> cart) {
        // phòng trường hợp lỗi
        try{
            Session session = this.sessionFactory.getObject().getCurrentSession();
            // tao order
            SaleOrder order = new SaleOrder();
            // lưu các thông tin hàng bán SaleOrder
            order.setUserId(this.userRepository.getUserById(7));
            order.setCreatedDate(new Date());
            Map<String, String> stats = Utils.cartSumStatus(cart);
            order.setAmount(Long.parseLong( stats.get("amount")));
            session.save(order);

            // lưu thông tin chi tiết đơn hàng OrderDetail
            for(Cart c: cart.values()){
                OrderDetail d = new OrderDetail();
                d.setOrderId(order);
                d.setProduct(this.productRepository.getProductById(c.getProductId()));
                d.setUnitPrice(c.getPrice());
                d.setNum(c.getQuantity());
                session.save(d);
            }
            
            return true;
        }catch(HibernateException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
}
