package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Article;
import model.Branch;
import model.EntranceInfo;
import model.Rate;
import model.Type;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-06T17:20:42")
@StaticMetamodel(School.class)
public class School_ { 

    public static volatile CollectionAttribute<School, EntranceInfo> entranceInfoCollection;
    public static volatile CollectionAttribute<School, Article> articleCollection;
    public static volatile SingularAttribute<School, String> website;
    public static volatile CollectionAttribute<School, Rate> rateCollection;
    public static volatile CollectionAttribute<School, Branch> branchCollection;
    public static volatile SingularAttribute<School, String> phone;
    public static volatile SingularAttribute<School, Integer> schoolId;
    public static volatile SingularAttribute<School, Type> typeId;
    public static volatile SingularAttribute<School, String> avatar;
    public static volatile SingularAttribute<School, String> schoolName;
    public static volatile SingularAttribute<School, String> email;
    public static volatile SingularAttribute<School, String> schoolCode;

}