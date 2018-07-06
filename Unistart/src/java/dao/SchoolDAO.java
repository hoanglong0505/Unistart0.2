/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.School;

/**
 *
 * @author QuyPC
 */
public class SchoolDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnistartPU");

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

    public List<School> getSchoolByTypeId(Integer typeID) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select * From School where TypeId = ?";
        Query query = em.createNativeQuery(jpql, School.class);
        query.setParameter(1, typeID);
        List result = query.getResultList();
        return result;
    }

    public List<School> getSchoolByMinPoint(Float minPoint) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select  S.SchoolId,S.SchoolCode,S.SchoolName,S.TypeId , E.SubName , ES.SjCombiCode ,E.MinPoint\n"
                + "From  School S,EntranceInfo E, Field F ,EntranceSubject ES \n"
                + "Where E.MinPoint <= ? AND S.SchoolId=E.SchoolId And F.FieldId=E.FieldId AND E.EntranceId = ES.EntranceId";
        Query query = em.createNativeQuery(jpql, School.class);
        query.setParameter(1, minPoint);
        List result = query.getResultList();
        return result;
    }

    public List<School> getSchoolByFieldCode(String fieldCode) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select  DISTINCT SchoolName , S.SchoolId , E.EntranceId , F.FieldId\n"
                + "From School S , Field F ,EntranceInfo E\n"
                + "Where F.PreFieldId In (Select FieldId From Field Where FieldCode=?) ANd S.SchoolId=E.SchoolId AND E.FieldId = F.FieldId";
        Query query = em.createNativeQuery(jpql, School.class);
        query.setParameter(1, fieldCode);
        List result = query.getResultList();
        return result;
    }

    public List<School> getSchoolBySjCombiCode(String code) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select DISTINCT S.SchoolName , S.SchoolId ,ES.SjCombiCode \n"
                + "From School S , EntranceInfo E , EntranceSubject ES\n"
                + "Where S.SchoolId = E.SchoolId AND E.EntranceId = ES.EntranceId AND ES.SjCombiCode =?";
        Query query = em.createNativeQuery(jpql, School.class);
        query.setParameter(1, code);
        List result = query.getResultList();
        return result;
    }

    public List<School> getSchoolByLocation(int code) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select * \n"
                + "From School S , Branch B ,dbo.Location L\n"
                + "Where S.SchoolId = B.SchoolId AND B.LocationId = L.LocationId AND L.LocationId=?";
        Query query = em.createNativeQuery(jpql, School.class);
        query.setParameter(1, code);
        List result = query.getResultList();
        return result;
    }

    public List<School> getSchoolList(String sjCode, Float minPoint, Integer typeID, String fieldCode, Integer location) {
        EntityManager em = emf.createEntityManager();
        Map<String, Integer> map = new HashMap<>();
        String jpql = "Select  DISTINCT S.SchoolCode,S.SchoolName , S.SchoolId \n"
                + "FROM School S \n"
                + "LEFT JOIN Branch B ON S.SchoolId = B.SchoolId\n"
                + "LEFT JOIN dbo.Location L ON L.LocationId = B.LocationId \n"
                + "LEFT JOIN EntranceInfo E ON E.SchoolId= S.SchoolId\n"
                + "LEFT JOIN EntranceSubject ES ON ES.EntranceId = E.EntranceId \n"
                + "LEFT JOIN Field F ON F.FieldId = E.FieldId\n";

        String str = "Where ";
        int count = 0;
        if (sjCode != null) {
            String add = " ES.SjCombiCode=?  \n";
            str += add;
            count++;
            map.put("sjCode", count);
        }
        if (minPoint != null) {
            String add = "";
            if (count > 0) {
                add = " AND ";
            }
            add += "  E.MinPoint <= ? \n ";
            count++;
            str += add;
            map.put("minPoint", count);
        }
        if (typeID != null) {
            String add = "";
            if (count > 0) {
                add = " AND ";
            }
            add += " S.TypeId=? \n ";
            count++;
            str += add;
            map.put("typeId", count);
        }
        if (fieldCode != null) {
            String add = "";
            if (count > 0) {
                add = " AND ";
            }
            add += " F.PreFieldId In (Select FieldId From Field Where FieldCode=?)\n ";
            count++;
            str += add;
            map.put("fieldCode", count);
        }
        if (location != null) {
            String add = "";
            if (count > 0) {
                add = " AND ";
            }
            add += " L.LocationId = ? \n ";
            count++;
            str += add;
            map.put("location", count);
        }

        if (count > 0) {
            jpql += str;
        }
        jpql += " ORDER BY S.SchoolCode ";
        System.out.println(jpql);
        Query query = em.createNativeQuery(jpql, School.class);
        if (map.get("sjCode") != null) {
            query.setParameter(map.get("sjCode"), sjCode);
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
