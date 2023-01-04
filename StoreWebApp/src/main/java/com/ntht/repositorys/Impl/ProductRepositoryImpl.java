/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.repositorys.Impl;

import com.ntht.pojos.Comment;
import com.ntht.pojos.OrderDetail;
import com.ntht.pojos.Product;
import com.ntht.repositorys.ProductRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class ProductRepositoryImpl implements ProductRepository{

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Product> getProducts(String keyWord, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root root = query.from(Product.class);
        if(keyWord != null ){
            Predicate predicate = builder.like(root.get("name").as(String.class), String.format("%%%s%%", keyWord));
            query = query.where(predicate);
        }
        
        query = query.orderBy(builder.desc(root.get("id"))); // desc là sắp xếp giảm dần
        Query q = session.createQuery(query);
        
        //page phân trang
        int max = 18;
        q.setMaxResults(max);
        q.setFirstResult((page - 1)* max);
        
        return q.getResultList();
        
    }

    @Override
    public Product getProductById(int productId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(Product.class, productId);
    }

    @Override
    public long countProduct() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root root = query.from(Product.class);
        query = query.multiselect(builder.count(root.get("id").as(Integer.class)));
        Query q = session.createQuery(query);
        return (long) q.getSingleResult();
    }

    @Override
    public boolean addOrUpdate(Product product) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            session.save(product);
            return true;
        }catch(HibernateException ex){
            System.err.println("=== ADD PRODUCT ERROR ===" + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Object[]> getHotProducts(int i) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query =builder.createQuery(Object[].class);
        // thực hiện jion hai bảng
        Root rootP =query.from(Product.class);
        Root rootD = query.from(OrderDetail.class);
        // thực hiện where // so sánh giữa Khóa ngoại orderDetail với Product_id
        query = query.where(builder.equal(rootD.get("product"), rootP.get("id")));
        // chọn những trường sẽ lấy // sau khi thực hiện jion ta đếm số sản phẩm 
        query.multiselect(rootP.get("id"), rootP.get("name"), rootP.get("price"), rootP.get("image"), builder.count(rootP.get("id")));
        // thực hiện gom nhóm
        query = query.groupBy(rootP.get("id"));
        // sắp xếp giảm dần
        query = query.orderBy(builder.desc(builder.count(rootP.get("id"))));
        Query q = session.createQuery(query);
        q.setMaxResults(i);
        
        return q.getResultList();
    }

    @Override
    public List<Object[]> getHotCommentProducts(int i) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query =builder.createQuery(Object[].class);
        // thực hiện jion hai bảng
        Root rootP =query.from(Product.class);
        Root rootC = query.from(Comment.class);
        // thực hiện where // so sánh giữa Khóa ngoại comment với Product_id
        query = query.where(builder.equal(rootC.get("product"), rootP.get("id")));
        // chọn những trường sẽ lấy // sau khi thực hiện jion ta đếm số sản phẩm 
        query.multiselect(rootP.get("id"), rootP.get("name"), rootP.get("price"), rootP.get("image"), builder.count(rootP.get("id")));
        // thực hiện gom nhóm
        query = query.groupBy(rootP.get("id"));
        // sắp xếp giảm dần theo comment, nếu số comment giống nhau thì sắp xếp giảm theo sản phẩm
        query = query.orderBy(builder.desc(builder.count(rootP.get("id"))), builder.desc(rootP.get("id")));
        Query q = session.createQuery(query);
        q.setMaxResults(i);
        
        return q.getResultList();
    }
    
}
