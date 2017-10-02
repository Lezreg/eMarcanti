package com.marcanti.ecommerce.service.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.dao.OrganisationDAO;
import com.marcanti.ecommerce.model.Organisation;


@Transactional
@Service("organisationServiceAction")
public class OrganisationServiceActionImpl implements OrganisationServiceAction {

	@Autowired
	private OrganisationDAO organisationDAO;
	
	@Override
	public Organisation getOrganisationById(Long orgId) {
		return organisationDAO.find(orgId);
	}
	
	public OrganisationDAO getOrganisationDAO() {
		return organisationDAO;
	}
	
	public void setOrganisationDAO(OrganisationDAO organisationDAO) {
		this.organisationDAO = organisationDAO;
	}

	@Override
	public List<Organisation> getOrganisationList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Organisation> getActiveOrganisationList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organisation getOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		
	}

}
