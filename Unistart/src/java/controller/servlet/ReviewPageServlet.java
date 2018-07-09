/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RateCriteria;
import model.School;
import restful.RateCriteriaFacadeREST;
import restful.SchoolFacadeREST;

/**
 *
 * @author TNT
 */
@WebServlet(name = "ReviewPageServlet", urlPatterns = {"/secured/review"})
public class ReviewPageServlet extends HttpServlet {

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
        Integer schoolId = null;
        try {
            schoolId = Integer.parseInt(request.getParameter("schoolId"));
            SchoolFacadeREST schRest = new SchoolFacadeREST();
            School sch = schRest.find(schoolId);
            if (sch != null) {
                RateCriteriaFacadeREST rcRest = new RateCriteriaFacadeREST();
                List<RateCriteria> rcList = rcRest.findAll();
                request.setAttribute("school", sch);
                request.setAttribute("criterias", rcList);
                request.getRequestDispatcher("review.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Unistart/");
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
        doGet(request, response);
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
