package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.Organisation;

public interface OrganisationDAO extends GenericDAO<Organisation>{
	
	public List<Organisation> getOrganisationList();

}
