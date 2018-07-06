package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Field;
import model.School;
import model.SubjectCombination;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-06T17:20:42")
@StaticMetamodel(EntranceInfo.class)
public class EntranceInfo_ { 

    public static volatile SingularAttribute<EntranceInfo, Double> minPoint;
    public static volatile SingularAttribute<EntranceInfo, Integer> otherEntranceAmount;
    public static volatile SingularAttribute<EntranceInfo, String> note;
    public static volatile SingularAttribute<EntranceInfo, Integer> entranceId;
    public static volatile SingularAttribute<EntranceInfo, Integer> year;
    public static volatile SingularAttribute<EntranceInfo, String> subCode;
    public static volatile SingularAttribute<EntranceInfo, String> subName;
    public static volatile SingularAttribute<EntranceInfo, School> schoolId;
    public static volatile SingularAttribute<EntranceInfo, Integer> normalEntranceAmount;
    public static volatile CollectionAttribute<EntranceInfo, SubjectCombination> subjectCombinationCollection;
    public static volatile SingularAttribute<EntranceInfo, Field> fieldId;

}