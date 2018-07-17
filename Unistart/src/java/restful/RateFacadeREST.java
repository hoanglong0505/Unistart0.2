/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Rate;
import model.business.JDBCConnector;

/**
 *
 * @author TNT
 */
@Stateless
@Path("model.rate")
public class RateFacadeREST extends AbstractFacade<Rate> {

    @PersistenceContext(unitName = "UnistartPU")
    private EntityManager em;

    public RateFacadeREST() {
        super(Rate.class);
        em = PersistenceUtils.getEntityManger();
    }

//    @POST
    @Override
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Rate entity) {
        em.getTransaction().begin();
        super.create(entity);
        em.getTransaction().commit();
    }

//    @PUT
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public void edit(Rate entity) {
        em.getTransaction().begin();
        super.edit(entity);
        em.getTransaction().commit();
    }

//    @DELETE
//    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Rate find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rate> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rate> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    //custom
    public void setRateAverageValue(Rate r) {
        String sql = "SELECT AVG (rd.Value) as averageValue FROM Rate r\n"
                + "INNER JOIN RateDetail rd ON r.RateId = rd.RateId\n"
                + "WHERE r.RateId = ?\n"
                + "GROUP BY r.RateId";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try (Connection c = JDBCConnector.connect()) {
            ps = c.prepareStatement(sql);

            ps.setInt(1, r.getRateId());

            rs = ps.executeQuery();
            if (rs.next()) {
                r.setAverageValue(rs.getDouble(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(RateFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RateFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
