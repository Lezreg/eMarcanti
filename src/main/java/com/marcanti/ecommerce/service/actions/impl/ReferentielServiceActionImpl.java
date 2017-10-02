package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcanti.ecommerce.dao.CategorieDAO;
import com.marcanti.ecommerce.dao.OrganisationDAO;
import com.marcanti.ecommerce.dao.ParamDAO;
import com.marcanti.ecommerce.dao.ProfilDAO;
import com.marcanti.ecommerce.model.Categorie;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Profil;
import com.marcanti.ecommerce.service.actions.ReferentielServiceAction;


@Service("referentielService")
public class ReferentielServiceActionImpl implements ReferentielServiceAction {


	@Autowired
	private OrganisationDAO organisationDAO;
	
	@Autowired
	private ProfilDAO profilDAO;	
 
	@Autowired
	private ParamDAO paramDAO;
	
	@Autowired
	private CategorieDAO categorieDAO;	
	
	
	public List<Organisation> getOrganisationList() {
		return organisationDAO.getOrganisationList();
	}

	public List<Profil> getProfilList() {
		return profilDAO.getProfilList();
	}

	public String getDefaultPassword() {
		return paramDAO.getDefaultPassword();
	}

	public List<Categorie> getCategorieList() {
		return categorieDAO.getCategorieList();
	}

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

	public ParamDAO getParamDAO() {
		return paramDAO;
	}

	public void setParamDAO(ParamDAO paramDAO) {
		this.paramDAO = paramDAO;
	}

	public CategorieDAO getCategorieDAO() {
		return categorieDAO;
	}

	public void setCategorieDAO(CategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;
	}

	
	
}
