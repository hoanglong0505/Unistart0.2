/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import app.Constants;
import dao.SchoolDAO;
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
        School sch = super.find(id);
        sch.ratesHandler = Constants.GENERATE;
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
