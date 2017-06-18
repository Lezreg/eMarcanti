package com.marcanti.ecommerce.service.actions.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcanti.ecommerce.dao.MembreDAO;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.service.actions.MembreServiceAction;


public class MembreServiceActionImpl implements MembreServiceAction {


	@Autowired
	private MembreDAO membreDAO;
 
	public MembreDAO getMembreDAO() {
		return membreDAO;
	}

	public void setMembreDAO(MembreDAO membreDAO) {
		this.membreDAO = membreDAO;
	}

	@Override
	public Membre getMembre(Membre idMembre) {
		return membreDAO.getMembre(idMembre);
	}

	@Override
	public void updateFilleul(Membre filleul) {
		membreDAO.updateFilleul(filleul);
		
	}

	@Override
	public void insertFilleul(Membre filleul) {
		membreDAO.insertFilleul(filleul);
		
	}


	
}
