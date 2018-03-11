/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.marcanti.ecommerce.dao.AbstractGenericDAO;
import com.marcanti.ecommerce.dao.OrganisationDAO;
import com.marcanti.ecommerce.model.Organisation;

/**
 *
 * @author lezreg
 */
public class OrganisationDAOImpl extends AbstractGenericDAO<Organisation> implements OrganisationDAO {

	@PersistenceContext
    private EntityManager em;

    public OrganisationDAOImpl() {
        super(Organisation.class);
    }

    @Override
    public void create(Organisation entity) {
        super.create(entity);
    }

	public void edit(Long id, Organisation entity) {
        super.edit(entity);
    }

	public void remove(Long id) {
        super.remove(super.find(id));
    }

	public Organisation find(Long id) {
        return super.find(id);
    }

    @Override
    public List<Organisation> getOrganisationList() {
    	BigInteger nbrMembre;
    	List<Organisation> organisationList = super.findAll();
    	for (Organisation organisation : organisationList) {
    		nbrMembre = (BigInteger)em.createNativeQuery("SELECT count(*) FROM organisation WHERE idOrga=?").setParameter(1, organisation.getIdOrga()).getSingleResult();
    		organisation.setNbrMembre(nbrMembre.longValue());
		}
        return organisationList;
    }

	public List<Organisation> findRange(Integer from, Integer to) {
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
	public List<Organisation> getActiveOrganisationList() {
		return em.createNamedQuery("Organisation.findByIsActive", Organisation.class).setParameter("isActive", new Boolean(true)).getResultList();
	}

	@Override
	public Organisation getOrganisation(Organisation organisation) {
		return em.createNamedQuery("Organisation.findByIdOrga", Organisation.class).setParameter("idOrga", organisation.getIdOrga()).getSingleResult();
	}

	@Override
	public void insertOrganisation(Organisation organisation) {
		Query query = em.createNativeQuery("INSERT INTO organisation (orgaNom,orgaAdresse,orgaCodePostal,orgaVille,orgaPays,orgaTel,orgaAdresseLivraison,orgaAlias,isAlivrerAvantPaiement,dateCreation,isActive,accesCatalogueComplet,envoiMailConfirmation,nbreMaxProduitParItem) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
				.setParameter(1, organisation.getOrgaNom())
				.setParameter(2, organisation.getOrgaAdresse())
				.setParameter(3, organisation.getOrgaCodePostal())
				.setParameter(4, organisation.getOrgaVille())
				.setParameter(5, organisation.getOrgaPays())
				.setParameter(6, organisation.getOrgaTel())
				.setParameter(7, organisation.getOrgaAdresseLivraison())
				.setParameter(8, organisation.getOrgaAlias())
				.setParameter(9, organisation.getIsAlivrerAvantPaiement())
				.setParameter(10, organisation.getDateCreation())
				.setParameter(11, organisation.getIsActive())
				.setParameter(12, organisation.getAccesCatalogueComplet())
				.setParameter(13, organisation.getEnvoiMailConfirmation())
				.setParameter(14, organisation.getNbreMaxProduitParItem());
		query.executeUpdate();
		//em.persist(departement);
	}

	@Override
	public void updateOrganisation(Organisation organisation) {
		Query query = em.createNativeQuery("UPDATE organisation SET orgaNom=?, "
				+ "orgaAdresse=?, "
				+ "orgaCodePostal=?, "
				+ "orgaVille=?, "
				+ "orgaPays=?, "
				+ "orgaTel=?, "
				+ "orgaAdresseLivraison=?, "
				+ "orgaAlias=?, "
				+ "isAlivrerAvantPaiement=?, "
				+ "dateModification=?, "
				+ "isActive=?, "
				+ "accesCatalogueComplet=?, "
				+ "envoiMailConfirmation=?, "
				+ "nbreMaxProduitParItem=? "
				+ "WHERE idOrga=?")
				.setParameter(1, organisation.getOrgaNom())
				.setParameter(2, organisation.getOrgaAdresse())
				.setParameter(3, organisation.getOrgaCodePostal())
				.setParameter(4, organisation.getOrgaVille())
				.setParameter(5, organisation.getOrgaPays())
				.setParameter(6, organisation.getOrgaTel())
				.setParameter(7, organisation.getOrgaAdresseLivraison())
				.setParameter(8, organisation.getOrgaAlias())
				.setParameter(9, organisation.getIsAlivrerAvantPaiement())
				.setParameter(10, organisation.getDateModification())
				.setParameter(11, organisation.getIsActive())
				.setParameter(12, organisation.getAccesCatalogueComplet())
				.setParameter(13, organisation.getEnvoiMailConfirmation())
				.setParameter(14, organisation.getNbreMaxProduitParItem())
				.setParameter(15, organisation.getIdOrga());
		query.executeUpdate();
		//em.persist(departement);
		
	}
    
}
