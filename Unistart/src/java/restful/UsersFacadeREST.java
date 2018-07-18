/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import app.Constants;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import controller.security.GoogleVerifier;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Rate;
import model.Users;

/**
 *
 * @author TNT
 */
@Stateless
@Path("model.users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "UnistartPU")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
        em = PersistenceUtils.getEntityManger();
    }

//    @POST
//    @Override
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Users entity) {
        em.getTransaction().begin();
        super.create(entity);
        em.getTransaction().commit();
    }

//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Users entity) {
        super.edit(entity);
    }

//    @DELETE
//    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Users find(@PathParam("id") String id) {
        Users u = super.find(id);
        u.ratesHandler = Constants.GENERATE;
        return u;
    }

    @POST
    @Path("get-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Users getUser(Users user) throws Exception {
        String requestUserToken = user.getIdToken();
        String responseUserId = user.getUserId();

        UsersFacadeREST uRest = new UsersFacadeREST();
        Users resUser = uRest.find(responseUserId);
        if (requestUserToken != null) {
            GoogleIdToken.Payload payload = GoogleVerifier.verify(requestUserToken);
            if (payload != null) {
                String userId = payload.getSubject();
                user = uRest.find(userId);
                if (user != null) {
                    if (!resUser.getUserId().equals(userId)) {
                        resUser.hideRate = true;
                    }
                }
            }
        } else {
            resUser.hideRate = true;
        }
        resUser.ratesHandler = Constants.GENERATE;

        return resUser;
    }

//    @GET
//    @Override
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    public void refresh(Users u) {
        em.refresh(u);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
