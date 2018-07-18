/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Answer;
import model.Question;


/**
 *
 * @author Admin
 */
public class MBTIDAO {
        public List<Question> getQuestions(EntityManager em) throws JsonProcessingException{
        String sql="SELECT * FROM Question";
        Query query=em.createNativeQuery(sql,Question.class);
        List<Question> result= query.getResultList();  
         
        for(Question re: result){
            List<Answer> list=getAnswerByQuestion(em,re.getQuestionId());
            re.setAnswerList(list);
        }
        return result;
    }
        public List<Answer> getAnswerByQuestion(EntityManager em, int id){
        String sql="SELECT * FROM Answer where QuestionId="+id;
        Query query=em.createNativeQuery(sql,Answer.class);
        List result= query.getResultList();             
        return result;
    }
}
