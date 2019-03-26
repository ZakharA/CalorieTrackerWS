/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ctrackerws.AppUser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@Path("ctrackerws.appuser")
public class AppUserFacadeREST extends AbstractFacade<AppUser> {

    @PersistenceContext(unitName = "CalorieTrackerWSPU")
    private EntityManager em;

    public AppUserFacadeREST() {
        super(AppUser.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(AppUser entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, AppUser entity) {
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
    public AppUser find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AppUser> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("findByName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppUser> findByName(@PathParam("name") String name) {
        Query query = em.createNamedQuery("AppUser.findByName");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @GET
    @Path("findBySurname/{surname}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppUser> findBySurname(@PathParam("surname") String surname) {
        Query query = em.createNamedQuery("AppUser.findBySurname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @GET
    @Path("findByEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppUser> findByEmail(@PathParam("email") String email) {
        Query query = em.createNamedQuery("AppUser.findByEmail");
        query.setParameter("email", email);
        return query.getResultList();
    }

    @GET
    @Path("findByDob/{dob}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppUser> findByDob(@PathParam("dob") String dob) throws ParseException {
        Query query = em.createNamedQuery("AppUser.findByDob");
        query.setParameter("dob", new SimpleDateFormat("yyyy-MM-dd").parse(dob));
        return query.getResultList();
    }

    @GET
    @Path("findByHeight/{height}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppUser> findByHeight(@PathParam("height") Double height) {
        Query query = em.createNamedQuery("AppUser.findByHeight");
        query.setParameter("height", height);
        return query.getResultList();
    }

    @GET
    @Path("findByWeight/{weight}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppUser> findByWeight(@PathParam("weight") Double weight) {
        Query query = em.createNamedQuery("AppUser.findByWeight");
        query.setParameter("weight", weight);
        return query.getResultList();
    }

    @GET
    @Path("findByGender/{gender}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppUser> findByGender(@PathParam("gender") String gender) {
        Query query = em.createNamedQuery("AppUser.findByGender");
        query.setParameter("gender", gender);
        return query.getResultList();
    }

    @GET
    @Path("findByAddress/{address}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppUser> findByAddress(@PathParam("address") String address) {
        Query query = em.createNamedQuery("AppUser.findByAddress");
        query.setParameter("address", address);
        return query.getResultList();
    }

    @GET
    @Path("findByPostcode/{postcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppUser> findByPostcode(@PathParam("postcode") String postcode) {
        Query query = em.createNamedQuery("AppUser.findByPostcode");
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }

    @GET
    @Path("findByLevelOfActivity/{levelOfActivity}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppUser> findByLevelOfActivity(@PathParam("levelOfActivity") Integer levelOfActivity) {
        Query query = em.createNamedQuery("AppUser.findByLevelOfActivity");
        query.setParameter("levelOfActivity", levelOfActivity);
        return query.getResultList();
    }

    @GET
    @Path("findByStepsPerMile/{stepsPerMile}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppUser> findByStepsPerMile(@PathParam("stepsPerMile") Integer stepsPerMile) {
        Query query = em.createNamedQuery("AppUser.findByStepsPerMile");
        query.setParameter("stepsPerMile", stepsPerMile);
        return query.getResultList();
    }
    
    @GET
    @Path("caloriesBurnedPerStep/{userId}")
    @Produces({MediaType.TEXT_PLAIN})
    public float caloriesBurnedPerStep(@PathParam("userId") Integer userId){
        AppUser user = super.find(userId);
        float userWeight = user.getWeight();
        Integer userStepsPerMile = user.getStepsPerMile();
        return (covertKgToLbs(userWeight) * 0.49f) / userStepsPerMile;
    }
    
    @GET
    @Path("calculateBMR/{userId}")
    @Produces({MediaType.TEXT_PLAIN})
    public float calculateBMR(@PathParam("userId") Integer userId){
        AppUser user = super.find(userId);
        float userWeight = user.getWeight();
        float userHeight = user.getHeight();
        String userGender = user.getGender();
        Integer userAge = calculateAge(user.getDob());
        if (userGender.equalsIgnoreCase("Male"))
            return (13.75f * userWeight) + (5.003f * userHeight) - (6.755f * userAge) + 66.5f;
        else
            return (9.563f * userWeight) + (1.85f * userHeight) - (4.676f * userAge) + 655.1f;
    }
    private Integer calculateAge(Date dob){
        Integer currentYear = LocalDate.now().getYear();
        Integer dobYear = dob.toInstant().atZone(ZoneId.systemDefault()).getYear();
        return  currentYear - dobYear;
    }
    
    private float covertKgToLbs(float weight){
        return weight * 2.205f;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
