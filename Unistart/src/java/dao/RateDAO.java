/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Rate;
import model.Rate_;

/**
 *
 * @author Admin
 */
public class RateDAO implements Serializable {

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
    public List<Rate> GetRateByUserId(int userId){
        EntityManager e=emf.createEntityManager();
        String sql="select r.RateId,r.SchoolId,r.UserId,r.Title,r.Pros,\n" +
"r.Cons,r.Experience,r.Encourage,r.Anonymous from Rate r where r.UserId="+userId+"";  
        Query query=e.createNativeQuery(sql,Rate.class);
       List<Rate> list =query.getResultList();
       JpaJsonConverter jj = new JpaJsonConverter();
        System.out.println(jj.convertToDatabaseColumn(list));
       return list;
    } 
    public  void CreateRate(Rate r){
       
    }
}
