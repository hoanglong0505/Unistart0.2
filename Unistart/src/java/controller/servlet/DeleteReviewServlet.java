/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.gson.Gson;
import controller.security.GoogleVerifier;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Rate;
import model.School;
import model.Users;
import network.HttpResponse;
import restful.AverageRateFacadeREST;
import restful.PersistenceUtils;
import restful.RateFacadeREST;
import restful.SchoolFacadeREST;
import restful.UsersFacadeREST;

/**
 *
 * @author TNT
 */
@WebServlet(name = "DeleteReviewServlet", urlPatterns = {"/delete-review"})
public class DeleteReviewServlet extends HttpServlet {

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
                    if (u != null) {
                        RateFacadeREST rRest = new RateFacadeREST();
                        rate = rRest.find(rate.getRateId());
                        if (u.getUserId().equals(rate.getUser().getUserId())) {
                            School sch = rate.getSchool();
                            rRest.remove(rate.getRateId());
                            uRest.refresh(u);
                            AverageRateFacadeREST avgRest = new AverageRateFacadeREST();
                            avgRest.updateAverageRate(sch);
                            schRest.refresh(schRest.find(sch.getSchoolId()));
                            status = 200;
                        }
                    }
                }
            }
        } catch (GeneralSecurityException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            Logger.getLogger(SendReviewServlet.class
                    .getName()).log(Level.SEVERE, null, e);
            status = -1;
        }

        res.setStatus(status);
        switch (status) {
            case 403:
                res.setContent("Bạn không có quyền này");
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
