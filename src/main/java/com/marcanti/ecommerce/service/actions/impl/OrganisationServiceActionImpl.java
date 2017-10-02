package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcanti.ecommerce.dao.OrganisationDAO;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.service.actions.OrganisationServiceAction;


public class OrganisationServiceActionImpl implements OrganisationServiceAction {


	@Autowired
	private OrganisationDAO organisationDAO;	
 
	public OrganisationDAO getOrganisationDAO() {
		return organisationDAO;
	}

	public void setOrganisationDAO(OrganisationDAO organisationDAO) {
		this.organisationDAO = organisationDAO;
	}

	@Override
	public List<Organisation> getOrganisationList() {
		return organisationDAO.getOrganisationList();
	}

	@Override
	public Organisation getOrganisation(Organisation organisation) {
		return organisationDAO.getOrganisation(organisation);
	}

	@Override
	public void updateOrganisation(Organisation organisation) {
		organisationDAO.updateOrganisation(organisation);
	}

	@Override
	public void insertOrganisation(Organisation organisation) {
		organisationDAO.insertOrganisation(organisation);
	}

	@Override
	public List<Organisation> getActiveOrganisationList() {
		return organisationDAO.getActiveOrganisationList();
	}

	@Override
	public Organisation getOrganisationById(Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
