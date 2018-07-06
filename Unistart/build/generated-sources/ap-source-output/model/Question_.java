package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Answer;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-06T20:08:50")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile SingularAttribute<Question, Integer> questionId;
    public static volatile SingularAttribute<Question, String> questionDetail;
    public static volatile CollectionAttribute<Question, Answer> answerCollection;

}