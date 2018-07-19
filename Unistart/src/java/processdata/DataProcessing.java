/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processdata;

import controller.servlet.SendReviewServlet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.AverageRate;
import model.AverageRatePK;
import model.business.JDBCConnector;
import model.School;
import restful.AverageRateFacadeREST;
import restful.PersistenceUtils;
import restful.SchoolFacadeREST;

/**
 *
 * @author TNT
 */
public class DataProcessing {

    private static void deleteAllRates() {
        EntityManager em = PersistenceUtils.getEntityManger();
        em.getTransaction().begin();
        String sql = "DELETE FROM Rate";
        String sql2 = "DELETE FROM AverageRate ";
        Query q = em.createNativeQuery(sql);
        q.executeUpdate();
        q = em.createNativeQuery(sql2);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    private static void updateAllAverageRate() {
        SchoolFacadeREST schoolRest = new SchoolFacadeREST();
        AverageRateFacadeREST avgRest = new AverageRateFacadeREST();
        String sql = "SELECT rd.CriteriaId,AVG(rd.Value) FROM RateDetail rd \n"
                + "INNER JOIN Rate r ON r.RateId = rd.RateId\n"
                + "INNER JOIN School s ON r.SchoolId = s.SchoolId\n"
                + "WHERE s.SchoolId=?\n"
                + "GROUP BY rd.CriteriaId;";
        try (Connection c = JDBCConnector.connect();
                ResultSet rs = c.createStatement().executeQuery(sql);) {
            List<School> sList = schoolRest.findAll();

            for (School sch : sList) {
                avgRest.beginTransaction();
                while (rs.next()) {
                    AverageRatePK avgPK = new AverageRatePK(sch.getSchoolId(), rs.getInt(1));
                    AverageRate avg = avgRest.find(avgPK);
                    if (avg == null) {
                        avg = new AverageRate();
                        avg.setAverageRatePK(avgPK);
                        avg.setAvgValue(rs.getDouble(2));
                        avgRest.create(avg);
                    } else {
                        avg.setAvgValue(rs.getDouble(2));
                        avgRest.edit(avg);
                    }
                }
                avgRest.commitTransaction();
            }

        } catch (SQLException ex) {
            Logger.getLogger(SendReviewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        DataProcessing.deleteAllRates();
    }

}
