/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import app.Constants;
import network.HttpResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.gson.Gson;
import controller.security.GoogleVerifier;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Rate;
import model.Report;
import model.ReportPK;
import model.Users;
import restful.PersistenceUtils;
import restful.RateFacadeREST;
import restful.UsersFacadeREST;

/**
 *
 * @author TNT
 */
@WebServlet(name = "SendReportServlet", urlPatterns = {"/send-report"})
public class SendReportServlet extends HttpServlet {

    private EntityManager em = PersistenceUtils.getEntityManger();
    private String content;
    private int status;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private boolean checkUserReport(Users u, Integer rateId) {
        String sql = "SELECT UserId FROM Report WHERE UserId=? AND RateId= ?";
        Query q = em.createNativeQuery(sql);
        q.setParameter(1, u.getUserId());
        q.setParameter(2, rateId);
        List res = q.getResultList();
        if (res.size() > 0) {
            content = "Bạn đã đánh giá bài viết này";
            status = 409;
            return false;
        }
        return true;
    }

    private boolean checkValid(Report rp) {
        if (rp.getRpContent() == null || rp.getRpContent().length() < 50) {
            status = 409;
            content = "Nội dung phải ít nhất 50 kí tự";
            return false;
        }
        if (rp.getUser() == null) {
            return false;
        }
        return true;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpResponse<String> res = new HttpResponse();
        response.addHeader("Content-Type", "application/json");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        status = 403;

        try {
            BufferedReader inp = request.getReader();
            String reportJson = inp.readLine();
            System.out.println(reportJson);
            Gson gson = new Gson();
            Report rp = gson.fromJson(reportJson, Report.class);
            rp.userHandler = Constants.GENERATE;
            
            if (checkValid(rp)) {
                Users u = rp.getUser();
                String idToken = u.getIdToken();
                if (idToken != null) {
                    GoogleIdToken.Payload payload = GoogleVerifier.verify(idToken);
                    if (payload != null) {
                        String userId = payload.getSubject();

                        UsersFacadeREST uRest = new UsersFacadeREST();
                        RateFacadeREST rRest = new RateFacadeREST();
                        u = uRest.find(userId);
                        if (u != null) {
                            Integer rateId = rp.getReportPK().getRateId();
                            if (checkUserReport(u, rateId)) {
                                rp.getReportPK().setUserId(u.getUserId());
                                em.getTransaction().begin();
                                Rate r = rRest.find(rateId);
                                rp.setRate(r);
                                rp.setUser(u);
                                em.persist(rp);
                                em.flush();
                                r.getReports().add(rp);
                                em.merge(r);
                                em.flush();
                                em.getTransaction().commit();

                                status = 200;
                            }
                        }
                    }

                }
            }
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(SendReportServlet.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (Exception e) {
            Logger.getLogger(SendReportServlet.class
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
        response.getWriter().write(gson.toJson(res));

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
