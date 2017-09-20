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

import com.marcanti.ecommerce.dao.AbstractGenericDAO;
import com.marcanti.ecommerce.dao.MarqueDAO;
import com.marcanti.ecommerce.model.Categorie;
import com.marcanti.ecommerce.model.Marque;

/**
 *
 * @author admin
 */
public class MarqueDAOImpl extends AbstractGenericDAO<Marque> implements MarqueDAO {

	@PersistenceContext
    private EntityManager em;

    public MarqueDAOImpl() {
        super(Marque.class);
    }

    @Override
    public void create(Marque entity) {
        super.create(entity);
    }

	public void edit(Short id, Marque entity) {
        super.edit(entity);
    }

	public void remove(Short id) {
        super.remove(super.find(id));
    }

	public Marque find(Short id) {
        return super.find(id);
    }

    @Override
    public List<Marque> findAll() {
        return super.findAll();
    }

	public List<Marque> findRange(Integer from, Integer to) {
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
	public List<Marque> getMarqueList() {
		return em.createNamedQuery("Marque.findAll", Marque.class).getResultList();
	}

	@Override
	public Marque getMarque(Marque marque) {
		return em.createNamedQuery("Marque.findByIdMarque", Marque.class).setParameter("idMarque", marque.getIdMarque()).getSingleResult();
	}

	@Override
	public void insertMarque(Marque marque) {
		Query query = em.createNativeQuery("INSERT INTO marque (marqueNom, commentaire, isFullAccess) VALUES (?,?,?)")
				.setParameter(1, marque.getMarqueNom())
				.setParameter(2, marque.getCommentaire())
				.setParameter(3, marque.getIsFullAccess());
		query.executeUpdate();
		//em.persist(marque);
	}

	@Override
	public void updateMarque(Marque marque) {
		Query query = em.createNativeQuery("UPDATE marque SET "
				+ "marqueNom=?, "
				+ "commentaire=?, "
				+ "isFullAccess=? "
				+ "WHERE idMarque=?")
				.setParameter(1, marque.getMarqueNom())
				.setParameter(2, marque.getCommentaire())
				.setParameter(3, marque.getIsFullAccess())
				.setParameter(4, marque.getIdMarque().shortValue());
		query.executeUpdate();
		//em.persist(marque);
	}
    
}
