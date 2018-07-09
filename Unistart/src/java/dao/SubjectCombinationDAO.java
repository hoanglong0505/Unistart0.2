/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.SubjectCombination;

/**
 *
 * @author QuyPC
 */
public class SubjectCombinationDAO {
   
    public List<SubjectCombination> getSubjectCombination (EntityManager em){
        String sql="Select DISTINCT ES.SjCombiCode\n" +
                        "From EntranceSubject ES\n" +
                        "Order BY SjCombiCode";
        Query query=em.createNativeQuery(sql,SubjectCombination.class);
        List result= query.getResultList();  
        return result;
    }
}
