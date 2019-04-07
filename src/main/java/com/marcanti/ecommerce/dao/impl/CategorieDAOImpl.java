/*
	 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao.impl;

import java.util.List;

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

import org.springframework.stereotype.Repository;

import com.marcanti.ecommerce.dao.AbstractGenericDAO;
import com.marcanti.ecommerce.dao.CategorieDAO;
import com.marcanti.ecommerce.model.Categorie;

/**
 *
 * @author lezreg
 */
@Repository
public class CategorieDAOImpl extends AbstractGenericDAO<Categorie> implements CategorieDAO {

	@PersistenceContext
	private EntityManager em;

	public CategorieDAOImpl() {
		super(Categorie.class);
	}

	@POST
	@Override
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void create(Categorie entity) {
		super.create(entity);
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void edit(@PathParam("id") Short id, Categorie entity) {
		super.edit(entity);
	}

	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") Short id) {
		super.remove(super.find(id));
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Categorie find(@PathParam("id") Short id) {
		return super.find(id);
	}

	@GET
	@Override
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Categorie> findAll() {
		return super.findAll();
	}

	@GET
	@Path("{from}/{to}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Categorie> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
		return super.findRange(new int[] { from, to });
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String countREST() {
		return String.valueOf(super.count());
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<Categorie> getCategorieList() {
		return em.createNamedQuery("Categorie.findAll", Categorie.class).getResultList();
	}

	@Override
	public Categorie getCategorie(Categorie categorie) {
		// return em.createNamedQuery("Categorie.findByIdCategorie",
		// Categorie.class).setParameter("idCategorieProduit",
		// categorie.getIdCategorie()).getSingleResult();
		return em.createNamedQuery("Categorie.findByIdCategorie", Categorie.class)
				.setParameter("idCategorie", categorie.getIdCategorie()).getSingleResult();
	}

	@Override
	public void insertCategorie(Categorie categorie) {
		Query query = em.createNativeQuery("INSERT INTO categorie (categorieNom, categorieCode) VALUES (?,?)")
				.setParameter(1, categorie.getCategorieNom()).setParameter(2, categorie.getCategorieCode());
		query.executeUpdate();
		// em.persist(categorie);
	}

	@Override
	public void updateCategorie(Categorie categorie) {
		Query query = em
				.createNativeQuery("UPDATE categorie SET categorieNom=?, " + "categorieCode=? " + "WHERE idCategorie=?")
				.setParameter(1, categorie.getCategorieNom()).setParameter(2, categorie.getCategorieCode())
				.setParameter(3, categorie.getIdCategorie().shortValue());
		query.executeUpdate();
		// em.persist(categorie);
	}

}
