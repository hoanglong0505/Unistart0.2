/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.persistence.AttributeConverter;


/**
 *
 * @author Admin
 */
public class JpaJsonConverter implements AttributeConverter<Object, String> {
    private static final ObjectMapper om = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object attribute) {
        try {
            return om.writeValueAsString(attribute);
        } catch (JsonProcessingException ex) {
            System.out.println("errrr"+ ex);
            return null;
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        try {
             ObjectMapper mapper = new ObjectMapper();
    Object o = mapper.readValue(dbData, Object.class);
            return o;
        } catch (IOException ex) {
            System.out.println("eeeee"+ ex);
            return null;
        }
    }
}