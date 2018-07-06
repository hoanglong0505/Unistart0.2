package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Rate;
import model.ReportPK;
import model.Users;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-06T17:20:42")
@StaticMetamodel(Report.class)
public class Report_ { 

    public static volatile SingularAttribute<Report, ReportPK> reportPK;
    public static volatile SingularAttribute<Report, Rate> rate;
    public static volatile SingularAttribute<Report, String> rpContent;
    public static volatile SingularAttribute<Report, Users> user;

}