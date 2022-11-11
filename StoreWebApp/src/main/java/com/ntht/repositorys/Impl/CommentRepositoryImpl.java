/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.repositorys.Impl;

import com.ntht.pojos.Comment;
import com.ntht.pojos.Product;
import com.ntht.repositorys.CommentRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class CommentRepositoryImpl implements CommentRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public Comment addComment(Comment c) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(c);
            return c;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Comment> getCommentsByProductId(int productId, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Comment> query = builder.createQuery(Comment.class);
        Root root = query.from(Comment.class);
        
        query = query.where(builder.equal(root.get("product"), productId));
        query = query.orderBy(builder.desc(root.get("id")));
        
        Query q = session.createQuery(query);
        
        int max = 7;
        q.setMaxResults(max);
        q.setFirstResult((page - 1) * max);
        
        return q.getResultList();
    }

    @Override
    public Long countComment(int productId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root root = query.from(Comment.class);
        query = query.where(builder.equal(root.get("product"), productId));
        query = query.multiselect(builder.count(root.get("id").as(Integer.class)));
        Query q = session.createQuery(query);
        return (long) q.getSingleResult();
    }
    
}
