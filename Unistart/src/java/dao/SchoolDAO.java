/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.FilterSchool;
import model.ImageSchool;
import model.School;

/**
 *
 * @author QuyPC
 */
public class SchoolDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnistartPU");

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
    public List<School> filterSchool(String schoolName, String sjCode,
            String _minPoint, String _typeID, String fieldCode, String _location, EntityManager em) {

        //======PROCESS PARAMETER==============
        if (schoolName != null && schoolName.trim().length() == 0) {
            schoolName = null;
        }
        Integer locationId = null;
        try {
            locationId = Integer.parseInt(_location);
        } catch (Exception e) {
        }

        if (sjCode != null && sjCode.equals("all")) {
            sjCode = null;
        }

        Float minPoint = null;
        try {
            minPoint = Float.parseFloat(_minPoint);
        } catch (Exception e) {
        }

        Integer typeId = null;
        try {
            typeId = Integer.parseInt(_typeID);
        } catch (Exception e) {
        }

        if (fieldCode != null && fieldCode.equals("all")) {
            fieldCode = null;
        }
        //===================================

        //============SQL====================
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
        if (schoolName != null && schoolName.trim().length() > 0) {
            if (count > 0) {
                condition.append(" AND ");
            }
            condition.append(" S.SchoolName+' '+S.SchoolCode LIKE ? COLLATE Latin1_general_CI_AI \n ");
            count++;
            map.put("schoolName", count);
        }
        if (minPoint != null) {
            if (count > 0) {
                condition.append(" AND ");
            }
            condition.append(" NOT(E.MinPoint > ?) \n ");
            count++;
            map.put("minPoint", count);
        }
        if (typeId != null) {
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
            condition.append(" F.PreFieldId In (Select FieldId From Field where SUBSTRING(FieldCode,2,4)=?)\n ");
            count++;
            map.put("fieldCode", count);
        }
        if (locationId != null) {
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
            query.setParameter(map.get("typeId"), typeId);
        }
        if (map.get("fieldCode") != null) {
            query.setParameter(map.get("fieldCode"), fieldCode);
            System.out.println("codeeeeee " + fieldCode);
        }
        if (map.get("location") != null) {
            query.setParameter(map.get("location"), locationId);
        }

        List result = query.getResultList();
        return result;
    }

   
    public List<School> filterSchoolMultiple(String schoolName, String[] sjCode,
            String _minPoint, String[] _typeID, String[] _fieldCode, String[] _location, EntityManager em) {

        //======PROCESS PARAMETER==============
        if (schoolName != null && schoolName.trim().length() == 0) {
            schoolName = null;
        }
        
        // convert locationId form String to Integer and add into List
        ArrayList<Integer> locationId = new ArrayList<>();
        try {
            for (int i = 0; i < _location.length; i++) {
                int id = Integer.parseInt(_location[i]);
                locationId.add(id);
            }
        } catch (Exception e) {
        }
        // check sjCode 
        if (sjCode != null && sjCode.equals("all")) {
            sjCode = null;
        }
        
        // convert minPoint form String to Float 
        Float minPoint = null;
        try {
            minPoint = Float.parseFloat(_minPoint);
        } catch (Exception e) {
        }
        
        //Convert typeId form String to Integer and add to List
        ArrayList<Integer> typeId = new ArrayList<>();
        try {
            for (int i = 0; i < _typeID.length; i++) {
                typeId.add(Integer.parseInt(_typeID[i]));
            }
        } catch (Exception e) {
        }
        
        //convert fieldCode from String to Int and add to List
        ArrayList<Integer> fieldCode = new ArrayList<>();
        try {
            for (int i = 0; i < _fieldCode.length; i++) {
                fieldCode.add(Integer.parseInt(_fieldCode[i]));
            }
        } catch (Exception e) {
        }
        //===================================

        //============SQL====================
        // call Map to save the position to do query 
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
        // check sjCode and append Condition.use map save postion to do query
        if (sjCode != null) {
            if (sjCode.length > 0) {
                for (int i = 0; i < sjCode.length; i++) {
                    condition.append(" ES.SjCombiCode=?  \n");
                    count++;
                    String key = "sjCode" + i;
                    map.put(key, count);
                    if (i < sjCode.length - 1) {
                        condition.append(" OR ");
                    }
                }
            }
        }
        if (schoolName != null && schoolName.trim().length() > 0) {
            if (count > 0) {
                condition.append(" AND ");
            }
            condition.append(" S.SchoolName+' '+S.SchoolCode LIKE ? COLLATE Latin1_general_CI_AI \n ");
            count++;
            map.put("schoolName", count);
        }
        if (minPoint != null) {
            if (count > 0) {
                condition.append(" AND ");
            }
            condition.append(" NOT(E.MinPoint > ?) \n ");
            count++;
            map.put("minPoint", count);
        }
        if (!typeId.isEmpty()) {
            if (count > 0) {
                condition.append(" AND ");
            }
            if (typeId.size() > 0) {
                for (int i = 0; i < typeId.size(); i++) {
                    condition.append(" S.TypeId=? \n ");
                    count++;
                    map.put("typeId" + i, count);
                    if (i < typeId.size() - 1) {
                        condition.append(" OR ");
                    }
                }
            }
        }
        if (!fieldCode.isEmpty()) {
            if (count > 0) {
                condition.append(" AND ");
            }
            if (fieldCode.size() > 0) {
                for (int i = 0; i < fieldCode.size(); i++) {
                    condition.append(" F.PreFieldId In (Select FieldId From Field where SUBSTRING(FieldCode,2,4)=?)\n ");
                    count++;
                    map.put("fieldCode" + i, count);
                    if (i < fieldCode.size() - 1) {
                        condition.append(" OR ");
                    }
                }
            }
        }
        if (!locationId.isEmpty()) {
            if (count > 0) {
                condition.append(" AND ");
            }
            if (locationId.size() > 0) {
                for (int i = 0; i < locationId.size(); i++) {
                    condition.append(" L.LocationId = ? \n ");
                    count++;
                    map.put("location" + i, count);
                    if (i < locationId.size() - 1) {
                        condition.append(" OR ");
                    }
                }
            }
        }

        if (count > 0) {
            sql.append(condition);
        }
        sql.append(" ORDER BY S.SchoolCode ");
        System.out.println(sql.toString());
        Query query = em.createNativeQuery(sql.toString(), School.class);
        String key;
        
        // check length of List and get map<key> to get position to add value into query
        if (sjCode != null) {
            for (int i = 0; i < sjCode.length; i++) {
                key = "sjCode" + i;
                if (map.get(key) != null) {
                    System.out.println("sjCode " + sjCode[i]);
                    query.setParameter(map.get(key), sjCode[i]);
                }
            }
        }
        // check map<key> isExist or not to do query
        if (map.get("schoolName") != null) {
            query.setParameter(map.get("schoolName"), ("%" + schoolName + "%"));
        }
        // check map<key> isExist or not to do query
        if (map.get("minPoint") != null) {
            query.setParameter(map.get("minPoint"), minPoint);
        }
        // check length of List and get map<key> to get position to add value into query
        if (!typeId.isEmpty()) {
            for (int i = 0; i < typeId.size(); i++) {
                key = "typeId" + i;
                if (map.get(key) != null) {
                    System.out.println("type " + typeId.get(i));
                    query.setParameter(map.get(key), typeId.get(i));
                }
            }
        }
        // check length of List and get map<key> to get position to add value into query
        if (!fieldCode.isEmpty()) {
            for (int i = 0; i < fieldCode.size(); i++) {
                key = "fieldCode" + i;
                if (map.get(key) != null) {
                    System.out.println("field " + fieldCode.get(i));
                    query.setParameter(map.get(key), fieldCode.get(i));
                }
            }
        }
        // check length of List and get map<key> to get position to add value into query
        if (!locationId.isEmpty()) {
            for (int i = 0; i < locationId.size(); i++) {
                key = "location" + i;
                if (map.get(key) != null) {
                    System.out.println("location " + locationId.get(i).toString());
                    query.setParameter(map.get(key), locationId.get(i));
                }
            }
        }
        List result = query.getResultList();
        return result;
    }
public List<ImageSchool> getImageByCode(String Code ){
     EntityManager em = emf.createEntityManager();
        String sql="SELECT s.* FROM ImageSchool s WHERE s.schoolCode ='"+Code+"'";
        Query query=em.createNativeQuery(sql,ImageSchool.class);
        List result=  query.getResultList();  
        return result;
    }
public void clearImage(){
     EntityManager em = emf.createEntityManager();
        String sql="DELETE  FROM ImageSchool  ";
        Query query=em.createQuery(sql,ImageSchool.class);
     
         em.getTransaction().begin();
        query.executeUpdate();
           em.getTransaction().commit();   
    }
public void createImage(String code, String link){
     EntityManager em = emf.createEntityManager();
//        String sql="INSERT INTO ImageSchool( Link , SchoolCode ) VALUES ('"+link+"','"+code+"')";
//        System.out.println(sql);
        ImageSchool ima= new ImageSchool();
        ima.setLink(link);
        ima.setSchoolCode(code);
//        Query query=em.createQuery(sql);  
         em.getTransaction().begin();
        em.persist(ima);
        em.flush();
        System.out.println("aaaaaaa"+ima.getImageId());
           em.getTransaction().commit();   
}
   
public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    

}
