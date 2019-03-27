/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ctrackerws.Consumption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Path("ctrackerws.consumption")
public class ConsumptionFacadeREST extends AbstractFacade<Consumption> {

    @PersistenceContext(unitName = "CalorieTrackerWSPU")
    private EntityManager em;

    public ConsumptionFacadeREST() {
        super(Consumption.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Consumption entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Consumption entity) {
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
    public Consumption find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("findByUserId/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Consumption> findByUserId(@PathParam("userId") Integer userid) {
        Query query = em.createNamedQuery("Consumption.findByUserId");
        query.setParameter("userId", userid);
        return query.getResultList();
    }

    @GET
    @Path("findByFoodId/{foodId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Consumption> findByFoodId(@PathParam("foodId") Integer foodId) {
        Query query = em.createNamedQuery("Consumption.findByFoodId");
        query.setParameter("foodId", foodId);
        return query.getResultList();
    }

    @GET
    @Path("findByDate/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Consumption> findByDate(@PathParam("date") String date) throws ParseException {
        Query query = em.createNamedQuery("Consumption.findByDate");
        query.setParameter("date", new SimpleDateFormat("yyyy-MM-dd").parse(date));
        return query.getResultList();
    }

    @GET
    @Path("findByNumberOfServings/{numberOfServings}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Consumption> findByNumberOfServings(@PathParam("numberOfServings") Integer numberOfServings) {
        Query query = em.createNamedQuery("Consumption.findByNumberOfServings");
        query.setParameter("numberOfServings", numberOfServings);
        return query.getResultList();
    }

    @GET
    @Path("findByUseridAndFoodname/{userId}/{foodname}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Consumption> findByUseridAndFoodname(@PathParam("userId") Integer userId, @PathParam("foodname") String foodname) {
        Query query = em.createNamedQuery("Consumption.findByUseridAndFoodname");
        query.setParameter("userId", userId);
        query.setParameter("foodname", foodname);
        return query.getResultList();
    }

    @GET
    @Path("findByUseridAndDate/{userId}/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Consumption> findByUseridAndDate(@PathParam("userId") Integer userId, @PathParam("date") String date) throws ParseException {
        TypedQuery query = em.createQuery("SELECT c FROM Consumption c WHERE c.userId.userId = :userId AND c.date = :date", Consumption.class);
        query.setParameter("userId", userId);
        query.setParameter("date", new SimpleDateFormat("yyyy-MM-dd").parse(date));
        return query.getResultList();
    }

    @GET
    @Path("totalCaloriesComsumed/{userId}/{date}")
    @Produces({MediaType.TEXT_PLAIN})
    public int totalCaloriesComsumed(@PathParam("userId") Integer userId, @PathParam("date") String date) throws ParseException {
        int totalCaloriesConsumed = 0;
        List<Consumption> consumedFoodList = findByUseridAndDate(userId, date);
        if (!consumedFoodList.isEmpty()) {
            for (Consumption consumption : consumedFoodList) {
                totalCaloriesConsumed += consumption.getNumberOfServings() * consumption.getFoodId().getCalorieAmount();
            }
        }
        return totalCaloriesConsumed;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
