package com.marcanti.ecommerce.service.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcanti.ecommerce.dao.MembreDAO;
import com.marcanti.ecommerce.model.Membre;

@Service("membreService")
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
