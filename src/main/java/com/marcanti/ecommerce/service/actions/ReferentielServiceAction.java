package com.marcanti.ecommerce.service.actions;

import java.util.List;

import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Profil;

public interface ReferentielServiceAction {
 
	public List<Organisation> getOrganisationList();
	
	public List<Profil> getProfilList();
	
    public String getDefaultPassword();
}