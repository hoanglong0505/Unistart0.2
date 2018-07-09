/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Rate;
import model.RateCriteria;
import model.RateDetail;
import model.RateDetailPK;
import model.School;
import model.Users;
import restful.RateCriteriaFacadeREST;
import restful.RateFacadeREST;
import restful.SchoolFacadeREST;

/**
 *
 * @author TNT
 */
@WebServlet(name = "SendReviewServlet", urlPatterns = {"/secured/send-review"})
public class SendReviewServlet extends HttpServlet {

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
            String title = null;
            String pros = null;
            String cons = null;
            String experience = null;
            Boolean encourage = false;
            Boolean anonymous = false;
            School school = null;
            Users u = (Users) request.getSession().getAttribute("user");
            SchoolFacadeREST sRest = new SchoolFacadeREST();

            if (u != null) {
                Map<Integer, Double> critVal = new HashMap<>();
                Enumeration<String> paraNames = request.getParameterNames();
                while (paraNames.hasMoreElements()) {
                    String field = paraNames.nextElement();
//                    System.out.println(request.getParameter(field));
                    switch (field) {
                        case "schoolId":
                            school = sRest.find(Integer.parseInt(request.getParameter(field)));
                            break;
                        case "title":
                            title = request.getParameter(field);
                            break;
                        case "pros":
                            pros = request.getParameter(field);
                            break;
                        case "cons":
                            cons = request.getParameter(field);
                            break;
                        case "experience":
                            experience = request.getParameter(field);
                            break;
                        case "encourage":
                            encourage = request.getParameter(field).equals("on");
                            break;
                        case "anonymous":
                            anonymous = request.getParameter(field).equals("on");
                            break;
                        default:
                            Integer criteriaId = Integer.parseInt(field.substring(1));
                            critVal.put(criteriaId, Double.parseDouble(request.getParameter(field)));
                            break;
                    }
                }

                //add to database
                EntityManager em = Persistence.createEntityManagerFactory("UnistartPU").createEntityManager();
                em.getTransaction().begin();
                Rate r = addRate(em, school, u, title, pros, cons, experience, encourage, anonymous);
                addRateDetails(em, critVal, r);
                school = r.getSchool();
                school.getRates().add(r);
                em.merge(school);
                em.getTransaction().commit();
//                em.refresh(school);
                
                response.sendRedirect("/Unistart/public/schoolinfo?schoolId=" + school.getSchoolId());
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Unistart/");
    }

    private Rate addRate(EntityManager em, School school, Users user,
            String title, String pros, String cons, String experience,
            Boolean encourage, Boolean anonymous) {
//        RateFacadeREST rRest= new RateFacadeREST();
        Rate r = new Rate();
        r.setSchool(school);
        r.setUser(user);
        r.setTitle(title);
        r.setPros(pros);
        r.setCons(cons);
        r.setExperience(experience);
        r.setEncourage(encourage);
        r.setAnonymous(anonymous);

        em.persist(r);
        em.flush();
        return r;
    }

    private void addRateDetails(EntityManager em, Map<Integer, Double> critVal, Rate r) {
        RateCriteriaFacadeREST rcRest=new RateCriteriaFacadeREST();
        Integer rateId = r.getRateId();
        for (Integer critId : critVal.keySet()) {
            RateDetailPK rdPK = new RateDetailPK(rateId, critId);
            RateDetail rd = new RateDetail();
            rd.setRateDetailPK(rdPK);
            rd.setValue(critVal.get(critId));
            
            em.persist(rd);
        }
        em.flush();
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
