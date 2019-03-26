/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ctrackerws.Credential;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author zakhar
 */
@Stateless
@Path("ctrackerws.credential")
public class CredentialFacadeREST extends AbstractFacade<Credential> {

    @PersistenceContext(unitName = "CalorieTrackerWSPU")
    private EntityManager em;

    public CredentialFacadeREST() {
        super(Credential.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Credential entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Credential entity) {
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
    public Credential find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("findByUsername/{username}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Credential> findByUsername(@PathParam("username") String username) {
        Query query = em.createNamedQuery("Credential.findByUsername");
        query.setParameter("username", username);
        return query.getResultList();
    }

    @GET
    @Path("findByPasswordHash/{passwordHash}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Credential> findByPasswordHash(@PathParam("passwordHash") String passwordHash) {
        Query query = em.createNamedQuery("Credential.findByPasswordHash");
        query.setParameter("passwordHash", passwordHash);
        return query.getResultList();
    }

    @GET
    @Path("findBySignUpDate/{signUpDate}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Credential> findBySignUpDate(@PathParam("signUpDate") String signUpDate) throws ParseException {
        Query query = em.createNamedQuery("Credential.findBySignUpDate");
        query.setParameter("signUpDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(signUpDate));
        return query.getResultList();
    }

    @GET
    @Path("findByUsernameAndPasswordHash/{username}/{passwordHash}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Credential> findByUsernameAndPasswordHash(@PathParam("username") String username,
            @PathParam("passwordHash") String passwordHash) {
        TypedQuery<Credential> query = em.createQuery("SELECT c FROM Credential c WHERE c.username = :username AND c.passwordHash = :passwordHash", Credential.class);
        query.setParameter("username", username);;
        query.setParameter("passwordHash", passwordHash);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUsernameAndEmail/{username}/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Credential> findByUsernameAndEmail(@PathParam("username") String username,
            @PathParam("email") String email) {
        TypedQuery<Credential> query = em.createQuery("SELECT c FROM Credential c WHERE c.username = :username AND c.userId.email = :email", Credential.class);
        query.setParameter("username", username);;
        query.setParameter("email", email);
        return query.getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
