package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.Organisation;

public interface OrganisationDAO {
	
	public List<Organisation> getOrganisationList();
	
	public List<Organisation> getActiveOrganisationList();
	
	public Organisation getOrganisation(Organisation organisation);
	
	public void insertOrganisation(Organisation organisation);
	
	public void updateOrganisation(Organisation organisation);

}
