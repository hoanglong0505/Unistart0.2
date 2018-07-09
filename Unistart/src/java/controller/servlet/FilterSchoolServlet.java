/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import dao.SchoolDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.School;

/**
 *
 * @author TNT
 */
@WebServlet(name = "FilterSchoolServlet", urlPatterns = {"/filter-school"})
public class FilterSchoolServlet extends HttpServlet {

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
        String schoolName = request.getParameter("schoolName");
        if (schoolName != null && schoolName.trim().length() == 0) {
            schoolName = null;
        }
        
        Integer locationId = null;
        try {
            locationId = Integer.parseInt(request.getParameter("location"));
        } catch (Exception e) {
        }

        String subjectCode = request.getParameter("sjCode");
        if (subjectCode.equals("all")) {
            subjectCode = null;
        }

        Float minPoint = null;
        try {
            minPoint = Float.parseFloat(request.getParameter("minPoint"));
        } catch (Exception e) {
        };

        Integer typeId = null;
        try {
            typeId = Integer.parseInt(request.getParameter("typeId"));
        } catch (Exception e) {
        };

        String fieldCode = null;
        fieldCode = request.getParameter("fieldCode");
        if (fieldCode.equals("all")) {
            fieldCode = null;
        }

        EntityManager em = Persistence.createEntityManagerFactory("UnistartPU").createEntityManager();
        List<School> schList = new SchoolDAO().getSchoolList(schoolName, subjectCode, minPoint, typeId, fieldCode, locationId, em);
        
        request.setAttribute("schools", schList);

        request.getRequestDispatcher("home").forward(request, response);
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
