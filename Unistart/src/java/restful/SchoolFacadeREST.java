/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import app.Constants;
import dao.SchoolDAO;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import model.FilterSchool;
import model.School;

/**
 *
 * @author TNT
 */
@Stateless
@Path("model.school")
public class SchoolFacadeREST extends AbstractFacade<School> {

    @PersistenceContext(unitName = "UnistartPU")
    private EntityManager em;

    public SchoolFacadeREST() {
        super(School.class);
        em = PersistenceUtils.getEntityManger();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(School entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, School entity) {
        em.getTransaction().begin();
        super.edit(entity);
        em.getTransaction().commit();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public School find(@PathParam("id") Integer id) {
        School sch = super.find(id);
        if (sch != null) {
            sch.ratesHandler = Constants.GENERATE;
            sch.eInfosHandler = Constants.GENERATE;
            sch.branchsHandler = Constants.GENERATE;
        }
        return sch;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<School> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<School> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    //CUSTOM
    //FILTER SCHOOL
    @GET
    @Path("filter-school")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<School> findSchool(
            @QueryParam("schoolName") String schoolName,
            @QueryParam("sjCode") String sjCode,
            @QueryParam("minPoint") String minPoint,
            @QueryParam("typeId") String typeId,
            @QueryParam("fieldCode") String fieldCode,
            @QueryParam("location") String location) {
        SchoolDAO dao = new SchoolDAO();
        List<School> list = dao.filterSchool(schoolName, sjCode, minPoint, typeId, fieldCode, location, em);
        return list;
    }
     @POST
    @Path("filter-school2")
    @Consumes( MediaType.APPLICATION_JSON)
    public String findSchool2(String[] school){
         System.out.println("name : " + school);
         System.out.println("length"+school.length);
        return school[0];
    }
    @POST
    @Path("filter-school2a")
    @Consumes( MediaType.APPLICATION_JSON)
    public String findSchool2a(FilterSchool entity){
         System.out.println("name : " + entity.getSchoolName());
         for(String location : entity.getLocation()){
             System.out.println("location " + location);
         }
          for(String field : entity.getField()){
             System.out.println("field " + field);
         }
           for(String sjcombi : entity.getSjCombi()){
             System.out.println("sjcombi " + sjcombi);
         }
            for(String type : entity.getTypeSchool()){
             System.out.println("type " + type);
         }
            System.out.println("point" + entity.getMinPoint());
        return entity.getSchoolName();
    }
     @GET
    @Path("filter-school3")
    @Produces( MediaType.APPLICATION_JSON)
    public String findSchool3( @QueryParam("schoolName") String schoolName){
       String[] name= schoolName.split(",");
       for(int i=0;i<name.length;i++){
           System.out.println("1 : "+name[i]);
       }
         System.out.println("name : " + schoolName);
        return schoolName;
    }

    public void refresh(School school){
        em.refresh(school);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
