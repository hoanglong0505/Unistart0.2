/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import network.HttpResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.gson.Gson;
import controller.security.GoogleVerifier;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AverageRate;
import model.AverageRatePK;
import model.business.JDBCConnector;
import model.Rate;
import model.RateDetail;
import model.School;
import model.Users;
import restful.AverageRateFacadeREST;
import restful.PersistenceUtils;
import restful.SchoolFacadeREST;
import restful.UsersFacadeREST;

/**
 *
 * @author TNT
 */
@WebServlet(name = "SendReviewServlet", urlPatterns = {"/send-review"})
public class SendReviewServlet extends HttpServlet {

    private EntityManager em = PersistenceUtils.getEntityManger();
    private String content;
    private int status;

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

    private boolean checkTodayReview(Users u, School sch) {
        String sql = "SELECT RateId FROM Rate WHERE UserId=? AND SubmitDate = CONVERT(Date,GETDATE())";
        Query q = em.createNativeQuery(sql);
        q.setParameter(1, u.getUserId());
        List res = q.getResultList();
        if (res.size() > 0) {
            status = 409;
            content = "Chỉ được review 1 lần 1 ngày";
            return false;
        }
        return true;
    }

    private boolean checkValid(Rate r) {

        if (r.getTitle() == null || r.getTitle().length() == 0) {
            status = 409;
            content = "Không được để trống tựa đề";
            return false;
        }

        return true;
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
        HttpResponse res = new HttpResponse();
        response.addHeader("Content-Type", "application/json");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        status = 403;

        try {
            BufferedReader inp = request.getReader();
            String rateJson = inp.readLine();
            Gson gson = new Gson();
            Rate rate = gson.fromJson(rateJson, Rate.class);

            Users u = rate.getUser();
            String idToken = u.getIdToken();
            if (idToken != null) {
                GoogleIdToken.Payload payload = GoogleVerifier.verify(idToken);
                if (payload != null) {
                    String userId = payload.getSubject();

                    UsersFacadeREST uRest = new UsersFacadeREST();
                    SchoolFacadeREST schRest = new SchoolFacadeREST();
                    u = uRest.find(userId);
                    if (u != null && checkTodayReview(u, rate.getSchool()) && checkValid(rate)) {
//                        if (true) {
                        //------
                        rate.setUser(u);
                        Date today = new Date();
                        rate.setSubmitDate(today);
                        rate.setSubmitTime(today);
                        //------

                        em.getTransaction().begin();
                        Collection<RateDetail> rds = rate.getRateDetails();
                        rate.setRateDetails(null);
                        em.persist(rate);
                        em.flush();
                        for (RateDetail rd : rds) {
                            rd.getRateDetailPK().setRateId(rate.getRateId());
                        }
                        rate.setRateDetails(rds);
                        School school = schRest.find(rate.getSchool().getSchoolId());
                        school.getRates().add(rate);
                        rate.setSchool(school);
                        em.merge(rate);
                        em.merge(school);
                        em.flush();
                        em.getTransaction().commit();

                        schRest.refresh(school);
                        AverageRateFacadeREST avgRest = new AverageRateFacadeREST();
                        avgRest.updateAverageRate(school);
                        schRest.refresh(school);

                        status = 200;
                    }
                }

            }

        } catch (GeneralSecurityException ex) {
            Logger.getLogger(SendReviewServlet.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (Exception e) {
            Logger.getLogger(SendReviewServlet.class
                    .getName()).log(Level.SEVERE, null, e);
            status = -1;
        }

        res.setStatus(status);
        switch (status) {
            case 403:
                res.setContent("Hãy đăng nhập để tiếp tục");
                break;
            case 409:
                res.setContent(content);
                break;
            case -1:
                res.setContent("Lỗi không xác định");
                break;
        }
        Gson gson = new Gson();

        response.getWriter()
                .write(gson.toJson(res));

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
