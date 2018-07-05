/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import dao.JpaJsonConverter;
import dao.RateDAO;
import dao.UserDAO;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Rate;

/**
 * REST Web Service
 *
 * @author Admin
 */
@Path("Users")
public class UsersResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsersResource
     */
    public UsersResource() {
    }
    @Path("/email/{email}")
    @GET
    @Produces()
    public String getUserByEmail(@PathParam("email") String email) throws SQLException, ClassNotFoundException {
    UserDAO dao = new UserDAO();                    
          String result = dao.CheckEmailExited(email);         
            return result;
    }
    @Path("/getRate/{userId}")
    @GET
    @Produces()
    public List<Rate> getUserByEmail(@PathParam("userId") int id) throws SQLException, ClassNotFoundException {
        RateDAO dao = new RateDAO();                    
         List<Rate> list= dao.GetRateByUserId(id);         
            return list;
    }
     @POST
   @Path("/createRate")
   @Produces()
   public String createRate(String rate){
        JpaJsonConverter jj = new JpaJsonConverter();
        System.out.println(jj.convertToDatabaseColumn(rate));
    
     return "";
   }
 
}
