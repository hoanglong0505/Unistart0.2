package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.BranchPK;
import model.Location;
import model.School;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-06T20:08:50")
@StaticMetamodel(Branch.class)
public class Branch_ { 

    public static volatile SingularAttribute<Branch, BranchPK> branchPK;
    public static volatile SingularAttribute<Branch, String> website;
    public static volatile SingularAttribute<Branch, String> address;
    public static volatile SingularAttribute<Branch, String> phone;
    public static volatile SingularAttribute<Branch, School> school;
    public static volatile SingularAttribute<Branch, Location> locationId;
    public static volatile SingularAttribute<Branch, String> branchName;

}