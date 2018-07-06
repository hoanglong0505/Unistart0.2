/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import controller.security.GoogleVerifier;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;
import restful.UsersFacadeREST;

/**
 *
 * @author TNT
 */
public class LoginServlet extends HttpServlet {

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
            BufferedReader inp = request.getReader();
            String idToken = inp.readLine();

            GoogleIdToken.Payload payload = GoogleVerifier.verify(idToken);
            String userId = payload.getSubject();
            if (payload != null) {
                UsersFacadeREST uRest = new UsersFacadeREST();
                Users u = uRest.find(userId);
                if (u == null) {
                    u = new Users();
                    u.setUserId(userId);
                    u.setAvatar((String) payload.get("picture"));
                    u.setName((String) payload.get("name"));
                    u.setEmail((String) payload.getEmail());
                    uRest.create(u);
                }

                HttpSession session = request.getSession(true);
                session.setAttribute("user", u);

//                System.out.println(u);
                String nextPage = (String) session.getAttribute("nextPage");
                if (nextPage != null) {
                    response.getWriter().print(nextPage);
                    session.removeAttribute("nextPage");
                }
            }
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
