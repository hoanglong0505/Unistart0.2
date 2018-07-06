package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Branch;
import model.Region;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-06T20:08:50")
@StaticMetamodel(Location.class)
public class Location_ { 

    public static volatile SingularAttribute<Location, String> locationName;
    public static volatile CollectionAttribute<Location, Branch> branchCollection;
    public static volatile SingularAttribute<Location, Region> regionId;
    public static volatile SingularAttribute<Location, Integer> locationId;

}