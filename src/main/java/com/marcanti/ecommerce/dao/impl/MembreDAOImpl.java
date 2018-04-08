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
import com.marcanti.ecommerce.model.Filleul;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Profil;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

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
	public void insertFilleulMembre(Membre filleul, UserSessionBean parrain) {
		Query query = em.createNativeQuery("INSERT INTO membre (idOrga, idDepartement, idProfil, membreNom, membrePrenom, membreEmail, membreTel, isActif, password, isDefaultPassword, dateCreation, dateModification) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)")
				.setParameter(1, filleul.getIdOrga().getIdOrga())
				.setParameter(2, filleul.getIdDepartement().getIdDepartement())
				.setParameter(3, filleul.getIdProfil().getIdProfil())
				.setParameter(4, filleul.getMembreNom())
				.setParameter(5, filleul.getMembrePrenom())
				.setParameter(6, filleul.getMembreEmail())
				.setParameter(7, filleul.getMembreTel())
				.setParameter(8, filleul.getIsActif())
				.setParameter(9, filleul.getPassword())
				.setParameter(10, filleul.getIsDefaultPassword())
				.setParameter(11, filleul.getDateCreation())
				.setParameter(12, filleul.getDateCreation());
		query.executeUpdate();
		//create(filleul);
		//em.persist(filleul);
		filleul.setIdMembre(em.createNamedQuery("Membre.findByMembreEmail", Membre.class).setParameter("membreEmail", filleul.getMembreEmail()).getSingleResult().getIdMembre());
		query = em.createNativeQuery("INSERT INTO filleul (idFilleul,idMembreParrain, parrainNom) VALUES (?,?,?)").setParameter(1, filleul.getIdMembre()).setParameter(2, parrain.getIdMembre()).setParameter(3, parrain.getMembreNom());
		query.executeUpdate();
	}
	
	@Override
	public void updateFilleulMembre(Membre filleul) {
		Query query = em.createNativeQuery("UPDATE membre SET membreNom=?, "
				+ "membrePrenom=?, "
				+ "membreEmail=?, "
				+ "membreTel=?, "
				+ "isActif=?, "
				+ "password=?, "
				+ "isDefaultPassword=?, "
				+ "dateModification=? "
				+ "WHERE idMembre=?")
				.setParameter(1, filleul.getMembreNom())
				.setParameter(2, filleul.getMembrePrenom())
				.setParameter(3, filleul.getMembreEmail())
				.setParameter(4, filleul.getMembreTel())
				.setParameter(5, filleul.getIsActif())
				.setParameter(6, filleul.getPassword())
				.setParameter(7, filleul.getIsDefaultPassword())
				.setParameter(8, filleul.getDateModification())
				.setParameter(9, filleul.getIdMembre());
		query.executeUpdate();
		//em.merge(filleul);
		//em.refresh(filleul);
		//edit(filleul);
	}	

	@Override
	public void insertMembre(Membre membre) {
		Query query = em.createNativeQuery("INSERT INTO membre (idOrga, idDepartement, idProfil, membreNom, membrePrenom, membreEmail, membreTel, hasReduc, isActif, password, isDefaultPassword, dateCreation, dateModification) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)")
				.setParameter(1, membre.getIdOrga().getIdOrga())
				.setParameter(2, membre.getIdDepartement().getIdDepartement())
				.setParameter(3, membre.getIdProfil().getIdProfil())
				.setParameter(4, membre.getMembreNom())
				.setParameter(5, membre.getMembrePrenom())
				.setParameter(6, membre.getMembreEmail())
				.setParameter(7, membre.getMembreTel())
				.setParameter(8, membre.getHasReduc())
				.setParameter(9, membre.getIsActif())
				.setParameter(10, membre.getPassword())
				.setParameter(11, membre.getIsDefaultPassword())
				.setParameter(12, membre.getDateCreation())
				.setParameter(13, membre.getDateCreation());
		query.executeUpdate();
	}
	
	public void insertMembreFilleul(Membre filleul, Membre membreParrain) {
		insertMembre(filleul);
		filleul.setIdMembre(em.createNamedQuery("Membre.findByMembreEmail", Membre.class).setParameter("membreEmail", filleul.getMembreEmail()).getSingleResult().getIdMembre());
		insertFilleul(filleul,membreParrain);
	}
	
	public void insertFilleul(Membre filleul, Membre membreParrain) {
		Query query = em.createNativeQuery("INSERT INTO filleul (idFilleul,idMembreParrain, parrainNom) VALUES (?,?,?)").setParameter(1, filleul.getIdMembre()).setParameter(2, membreParrain.getIdMembre()).setParameter(3, membreParrain.getMembreNom());
		query.executeUpdate();
	}	
	
	@Override
	public void updateMembre(Membre membre) {
		Query query = em.createNativeQuery("UPDATE membre SET "
				+ "idOrga=?, "
				+ "idDepartement=?, "
				+ "idProfil=?, "
				+ "membreNom=?, "
				+ "membrePrenom=?, "
				+ "membreEmail=?, "
				+ "membreTel=?, "
				+ "hasReduc=?, "
				+ "isActif=?, "
				+ "password=?, "
				+ "isDefaultPassword=?, "
				+ "dateModification=? "
				+ "WHERE idMembre=?")
				.setParameter(1, membre.getIdOrga().getIdOrga())
				.setParameter(2, membre.getIdDepartement().getIdDepartement())
				.setParameter(3, membre.getIdProfil().getIdProfil())
				.setParameter(4, membre.getMembreNom())
				.setParameter(5, membre.getMembrePrenom())
				.setParameter(6, membre.getMembreEmail())
				.setParameter(7, membre.getMembreTel())
				.setParameter(8, membre.getHasReduc())
				.setParameter(9, membre.getIsActif())
				.setParameter(10, membre.getPassword())
				.setParameter(11, membre.getIsDefaultPassword())
				.setParameter(12, membre.getDateModification())
				.setParameter(13, membre.getIdMembre());
		query.executeUpdate();
	}
	
	public void updateMembreFilleul(Membre filleul, Membre membreParrain) {
		updateMembre(filleul);
		boolean isFilleulExist = em.createNamedQuery("Filleul.findByIdFilleul", Filleul.class).setParameter("idFilleul", filleul.getIdMembre()).getResultList().isEmpty();
		if(isFilleulExist){
			insertFilleul(filleul,membreParrain);
		}else{
			updateFilleul(filleul,membreParrain);
		}
	}	
	
	public void updateFilleul(Membre filleul, Membre membreParrain) {
		Query query = em.createNativeQuery("UPDATE filleul SET "
				+ "idMembreParrain=?, "
				+ "parrainNom=? "		
				+ "WHERE idFilleul=?")
				.setParameter(1, membreParrain.getIdMembre()).setParameter(2, membreParrain.getMembreNom()).setParameter(3, filleul.getIdMembre());
		query.executeUpdate();
	}	

	@Override
	public List<Membre> getMembreByOrgaList(Organisation idOrga) {
		return em.createNamedQuery("Membre.findByIdOrga", Membre.class).setParameter("idOrga", idOrga).getResultList();
	}	
    
	@Override
	public List<Membre> getParrainByOrgaList(Organisation idOrga, List<Profil> idProfilList) {
		return em.createNamedQuery("Membre.findParrainByIdOrga", Membre.class).setParameter("idOrga", idOrga).setParameter("idProfilList", idProfilList).getResultList();
	}	
}
