/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import controller.login.GoogleLogin;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
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
import model.Users;

/**
 *
 * @author TNT
 */
@javax.ejb.Stateless
@Path("model.users")
public class UserFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "UnistartPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(Users.class);
        em = Persistence.createEntityManagerFactory("UnistartPU").createEntityManager();
    }

    @POST
    @Override
    @Path("add-user")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Users entity) {
<<<<<<< HEAD
        System.out.println(entity.toString());
=======
>>>>>>> 41b24756cc7f821ee777a6ef3583b43150fcd482
        em.getTransaction().begin();
        super.create(entity);
        em.getTransaction().commit();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Users entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Users find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
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

    @POST
    @Path("get-g-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Users getUser(String idToken) throws GeneralSecurityException, IOException {
        GoogleIdToken.Payload payload = GoogleLogin.verified(idToken);
        if (payload != null) {
            Users u = new Users();
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            
            u.setName(name);
            u.setAvatar(pictureUrl);
            u.setEmail(payload.getEmail());
            return u;
        }
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}