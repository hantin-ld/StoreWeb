package com.ntht.pojos;

import com.ntht.pojos.Comment;
import com.ntht.pojos.SaleOrder;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-01-04T21:38:17")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> lastName;
    public static volatile CollectionAttribute<User, SaleOrder> saleOrderCollection;
    public static volatile SingularAttribute<User, String> address;
    public static volatile CollectionAttribute<User, Comment> commentCollection;
    public static volatile SingularAttribute<User, Boolean> active;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> userRole;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}