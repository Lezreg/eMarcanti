/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.marcanti.ecommerce.dao.AbstractGenericDAO;
import com.marcanti.ecommerce.dao.SousCategorieDAO;
import com.marcanti.ecommerce.model.Categorie;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.SousCategorie;

/**
 *
 * @author lezreg
 */
public class SousCategorieDAOImpl extends AbstractGenericDAO<SousCategorie> implements SousCategorieDAO {

	@PersistenceContext
    private EntityManager em;

    public SousCategorieDAOImpl() {
        super(SousCategorie.class);
    }

    @Override
    public void create(SousCategorie entity) {
        super.create(entity);
    }

	public void edit(Short id, SousCategorie entity) {
        super.edit(entity);
    }

	public void remove(Short id) {
        super.remove(super.find(id));
    }

	public SousCategorie find(Short id) {
        return super.find(id);
    }


    public List<SousCategorie> findAll() {
        return super.findAll();
    }

	public List<SousCategorie> findRange(Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

	@Override
	public List<SousCategorie> getSousCategorieList() {
		List<SousCategorie> listSousCategorie = new ArrayList<SousCategorie>();
		SousCategorie sousCategorie;
		Query query = em.createNativeQuery("select sc.idSousCategorie, sc.idCategorieProduit, sc.sousCategorieNom, c.categorieNom from sous_categorie sc, categorie c where sc.idCategorieProduit=c.idCategorie");
		List<Object[]> resuList = query.getResultList();
		for (Object[] entity : resuList) {
			sousCategorie = new SousCategorie();
			sousCategorie.setIdSousCategorie(((Short)(entity[0])).shortValue());
			sousCategorie.setIdCategorieProduit(new Categorie(((Short)(entity[1])).shortValue()));
			sousCategorie.setSousCategorieNom((String)entity[2]);
			sousCategorie.setCategorieNom((String)entity[3]);
			listSousCategorie.add(sousCategorie);
		}
		return listSousCategorie;
	}

	@Override
	public SousCategorie getSousCategorie(SousCategorie sousCategorie) {
		return em.createNamedQuery("SousCategorie.findByIdSousCategorie", SousCategorie.class).setParameter("idSousCategorie", sousCategorie.getIdSousCategorie()).getSingleResult();
	}

	@Override
	public void updateSousCategorie(SousCategorie sousCategorie) {
		Query query = em.createNativeQuery("UPDATE sous_categorie SET idCategorieProduit=?, "
				+ "sousCategorieNom=? "
				+ "WHERE idSousCategorie=?")
				.setParameter(1, sousCategorie.getIdCategorieProduit().getIdCategorie())
				.setParameter(2, sousCategorie.getSousCategorieNom())
				.setParameter(3, sousCategorie.getIdSousCategorie().shortValue());
		query.executeUpdate();
		//em.persist(sousCategorie);
		
	}

	@Override
	public void insertSousCategorie(SousCategorie sousCategorie) {
		Query query = em.createNativeQuery("INSERT INTO sous_categorie (idCategorieProduit, sousCategorieNom) VALUES (?,?)")
				.setParameter(1, sousCategorie.getIdCategorieProduit().getIdCategorie())
				.setParameter(2, sousCategorie.getSousCategorieNom());
		query.executeUpdate();
		//em.persist(sousCategorie);
	}

	@Override
	public List<SousCategorie> getSousCategorieByCategorieList(Categorie idCategorie) {
		return em.createNamedQuery("SousCategorie.findByIdCategorie", SousCategorie.class).setParameter("idCategorieProduit", idCategorie).getResultList();
	}
    
}
