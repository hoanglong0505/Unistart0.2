/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.School;

/**
 *
 * @author QuyPC
 */
public class SchoolDAO {

//    public List<School> getSchoolByTypeId(Integer typeID, EntityManager em) {
//        String sql = "Select * From School where TypeId = ?";
//        Query query = em.createNativeQuery(sql, School.class);
//        query.setParameter(1, typeID);
//        List result = query.getResultList();
//        return result;
//    }
//
//    public List<School> getSchoolByMinPoint(Float minPoint, EntityManager em) {
//        String sql = "Select  S.SchoolId,S.SchoolCode,S.SchoolName,S.TypeId , E.SubName , ES.SjCombiCode ,E.MinPoint\n"
//                + "From  School S,EntranceInfo E, Field F ,EntranceSubject ES \n"
//                + "Where E.MinPoint <= ? AND S.SchoolId=E.SchoolId And F.FieldId=E.FieldId AND E.EntranceId = ES.EntranceId";
//        Query query = em.createNativeQuery(sql, School.class);
//        query.setParameter(1, minPoint);
//        List result = query.getResultList();
//        return result;
//    }
//
//    public List<School> getSchoolByFieldCode(String fieldCode, EntityManager em) {
//        String sql = "Select  DISTINCT SchoolName , S.SchoolId , E.EntranceId , F.FieldId\n"
//                + "From School S , Field F ,EntranceInfo E\n"
//                + "Where F.PreFieldId In (Select FieldId From Field Where FieldCode=?) ANd S.SchoolId=E.SchoolId AND E.FieldId = F.FieldId";
//        Query query = em.createNativeQuery(sql, School.class);
//        query.setParameter(1, fieldCode);
//        List result = query.getResultList();
//        return result;
//    }
//
//    public List<School> getSchoolBySjCombiCode(String code, EntityManager em) {
//        String sql = "Select DISTINCT S.SchoolName , S.SchoolId ,ES.SjCombiCode \n"
//                + "From School S , EntranceInfo E , EntranceSubject ES\n"
//                + "Where S.SchoolId = E.SchoolId AND E.EntranceId = ES.EntranceId AND ES.SjCombiCode =?";
//        Query query = em.createNativeQuery(sql, School.class);
//        query.setParameter(1, code);
//        List result = query.getResultList();
//        return result;
//    }
//
//    public List<School> getSchoolByLocation(int code, EntityManager em) {
//        String sql = "Select * \n"
//                + "From School S , Branch B ,dbo.Location L\n"
//                + "Where S.SchoolId = B.SchoolId AND B.LocationId = L.LocationId AND L.LocationId=?";
//        Query query = em.createNativeQuery(sql, School.class);
//        query.setParameter(1, code);
//        List result = query.getResultList();
//        return result;
//    }

    public List<School> filterSchool(String schoolName, String sjCode, Float minPoint, Integer typeID, String fieldCode, Integer location, EntityManager em) {
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sql = new StringBuilder("Select  DISTINCT S.SchoolCode,S.SchoolName , S.SchoolId \n"
                + "FROM School S \n"
                + "LEFT JOIN Branch B ON S.SchoolId = B.SchoolId\n"
                + "LEFT JOIN Location L ON L.LocationId = B.LocationId \n"
                + "LEFT JOIN EntranceInfo E ON E.SchoolId= S.SchoolId\n"
                + "LEFT JOIN EntranceSubject ES ON ES.EntranceId = E.EntranceId \n"
                + "LEFT JOIN Field F ON F.FieldId = E.FieldId\n");

        StringBuilder condition = new StringBuilder("Where ");
        int count = 0;
        if (sjCode != null) {
            condition.append(" ES.SjCombiCode=?  \n");
            count++;
            map.put("sjCode", count);
        }
        if (schoolName != null && schoolName.trim().length()>0) {
            if (count > 0) {
                condition.append(" AND ");
            }
            condition.append(" S.SchoolName LIKE ? \n ");
            count++;
            map.put("schoolName", count);
        }
        if (minPoint != null) {
            if (count > 0) {
                condition.append(" AND ");
            }
            condition.append(" E.MinPoint <= ? \n ");
            count++;
            map.put("minPoint", count);
        }
        if (typeID != null) {
            if (count > 0) {
                condition.append(" AND ");
            }
            condition.append(" S.TypeId=? \n ");
            count++;
            map.put("typeId", count);
        }
        if (fieldCode != null) {
            if (count > 0) {
                condition.append(" AND ");
            }
            condition.append(" F.PreFieldId In (Select FieldId From Field Where FieldCode=?)\n ");
            count++;
            map.put("fieldCode", count);
        }
        if (location != null) {
            if (count > 0) {
                condition.append(" AND ");
            }
            condition.append(" L.LocationId = ? \n ");
            count++;
            map.put("location", count);
        }

        if (count > 0) {
            sql.append(condition);
        }
        sql.append(" ORDER BY S.SchoolCode ");
        System.out.println(sql.toString());
        Query query = em.createNativeQuery(sql.toString(), School.class);
        if (map.get("sjCode") != null) {
            query.setParameter(map.get("sjCode"), sjCode);
        }
        if (map.get("schoolName") != null) {
            query.setParameter(map.get("schoolName"), ("%" + schoolName + "%"));
        }
        if (map.get("minPoint") != null) {
            query.setParameter(map.get("minPoint"), minPoint);
        }
        if (map.get("typeId") != null) {
            query.setParameter(map.get("typeId"), typeID);
        }
        if (map.get("fieldCode") != null) {
            query.setParameter(map.get("fieldCode"), fieldCode);
        }
        if (map.get("location") != null) {
            query.setParameter(map.get("location"), location);
        }

        List result = query.getResultList();
        return result;
    }

}
