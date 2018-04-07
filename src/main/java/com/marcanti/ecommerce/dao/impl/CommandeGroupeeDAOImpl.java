/*
	 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao.impl;

import java.util.Collections;
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
import com.marcanti.ecommerce.model.VCdeGroupeeDetail;
import com.marcanti.ecommerce.model.VReduction;

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
	public Long getIdDerniereCdeGoupeeALivrer(Long idOrg) {
		LOGGER.info("-----getIdDerniereCdeGoupee : idOrg =" + idOrg);
		Query query = em.createQuery(
				"SELECT max (c.idCdeGroupee) FROM CommandeGroupee c WHERE c.idOrga.idOrga = :idOrga and  c.idStatus.statusCode = :statusCode ")
				.setParameter("idOrga", idOrg).setParameter("statusCode", "CDE_GROUPEE_A_LIVRER");

		if (query.getResultList() == null || query.getResultList().isEmpty()) {
			return 0L;
		}
		return (Long) query.getResultList().get(0);
	}

	@Override
	public Long getIdDerniereCdeGoupee(Long idOrg) {
		LOGGER.info("-----getIdDerniereCdeGoupee : idOrg =" + idOrg);
		Query query = em.createQuery(
				"SELECT max (c.idCdeGroupee) FROM CommandeGroupee c WHERE c.idOrga.idOrga = :idOrga AND c.isEnCours = :isEncours ")
				.setParameter("isEncours", Boolean.TRUE).setParameter("idOrga", idOrg);

		if (query.getResultList() == null || query.getResultList().isEmpty()) {
			return 0L;
		}
		return (Long) query.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommandeGroupee> getCmdGroupeesByOrg(Long idOrg) {
		LOGGER.info("-----getCmdGroupeesByOrg : idOrg =" + idOrg);
		Query query = em.createQuery("SELECT c FROM CommandeGroupee c WHERE c.idOrga.idOrga = :idOrga")
				.setParameter("idOrga", idOrg);
		return (List<CommandeGroupee>) query.getResultList();
	}

	@Override
	public List<VCdeGroupeeDetail> getCmdGroupeesFilleuls(List<Long> idsMembre) {
		LOGGER.info("----------getCmdGroupeesFilleuls --------");

		if (idsMembre == null || idsMembre.isEmpty()) {
			LOGGER.info("idsMembre is null or is empty");
			return Collections.emptyList();
		}
		List<VCdeGroupeeDetail> cmdGroupees = em
				.createQuery("SELECT DISTINCT v FROM VCdeGroupeeDetail v WHERE v.idMembre IN (:ids)",
						VCdeGroupeeDetail.class)
				.setParameter("ids", idsMembre).getResultList();
		return cmdGroupees;
	}

	@Override
	public List<VCdeGroupeeDetail> getCmdGroupeesFilleulsByStatus(List<Long> idsMembre, String status) {
		LOGGER.info("----------getCmdGroupeesFilleuls --------");

		if (idsMembre == null || idsMembre.isEmpty()) {
			LOGGER.info("idsMembre is null or is empty");
			return Collections.emptyList();
		}
		List<VCdeGroupeeDetail> cmdGroupees = em.createQuery(
				"SELECT v FROM VCdeGroupeeDetail v WHERE v.idMembre IN (:ids) AND v.cdeIndivStatusCode = :statusCode",
				VCdeGroupeeDetail.class).setParameter("ids", idsMembre).setParameter("statusCode", status)
				.getResultList();
		return cmdGroupees;
	}

	@Override
	public List<CommandeGroupee> getCmdGroupeesByOrganisation(Long idOrg, boolean isEncours) {
		LOGGER.info("-----getCmdGroupeesByOrganisation : idOrg =" + idOrg + " isEncours :" + isEncours);
		List<CommandeGroupee> cmdGroupees = em
				.createQuery(
						"SELECT v FROM CommandeGroupee v WHERE v.idOrga.idOrga = :idOrga and v.isEnCours = :isEnCours",
						CommandeGroupee.class)
				.setParameter("idOrga", idOrg).setParameter("isEnCours", isEncours).getResultList();
		return cmdGroupees;
	}

	@Override
	public List<VReduction> getAllReductions() {
		LOGGER.info("-----getReductions");
		List<VReduction> reductions = em.createQuery("SELECT v FROM VReduction v", VReduction.class).getResultList();
		return reductions;
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
		return super.findRange(new int[] { from, to });
	}

	public String countREST() {
		return String.valueOf(super.count());
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
