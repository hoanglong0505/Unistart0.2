/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import dao.SchoolDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
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
import model.School;

/**
 *
 * @author TNT
 */
@javax.ejb.Stateless
@Path("model.school")
public class SchoolFacadeREST extends AbstractFacade<School> {

    @PersistenceContext(unitName = "UnistartPU")
    private EntityManager em;

    public SchoolFacadeREST() {
        super(School.class);
        em= Persistence.createEntityManagerFactory("UnistartPU").createEntityManager();
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
        super.edit(entity);
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
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<School> findAll() {
        return super.findAll();
    }
    
    // Get School by TypeId
    @GET
    @Path("schoolType.{typeId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<School> findSchoolByTypeId(@PathParam("typeId") Integer id) {
        SchoolDAO dao = new SchoolDAO();
        List<School> list =dao.getSchoolByTypeId(id);
        return list;
    }
    
    // Get School by MinPoint
    @GET
    @Path("schoolPoint.{minPoint}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<School> findSchoolByMinPoint(@PathParam("minPoint") Float point) {
        SchoolDAO dao = new SchoolDAO();
        List<School> list =dao.getSchoolByMinPoint(point);
        return list;
    }
    // Get School by FieldCode
    @GET
    @Path("schoolFcode.{fieldCode}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<School> findSchoolByFieldCode(@PathParam("fieldCode") String code) {
        SchoolDAO dao = new SchoolDAO();
        List<School> list =dao.getSchoolByFieldCode(code);
        return list;
    }
    // Get School by SjCombiCode
    @GET
    @Path("schoolSjcode.{code}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<School> findSchoolBySjCombiCode(@PathParam("code") String code) {
        SchoolDAO dao = new SchoolDAO();
        List<School> list =dao.getSchoolBySjCombiCode(code);
        return list;
    }
    // Get School by Location
    @GET
    @Path("schoolLocation.{code}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<School> findSchoolByLocation(@PathParam("code") int code) {
        SchoolDAO dao = new SchoolDAO();
        List<School> list =dao.getSchoolByLocation(code);
        return list;
    }
    
    // Get School 
    @GET
    @Path("schoolLocation")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<School> findSchool(@QueryParam(value = "sjCode") String sjCode , @QueryParam("minPoint") Float minPoint ,
            @QueryParam("typeID") Integer typeID , @QueryParam("fieldCode") String fieldCode ,@QueryParam("location") Integer location ) {
       
        SchoolDAO dao = new SchoolDAO();
        List<School> list =dao.getSchoolList(sjCode,minPoint,typeID,fieldCode,location);
        return list;
    }
    ///
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
