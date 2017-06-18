package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcanti.ecommerce.dao.OrganisationDAO;
import com.marcanti.ecommerce.dao.ProfilDAO;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Profil;
import com.marcanti.ecommerce.service.actions.ReferentielServiceAction;


public class ReferentielServiceActionImpl implements ReferentielServiceAction {


	@Autowired
	private OrganisationDAO organisationDAO;
	
	@Autowired
	private ProfilDAO profilDAO;	
 
	public OrganisationDAO getOrganisationDAO() {
		return organisationDAO;
	}

	public void setOrganisationDAO(OrganisationDAO organisationDAO) {
		this.organisationDAO = organisationDAO;
	}

	public ProfilDAO getProfilDAO() {
		return profilDAO;
	}

	public void setProfilDAO(ProfilDAO profilDAO) {
		this.profilDAO = profilDAO;
	}

	@Override
	public List<Organisation> getOrganisationList() {
		return organisationDAO.getOrganisationList();
	}

	@Override
	public List<Profil> getProfilList() {
		return profilDAO.getProfilList();
	}


	
	
}
