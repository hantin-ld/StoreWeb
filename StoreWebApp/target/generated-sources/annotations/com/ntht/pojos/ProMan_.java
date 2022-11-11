package com.ntht.pojos;

import com.ntht.pojos.Manufacturer;
import com.ntht.pojos.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-11-10T16:03:27")
@StaticMetamodel(ProMan.class)
public class ProMan_ { 

    public static volatile SingularAttribute<ProMan, Product> productId;
    public static volatile SingularAttribute<ProMan, Manufacturer> manufacturerId;
    public static volatile SingularAttribute<ProMan, Integer> id;

}