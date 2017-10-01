package com.marcanti.ecommerce.service.actions;

import java.util.List;

import com.marcanti.ecommerce.model.Departement;
import com.marcanti.ecommerce.model.Organisation;

public interface DepartementServiceAction {
 
	public List<Departement> getDepartementList();
	
	public List<Departement> getDepartementByOrgaList(Organisation idOrga);
	
	public Departement getDepartement(Departement departement);
	
	public void updateDepartement(Departement departement);
	
	public void insertDepartement(Departement departement);
	
     
}