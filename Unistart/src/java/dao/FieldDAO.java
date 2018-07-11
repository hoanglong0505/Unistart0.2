/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Field;

/**
 *
 * @author QuyPC
 */
public class FieldDAO {
    
    public List<Field> getFieldType(Integer FieldId, EntityManager em){
        String sql="Select *\n" +
                        "From Field F\n" +
                        "Where F.FieldTypeId=?";
        Query query=em.createNativeQuery(sql,Field.class);
        query.setParameter(1, FieldId);
        List result= query.getResultList();  
        return result;
    }
}
