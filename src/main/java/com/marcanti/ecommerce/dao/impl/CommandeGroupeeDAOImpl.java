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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.marcanti.ecommerce.dao.AbstractGenericDAO;
import com.marcanti.ecommerce.dao.CommandeGroupeeDAO;
import com.marcanti.ecommerce.model.CommandeGroupee;

/**
 *
 * @author lezreg
 */
@Repository
public class CommandeGroupeeDAOImpl extends AbstractGenericDAO<CommandeGroupee> implements CommandeGroupeeDAO {
	/**
	 * 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandeGroupeeDAOImpl.class);
	

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Long getIdDerniereCdeGoupee(Long idOrg) {
		Query query = em
				.createQuery(
						"SELECT max (c.idCdeGroupee) FROM CommandeGroupee c WHERE c.idOrga.idOrga = :idOrga and  c.idStatus.statusCode = :statusCode ")
				.setParameter("idOrga", idOrg)
				.setParameter("statusCode", "CDE_GROUPEE_A_LIVRER");
		
		if (query.getResultList() == null || query.getResultList().isEmpty()) {
			return null;
		}
		return (Long) query.getResultList().get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CommandeGroupee> getCmdGroupeesByOrg(Long idOrg) {
		Query query = em
				.createQuery(
						"SELECT c FROM CommandeGroupee c WHERE c.idOrga.idOrga = :idOrga")
				.setParameter("idOrga", idOrg);
		return (List<CommandeGroupee>) query.getResultList();
	}
	
	
    public CommandeGroupeeDAOImpl() {
        super(CommandeGroupee.class);
    }

    public void create(CommandeGroupee entity) {
        super.create(entity);
    }

	public void edit(Long id, CommandeGroupee entity) {
        super.edit(entity);
    }

	public void remove(Long id) {
        super.remove(super.find(id));
    }

	public CommandeGroupee find(Long id) {
        return super.find(id);
    }

    public List<CommandeGroupee> findAll() {
        return super.findAll();
    }

	public List<CommandeGroupee> findRange(Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


	

	
    
}
