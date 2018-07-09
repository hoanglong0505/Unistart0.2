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
import javax.servlet.http.HttpSession;
import model.Field;
import model.Location;
import model.School;
import model.SubjectCombination;
import model.Type;
import restful.FieldFacadeREST;
import restful.LocationFacadeREST;
import restful.SchoolFacadeREST;
import restful.SubjectCombinationFacadeREST;
import restful.TypeFacadeREST;

/**
 *
 * @author TNT
 */
@WebServlet(name = "HomePageServlet", urlPatterns = {"/home"})
public class HomePageServlet extends HttpServlet {

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

        if (request.getAttribute("sjCodes") == null) {
            SubjectCombinationFacadeREST sjRest = new SubjectCombinationFacadeREST();
            TypeFacadeREST tRest = new TypeFacadeREST();
            FieldFacadeREST fRest = new FieldFacadeREST();
            LocationFacadeREST lRest = new LocationFacadeREST();

            List<SubjectCombination> sjList = sjRest.findAll();
            List<Type> tList = tRest.findAll();
            List<Field> fList = fRest.findFieldType(3);
            List<Location> lList = lRest.findAll();
            
            request.setAttribute("sjCodes", sjList);
            request.setAttribute("types", tList);
            request.setAttribute("fields", fList);
            request.setAttribute("locations", lList);

        }

        if (request.getAttribute("schools") == null) {
            SchoolFacadeREST schRest = new SchoolFacadeREST();
            List<School> schList = schRest.findAll();
            request.setAttribute("schools",schList);

        }
        
        request.getRequestDispatcher("home.jsp").forward(request, response);

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
