package com.marcanti.ecommerce.service.actions;

import com.marcanti.ecommerce.model.Organisation;

import java.util.List;


public interface OrganisationServiceAction {
 
	public List<Organisation> getOrganisationList();
	
	public List<Organisation> getActiveOrganisationList();
	
	public Organisation getOrganisation(Organisation organisation);
	
	public void updateOrganisation(Organisation organisation);
	
	public void insertOrganisation(Organisation organisation);
	
	Organisation getOrganisationById(Long orgId);
	
     
}
