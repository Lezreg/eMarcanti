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
import com.marcanti.ecommerce.dao.MembreDAO;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.utils.ParfumUtils;

/**
 *
 * @author admin
 */
public class MembreDAOImpl extends AbstractGenericDAO<Membre> implements MembreDAO {

	@PersistenceContext
    private EntityManager em;

    public MembreDAOImpl() {
        super(Membre.class);
    }

    @Override
    public void create(Membre entity) {
        super.create(entity);
    }

	public void edit(Long id, Membre entity) {
        super.edit(entity);
    }

	public void remove(Long id) {
        super.remove(super.find(id));
    }


	public Membre find(Long id) {
        return super.find(id);
    }

    @Override
    public List<Membre> findAll() {
        return super.findAll();
    }

	public List<Membre> findRange(Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        return String.valueOf(super.count());
    }
    
    public Membre getMembre(Membre idMembre)
    {
    	return em.createNamedQuery("Membre.findByIdMembre", Membre.class).setParameter("idMembre", idMembre.getIdMembre()).getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

	@Override
	public void updateFilleul(Membre filleul) {
		Query query = em.createNativeQuery("UPDATE membre SET membreNom=?, "
				+ "membrePrenom=?, "
				+ "membreEmail=?, "
				+ "membreTel=?, "
				+ "isActif=?, "
				+ "isDefaultPassword=?, "
				+ "dateModification=? "
				+ "WHERE idMembre=?")
				.setParameter(1, filleul.getMembreNom())
				.setParameter(2, filleul.getMembrePrenom())
				.setParameter(3, filleul.getMembreEmail())
				.setParameter(4, filleul.getMembreTel())
				.setParameter(5, filleul.getIsActif())
				.setParameter(6, filleul.getIsDefaultPassword())
				.setParameter(7, ParfumUtils.getDateNowEN())
				.setParameter(8, filleul.getIdMembre());
		query.executeUpdate();
	}
	
	@Override
	public void insertFilleul(Membre filleul) {
		Query query = em.createNativeQuery("INSERT INTO membre (membreNom, membrePrenom, membreEmail, membreTel, isActif, isDefaultPassword, dateCreation) VALUES (?, ?, ?, ?, ?, ?, ?)")
				.setParameter(1, filleul.getMembreNom())
				.setParameter(2, filleul.getMembrePrenom())
				.setParameter(3, filleul.getMembreEmail())
				.setParameter(4, filleul.getMembreTel())
				.setParameter(5, filleul.getIsActif())
				.setParameter(6, filleul.getIsDefaultPassword())
				.setParameter(7, ParfumUtils.getDateNowEN());
		query.executeUpdate();
	}	
    
}
