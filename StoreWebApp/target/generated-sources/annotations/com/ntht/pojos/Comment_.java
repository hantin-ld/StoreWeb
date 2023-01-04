package com.ntht.pojos;

import com.ntht.pojos.Product;
import com.ntht.pojos.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-01-04T21:38:17")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, Product> product;
    public static volatile SingularAttribute<Comment, Date> createdDate;
    public static volatile SingularAttribute<Comment, Integer> id;
    public static volatile SingularAttribute<Comment, User> user;
    public static volatile SingularAttribute<Comment, String> content;

}