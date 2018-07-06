package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.EntranceInfo;
import model.Field;
import model.FieldType;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-06T20:08:50")
@StaticMetamodel(Field.class)
public class Field_ { 

    public static volatile CollectionAttribute<Field, EntranceInfo> entranceInfoCollection;
    public static volatile SingularAttribute<Field, String> fieldName;
    public static volatile SingularAttribute<Field, String> fieldCode;
    public static volatile SingularAttribute<Field, Integer> preFieldId;
    public static volatile SingularAttribute<Field, Field> preField;
    public static volatile CollectionAttribute<Field, Field> fieldCollection;
    public static volatile SingularAttribute<Field, FieldType> fieldType;
    public static volatile SingularAttribute<Field, Integer> fieldTypeId;
    public static volatile SingularAttribute<Field, Integer> fieldId;

}