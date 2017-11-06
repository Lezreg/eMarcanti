/*
	 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao.impl;

import java.util.List;

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

import org.springframework.stereotype.Repository;

import com.marcanti.ecommerce.dao.AbstractGenericDAO;
import com.marcanti.ecommerce.dao.VCatalogueAdminDAO;
import com.marcanti.ecommerce.model.Categorie;
import com.marcanti.ecommerce.model.VCatalogueAdmin;

/**
 *
 * @author lezreg
 */
@Repository
public class VCatalogueAdminDAOImpl extends AbstractGenericDAO<Categorie> implements VCatalogueAdminDAO {

	@PersistenceContext
    private EntityManager em;

    public VCatalogueAdminDAOImpl() {
        super(Categorie.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Categorie entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
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
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Categorie find(@PathParam("id") Short id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Categorie> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Categorie> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
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

	/*@Override
	public List<VCatalogueAdmin> getCategorieList() {
		
		List<VCatalogueAdmin> listCatalogue = new ArrayList<VCatalogueAdmin>();
		VCatalogueAdmin catalogue;
		Query query = em.createNativeQuery("select * FROM v_catalogue_admin ");
		List<Object[]> resuList = query.getResultList();
		for (Object[] entity : resuList) {
			catalogue = new VCatalogueAdmin();
			catalogue.setMarqueNom((String)entity[1]);
			System.out.println("marque nom : " + (String)entity[1]);
			catalogue.setProduitDescription((String)entity[2]);
			System.out.println("produit description : " + (String)entity[2]);
			catalogue.setSousCategorieNom((String)entity[3]);
			catalogue.setPrixPublic((BigDecimal)entity[4]);
			listCatalogue.add(catalogue);
		}
		return listCatalogue;
	}*/

	@Override
	public List<VCatalogueAdmin> getCatalogueList() {
		return em.createNamedQuery("VCatalogueAdmin.findAll", VCatalogueAdmin.class).getResultList();
	}

    
}
