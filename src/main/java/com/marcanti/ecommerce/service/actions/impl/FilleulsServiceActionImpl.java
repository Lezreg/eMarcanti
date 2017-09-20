package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcanti.ecommerce.dao.FilleulDAO;
import com.marcanti.ecommerce.model.Filleul;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.service.actions.FilleulsServiceAction;


public class FilleulsServiceActionImpl implements FilleulsServiceAction {


	@Autowired
	private FilleulDAO filleulDAO;
 

	public FilleulDAO getFilleulDAO() {
		return filleulDAO;
	}

	public void setFilleulDAO(FilleulDAO filleulDAO) {
		this.filleulDAO = filleulDAO;
	}

	@Override
	public List<Membre> getFilleulsList(Membre idMembreParrain) {
		return filleulDAO.getFilleulsList(idMembreParrain);
	}

	@Override
	public Filleul getFilleul(Long idFilleul) {
		return filleulDAO.getFilleul(idFilleul);
	}


	
	
}
