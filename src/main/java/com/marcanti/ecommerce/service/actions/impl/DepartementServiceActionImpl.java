package com.marcanti.ecommerce.service.actions.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcanti.ecommerce.dao.DepartementDAO;
import com.marcanti.ecommerce.model.Departement;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.service.actions.DepartementServiceAction;


public class DepartementServiceActionImpl implements DepartementServiceAction {


	@Autowired
	private DepartementDAO departementDAO;	
 
	public DepartementDAO getDepartementDAO() {
		return departementDAO;
	}

	public void setDepartementDAO(DepartementDAO departementDAO) {
		this.departementDAO = departementDAO;
	}

	@Override
	public List<Departement> getDepartementList() {
		return departementDAO.getDepartementList();
	}

	@Override
	public Departement getDepartement(Departement departement) {
		return departementDAO.getDepartement(departement);
	}

	@Override
	public void updateDepartement(Departement departement) {
		departementDAO.updateDepartement(departement);
	}

	@Override
	public void insertDepartement(Departement departement) {
		departementDAO.insertDepartement(departement);
	}

	@Override
	public List<Departement> getDepartementByOrgaList(Organisation idOrga) {
		ArrayList<Departement> departementList = (ArrayList<Departement>)departementDAO.getDepartementByOrgaList(idOrga);
		Departement departement = new Departement(0L);
		departement.setDepartementNom("");
		departementList.add(0,departement);
		return departementList;
	}
	
}
