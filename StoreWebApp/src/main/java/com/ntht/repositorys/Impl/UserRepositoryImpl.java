/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.repositorys.Impl;

import com.ntht.pojos.User;
import com.ntht.repositorys.UserRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Acer
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public User getUserById(int userId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(User.class, userId);
    }

    @Override
    public boolean addUser(User user) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        // nếu user không tồn tại
        try {
            session.save(user); // tạo user mới
            return true;
        } catch (HibernateException e) {
            System.err.println("=== ADD USER ERROR ===" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getUsers(String username) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class); // Tạo câu truy vấn
        Root root =  query.from(User.class); // tương tác trên đối tượng Invidual
        query = query.select(root); // lấy hết các dữ liệu có trong Invidual (a)
        
        // nếu có tên tài khoảm truyền vào
        if (!username.isEmpty()) {
            // lấy ds (a) theo trường account so sánh với dữ liệu acc được truyền vào đã được xóa khoảng tráng hai đầu 
            Predicate p = builder.equal(root.get("username").as(String.class), username.trim());
            // lấy dữ liệu được từ trên nạp vào điều kiện where
            // h câu truy vấn: select * from Invidual where Invidual.account == acc => trả về một kết quả
            query = query.where(p);
        }
        // thực thi truy vấn
        Query q = session.createQuery(query);
        // không có dữ liệu truyền vào thì trả về một list danh sách, ngược lại trả về một đối tượng trong danh sách 
        return q.getResultList();
    }
    
}
