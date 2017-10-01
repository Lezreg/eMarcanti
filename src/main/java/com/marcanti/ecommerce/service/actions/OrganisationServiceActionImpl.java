package com.marcanti.ecommerce.service.actions;

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

}
