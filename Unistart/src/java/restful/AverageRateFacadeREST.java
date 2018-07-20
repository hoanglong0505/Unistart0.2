/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import controller.servlet.SendReviewServlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import model.AverageRate;
import model.AverageRatePK;
import model.School;
import model.business.JDBCConnector;

/**
 *
 * @author TNT
 */
@Stateless
@Path("model.averagerate")
public class AverageRateFacadeREST extends AbstractFacade<AverageRate> {

    @PersistenceContext(unitName = "UnistartPU")
    private EntityManager em;

    private AverageRatePK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;schoolId=schoolIdValue;criteriaId=criteriaIdValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        model.AverageRatePK key = new model.AverageRatePK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> schoolId = map.get("schoolId");
        if (schoolId != null && !schoolId.isEmpty()) {
            key.setSchoolId(new java.lang.Integer(schoolId.get(0)));
        }
        java.util.List<String> criteriaId = map.get("criteriaId");
        if (criteriaId != null && !criteriaId.isEmpty()) {
            key.setCriteriaId(new java.lang.Integer(criteriaId.get(0)));
        }
        return key;
    }

    public AverageRateFacadeREST() {
        super(AverageRate.class);
        em = PersistenceUtils.getEntityManger();
    }

    public void beginTransaction() {
        em.getTransaction().begin();
    }

    public void commitTransaction() {
        em.getTransaction().commit();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(AverageRate entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, AverageRate entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        model.AverageRatePK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AverageRate find(@PathParam("id") PathSegment id) {
        model.AverageRatePK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AverageRate> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AverageRate> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    //custom
    //update average rate
    public void updateAverageRate(School sch) {
        String sql = "SELECT rd.CriteriaId,AVG(rd.Value) FROM RateDetail rd \n"
                + "INNER JOIN Rate r ON r.RateId = rd.RateId\n"
                + "INNER JOIN School s ON r.SchoolId = s.SchoolId\n"
                + "WHERE s.SchoolId=?\n"
                + "GROUP BY rd.CriteriaId;";
        try (Connection c = JDBCConnector.connect();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, sch.getSchoolId());
            ResultSet rs = ps.executeQuery();
            beginTransaction();
            boolean hasReview = false;
            while (rs.next()) {
                hasReview = true;
                AverageRatePK avgPK = new AverageRatePK(sch.getSchoolId(), rs.getInt(1));
                AverageRate avg = find(avgPK);
                if (avg == null) {
                    avg = new AverageRate();
                    avg.setAverageRatePK(avgPK);
                    avg.setAvgValue(rs.getDouble(2));
                    create(avg);
                } else {
                    avg.setAvgValue(rs.getDouble(2));
                    edit(avg);
                }
            }
            if (!hasReview) {
                sql = "DELETE FROM AverageRate WHERE SchoolId= ? ";
                Query q = em.createNativeQuery(sql);
                q.setParameter(1, sch.getSchoolId());
                q.executeUpdate();
            }
            
            System.out.println("has review: "+hasReview);
            commitTransaction();

        } catch (SQLException ex) {
            Logger.getLogger(SendReviewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
