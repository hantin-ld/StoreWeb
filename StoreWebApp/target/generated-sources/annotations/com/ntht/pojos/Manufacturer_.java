package com.ntht.pojos;

import com.ntht.pojos.ProMan;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-01-04T21:38:17")
@StaticMetamodel(Manufacturer.class)
public class Manufacturer_ { 

    public static volatile SingularAttribute<Manufacturer, String> country;
    public static volatile SingularAttribute<Manufacturer, String> name;
    public static volatile SingularAttribute<Manufacturer, Integer> id;
    public static volatile CollectionAttribute<Manufacturer, ProMan> proManCollection;

}