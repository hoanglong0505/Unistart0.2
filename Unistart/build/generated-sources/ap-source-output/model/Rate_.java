package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.RateDetail;
import model.Report;
import model.School;
import model.Users;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-06T20:08:50")
@StaticMetamodel(Rate.class)
public class Rate_ { 

    public static volatile SingularAttribute<Rate, String> title;
    public static volatile SingularAttribute<Rate, String> experience;
    public static volatile SingularAttribute<Rate, String> userId;
    public static volatile SingularAttribute<Rate, Integer> rateId;
    public static volatile SingularAttribute<Rate, String> pros;
    public static volatile CollectionAttribute<Rate, Report> reportCollection;
    public static volatile SingularAttribute<Rate, Boolean> encourage;
    public static volatile CollectionAttribute<Rate, RateDetail> rateDetails;
    public static volatile SingularAttribute<Rate, School> school;
    public static volatile SingularAttribute<Rate, Integer> schoolId;
    public static volatile SingularAttribute<Rate, Integer> userLike;
    public static volatile SingularAttribute<Rate, Boolean> anonymous;
    public static volatile SingularAttribute<Rate, Users> user;
    public static volatile SingularAttribute<Rate, String> cons;
    public static volatile SingularAttribute<Rate, Integer> userDislike;

}