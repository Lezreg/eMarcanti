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

import com.marcanti.ecommerce.constants.CommandeIndividuelleStatusEnum;
import com.marcanti.ecommerce.dao.AbstractGenericDAO;
import com.marcanti.ecommerce.dao.CommandeIndividuelleDAO;
import com.marcanti.ecommerce.model.CommandeGroupee;
import com.marcanti.ecommerce.model.CommandeIndividuelle;
import com.marcanti.ecommerce.model.Membre;

/**
 *
 * @author lezreg
 */
@Repository
public class CommandeIndividuelleDAOImpl extends AbstractGenericDAO<CommandeIndividuelle>
		implements CommandeIndividuelleDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandeIndividuelleDAOImpl.class);

	@PersistenceContext
    private EntityManager em;

    public CommandeIndividuelleDAOImpl() {
        super(CommandeIndividuelle.class);
    }

    public void create(CommandeIndividuelle entity) {
        super.create(entity);
    }

	public void edit(Long id, CommandeIndividuelle entity) {
        super.edit(entity);
    }

	public void remove(Long id) {
        super.remove(super.find(id));
    }

	public CommandeIndividuelle find(Long id) {
        return super.find(id);
    }

    public List<CommandeIndividuelle> findAll() {
        return super.findAll();
    }

	public List<CommandeIndividuelle> findRange(Integer from, Integer to) {
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
	public boolean verifyExistingCommandeIndividuelle(Membre membre, CommandeGroupee commandeGroupee) {
		Query query = em
				.createQuery(
						"SELECT c FROM CommandeIndividuelle c WHERE c.idMembre.idMembre = :idMembre and c.idCdeGroupee.idCdeGroupee = :idCdeGroupee")
				.setParameter("idMembre", membre.getIdMembre())
				.setParameter("idCdeGroupee", commandeGroupee.getIdCdeGroupee()).setMaxResults(1);
		if (query.getResultList() == null || query.getResultList().isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public CommandeIndividuelle getCommandeIndividuellByMembreAndCmdGroupe(Membre membre,
			CommandeGroupee commandeGroupee) {
		Query query = em
				.createQuery(
						"SELECT c FROM CommandeIndividuelle c WHERE c.idMembre.idMembre = :idMembre and c.idCdeGroupee.idCdeGroupee = :idCdeGroupee and c.isPaiementEffectue = :isPaiementEffectue")
				.setParameter("idMembre", membre.getIdMembre())
				.setParameter("idCdeGroupee", commandeGroupee.getIdCdeGroupee())
				.setParameter("isPaiementEffectue", Boolean.FALSE);

		LOGGER.info(query.toString());

		if (query.getResultList() == null || query.getResultList().isEmpty()) {
			return null;
		}
		return (CommandeIndividuelle) query.getResultList().get(0);
	}
    
	@Override
	public List<CommandeIndividuelle> getCommandeIndivListByMembreAndCmdGroupe(Long idMembre,
			Long idCdeGroupee) {
		Query query = em
				.createQuery(
						"SELECT c FROM CommandeIndividuelle c WHERE (c.idMembre.idMembre = :idMembre and c.idCdeGroupee.idCdeGroupee = :idCdeGroupee and c.idStatus.statusCode != :statusCode) order by date(dateCreation) desc")
				.setParameter("idMembre", idMembre)
				.setParameter("idCdeGroupee", idCdeGroupee)
				.setParameter("statusCode", CommandeIndividuelleStatusEnum.CDE_INDIVID_LIVREE.getCode());
		
		LOGGER.info(query.toString());

		if (query.getResultList() == null || query.getResultList().isEmpty()) {
			return null;
		}
		return (List<CommandeIndividuelle>) query.getResultList();
	}

	@Override
	public List<CommandeIndividuelle> getCommandeIndivLivreListByMembre(Long idMembre) {
		Query query = em
				.createQuery(
						"SELECT c FROM CommandeIndividuelle c WHERE (c.idMembre.idMembre = :idMembre and c.idStatus.statusCode = :statusCode) order by date(dateCreation) desc")
				.setParameter("idMembre", idMembre)
				.setParameter("statusCode", CommandeIndividuelleStatusEnum.CDE_INDIVID_LIVREE.getCode());

		LOGGER.info(query.toString());

		if (query.getResultList() == null || query.getResultList().isEmpty()) {
			return null;
		}
		return (List<CommandeIndividuelle>) query.getResultList();
	}
}
