package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Answer;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-06T20:08:50")
@StaticMetamodel(Genitite.class)
public class Genitite_ { 

    public static volatile SingularAttribute<Genitite, Integer> genitiveId;
    public static volatile SingularAttribute<Genitite, String> gentiveCode;
    public static volatile CollectionAttribute<Genitite, Answer> answerCollection;

}