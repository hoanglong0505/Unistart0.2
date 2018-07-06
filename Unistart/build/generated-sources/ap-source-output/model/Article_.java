package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.School;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-06T20:08:50")
@StaticMetamodel(Article.class)
public class Article_ { 

    public static volatile SingularAttribute<Article, School> schoolId;
    public static volatile SingularAttribute<Article, Integer> articleId;
    public static volatile SingularAttribute<Article, String> link;
    public static volatile SingularAttribute<Article, Boolean> status;

}