/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.repositorys.Impl;

import com.ntht.pojos.Category;
import com.ntht.pojos.OrderDetail;
import com.ntht.pojos.Product;
import com.ntht.pojos.SaleOrder;
import com.ntht.repositorys.StatisticRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class StatisticRepositoryImpl implements StatisticRepository{

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Object[]> cateStatis() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = builder.createQuery(Object[].class);
        
        Root rootP = q.from(Product.class);
        Root rootC = q.from(Category.class);
//        phép jion
        q.where(builder.equal(rootP.get("category"), rootC.get("id")));
        
        q.multiselect(rootC.get("id"), rootC.get("name"), builder.count(rootP.get("id")));
        q.groupBy(rootC.get("id"));
        
        Query query = session.createQuery(q);
        
        return query.getResultList();
    }

    @Override
    public List<Object[]> productStatis(String keyWord, Date fromDate, Date toDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = builder.createQuery(Object[].class);
        
        Root rootP = q.from(Product.class);
        Root rootSO = q.from(SaleOrder.class);
        Root rootOD = q.from(OrderDetail.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(rootOD.get("product"), rootP.get("id")));
        predicates.add(builder.equal(rootOD.get("orderId"), rootSO.get("id")));
        
        q.multiselect(rootP.get("id"), rootP.get("name"), builder.sum(builder.prod(rootOD.get("unitPrice"), rootOD.get("num"))));
        
        if(keyWord != null && !keyWord.isEmpty())
            predicates.add(builder.like(rootP.get("name").as(String.class), String.format("%%%s%%", keyWord)));
        
        if(fromDate != null)
            predicates.add(builder.greaterThanOrEqualTo(rootSO.get("createdDate"), fromDate));
        
        if(toDate != null)
            predicates.add(builder.lessThanOrEqualTo(rootSO.get("createdDate"), toDate));
        
        q.where(predicates.toArray(new Predicate[] {}));
        q.groupBy(rootP.get("id"));
        
        Query query = session.createQuery(q);
        
        return query.getResultList();
    }

    @Override
    public List<Object[]> productMonthStatis(String keyWord, Date fromDate, Date toDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = builder.createQuery(Object[].class);
        
        Root rootP = q.from(Product.class);
        Root rootSO = q.from(SaleOrder.class);
        Root rootOD = q.from(OrderDetail.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(rootOD.get("product"), rootP.get("id")));
        predicates.add(builder.equal(rootOD.get("orderId"), rootSO.get("id")));
        
        q.multiselect(builder.function("MONTH", Integer.class, rootSO.get("createdDate")), 
                builder.function("YEAR", Integer.class, rootSO.get("createdDate")),
                builder.sum(builder.prod(rootOD.get("unitPrice"), rootOD.get("num"))));
        
        if(keyWord != null && !keyWord.isEmpty())
            predicates.add(builder.like(rootP.get("name").as(String.class), String.format("%%%s%%", keyWord)));
        
        if(fromDate != null)
            predicates.add(builder.greaterThanOrEqualTo(rootSO.get("createdDate"), fromDate));
        
        if(toDate != null)
            predicates.add(builder.lessThanOrEqualTo(rootSO.get("createdDate"), toDate));
        
        q.where(predicates.toArray(new Predicate[] {}));
        q.groupBy(builder.function("MONTH", Integer.class, rootSO.get("createdDate")), 
                builder.function("YEAR", Integer.class, rootSO.get("createdDate")));
        
        Query query = session.createQuery(q);
        
        return query.getResultList();
    }
    
}
