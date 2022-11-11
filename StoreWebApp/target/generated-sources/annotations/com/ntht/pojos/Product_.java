package com.ntht.pojos;

import com.ntht.pojos.Category;
import com.ntht.pojos.Comment;
import com.ntht.pojos.OrderDetail;
import com.ntht.pojos.ProMan;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-11-10T16:03:27")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, String> image;
    public static volatile SingularAttribute<Product, Date> createdDate;
    public static volatile CollectionAttribute<Product, OrderDetail> orderDetailCollection;
    public static volatile SingularAttribute<Product, Long> price;
    public static volatile CollectionAttribute<Product, Comment> commentCollection;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Boolean> active;
    public static volatile SingularAttribute<Product, Integer> id;
    public static volatile SingularAttribute<Product, Category> category;
    public static volatile CollectionAttribute<Product, ProMan> proManCollection;
    public static volatile SingularAttribute<Product, String> manufacturer;

}