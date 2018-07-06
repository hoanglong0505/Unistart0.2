/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Field;
import model.School;

/**
 *
 * @author QuyPC
 */
public class FieldDAO {
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
    public List<Field> getFieldType(Integer FieldId){
        EntityManager em=emf.createEntityManager();
        String jpql="Select *\n" +
                        "From Field F\n" +
                        "Where F.FieldTypeId=?";
        Query query=em.createNativeQuery(jpql,Field.class);
        query.setParameter(1, FieldId);
        List result= query.getResultList();  
        return result;
    }
}
