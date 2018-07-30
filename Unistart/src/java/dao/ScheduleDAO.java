/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import customer.model.Class;
import customer.model.Session;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
public class ScheduleDAO {

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

    public void createClass(Class c) {
        EntityManager em = emf.createEntityManager();
        c.setStatus(1);
        List<Session> list = c.getSessionList();
        c.setSessionList(null);
        em.getTransaction().begin();
        em.persist(c);
        em.flush();

        list.forEach((s) -> {

            s.setClassId(c);
        });
        c.setSessionList(list);
        em.persist(c);
        em.flush();

        em.getTransaction().commit();
        em.close();
    }

     public void updateClass(Class c) {
        EntityManager em = emf.createEntityManager();
//         clearSession(c);
          List<Session> list = c.getSessionList();
         list.forEach((s) -> {
            s.setClassId(c);
        });
         c.setSessionList(list);
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
        em.close();
    }
     public void clearSession(Class id){
     EntityManager em = emf.createEntityManager();
        String sql="DELETE  FROM Session s WHERE s.classId=39";
        Query query=em.createQuery(sql,Session.class);    
         em.getTransaction().begin();
        query.executeUpdate();
           em.getTransaction().commit();   
    }
}
