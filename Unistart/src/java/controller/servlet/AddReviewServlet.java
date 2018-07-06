/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Rate;
import model.RateDetail;
import model.RateDetailPK;
import model.School;
import model.Users;
import restful.RateDetailFacadeREST;

/**
 *
 * @author TNT
 */
@WebServlet(name = "AddReviewServlet", urlPatterns = {"/pages/addreview"})
public class AddReviewServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            Gson g = new Gson();
//            request.setCharacterEncoding("utf-8");
            Rate rate = g.fromJson(request.getReader().readLine(), Rate.class);
            //=====
            HttpSession session = request.getSession(false);
            System.out.println(session);
            if (session != null) {
                Users u = (Users) session.getAttribute("user");
                rate.setUserId(u.getUserId());
                School sch = (School) session.getAttribute("school");
                rate.setSchoolId(sch.getSchoolId());

                EntityManager em = Persistence.createEntityManagerFactory("UnistartPU").createEntityManager();

                em.getTransaction().begin();
                String sql = "INSERT INTO Rate(SchoolId,UserId,Title,Pros,Cons,Experience,Encourage,Anonymous) "
                        + "OUTPUT INSERTED.RateId VALUES(?,?,?,?,?,?,?,?)";

                System.out.println(g.toJson(rate));
                Query q = em.createNativeQuery(sql);
                q.setParameter(1, rate.getSchoolId());
                q.setParameter(2, rate.getUserId());
                q.setParameter(3, rate.getTitle());
                q.setParameter(4, rate.getPros());
                q.setParameter(5, rate.getCons());
                q.setParameter(6, rate.getExperience());
                q.setParameter(7, rate.getEncourage());
                q.setParameter(8, rate.getAnonymous());

                Integer rateId = (Integer) q.getSingleResult();

                System.out.println("REVIEW RATE ID: " + rateId);
                for (RateDetail r : rate.getRateDetails()) {
                    RateDetailPK rdPK = new RateDetailPK();
                    rdPK.setCriteriaId(r.getCriteriaId());
                    rdPK.setRateId(rateId);
                    r.setRateDetailPK(rdPK);
                    em.persist(r);
                }

                em.getTransaction().commit();
            }
            response.getWriter().print("/Unistart/");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
