package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcanti.ecommerce.dao.FilleulDAO;
import com.marcanti.ecommerce.model.Filleul;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.service.actions.FilleulsServiceAction;

@Service("filleulsService")
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
	public List<Membre> getFilleulsList(Membre parrain) {
		return filleulDAO.getFilleulsList(parrain);
	}

	@Override
	public Filleul getFilleul(Long idFilleul) {
		return filleulDAO.getFilleul(idFilleul);
	}


	
	
}
