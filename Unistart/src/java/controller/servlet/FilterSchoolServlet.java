/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import controller.filter.HomePageFilter;
import dao.SchoolDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
        Integer locationId = null;
        try {
            locationId = Integer.parseInt(request.getParameter("location"));
        } catch (Exception e) {
        }
        System.out.println(locationId);
        String subjectCode = null;
        subjectCode = request.getParameter("sjCode");
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

        List<School> schList=new SchoolDAO().getSchoolList(subjectCode, minPoint, typeId, fieldCode, locationId);
        request.setAttribute("schools",schList);
        
        new HomePageFilter().setData(request);
        
        request.getRequestDispatcher("/").forward(request, response);

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
