/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.marcanti.ecommerce.dao.AbstractGenericDAO;
import com.marcanti.ecommerce.dao.FilleulDAO;
import com.marcanti.ecommerce.model.Filleul;
import com.marcanti.ecommerce.model.Membre;


/**
 *
 * @author RK
 */
public class FilleulDAOImpl extends AbstractGenericDAO<Filleul> implements FilleulDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	public FilleulDAOImpl() {
		super(Filleul.class);
	}

	@Override
	public List<Membre> getFilleulsList(Membre parrain) {
		return em.createNamedQuery("Filleul.getFilleulsList", Membre.class).setParameter("idMembreParrain", parrain).getResultList();
	}

	@Override
	public Filleul getFilleul(Long idFilleul) {
		Filleul filleul=null;
		try {
			filleul = em.createNamedQuery("Filleul.findByIdFilleul", Filleul.class).setParameter("idFilleul", idFilleul).getSingleResult();
		} catch (Exception e) {
		} 
		return filleul;
	}
	
}
