/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
public class UserDAO implements Serializable{

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
    
    public String CheckEmailExited(String email){
        EntityManager e=emf.createEntityManager();
        String sql="select User from Users  where Email = '"+email+"'";  
       // String sql="select User from Users  where Email = '"+email+"'";  
        Query query=e.createNativeQuery(sql);
        try{
            query.getSingleResult();
            return "Exited";
        }catch( NoResultException ex){
            return "Not exited";
        }
        
    }
    
}
