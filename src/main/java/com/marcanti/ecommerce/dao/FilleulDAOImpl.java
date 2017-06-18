/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.marcanti.ecommerce.model.Filleul;
import com.marcanti.ecommerce.model.Membre;


/**
 *
 * @author RK
 */
@Repository
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
	public List<Membre> getFilleulsList(Membre idMembreParrain) {
		return em.createNamedQuery("Filleul.getFilleulsList", Membre.class).setParameter("idMembreParrain", idMembreParrain).getResultList();
	}
    
}
