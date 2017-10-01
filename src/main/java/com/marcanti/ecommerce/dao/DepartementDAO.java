package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.Departement;
import com.marcanti.ecommerce.model.Organisation;

public interface DepartementDAO {
	
	public List<Departement> getDepartementList();
	
	public List<Departement> getDepartementByOrgaList(Organisation idOrga);
	
	public Departement getDepartement(Departement departement);
	
	public void insertDepartement(Departement departement);
	
	public void updateDepartement(Departement departement);
	
}
