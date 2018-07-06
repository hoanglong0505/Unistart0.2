package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Genitite;
import model.Question;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-06T20:08:50")
@StaticMetamodel(Answer.class)
public class Answer_ { 

    public static volatile SingularAttribute<Answer, Integer> answerId;
    public static volatile SingularAttribute<Answer, Genitite> genititeId;
    public static volatile SingularAttribute<Answer, String> answerDetail;
    public static volatile SingularAttribute<Answer, Question> questionId;

}