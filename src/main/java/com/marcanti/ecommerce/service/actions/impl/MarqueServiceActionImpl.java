package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.dao.MarqueDAO;
import com.marcanti.ecommerce.model.Marque;
import com.marcanti.ecommerce.service.actions.MarqueServiceAction;

@Transactional
public class MarqueServiceActionImpl implements MarqueServiceAction {

	@Autowired
	private MarqueDAO marqueDAO;

	public MarqueDAO getMarqueDAO() {
		return marqueDAO;
	}

	public void setMarqueDAO(MarqueDAO marqueDAO) {
		this.marqueDAO = marqueDAO;
	}

	@Override
	public List<Marque> getMarqueList() {
		return marqueDAO.getMarqueList();
	}

	@Override
	public Marque getMarque(Marque marque) {
		return marqueDAO.getMarque(marque);
	}

	@Override
	public void updateMarque(Marque marque) {
		marqueDAO.updateMarque(marque);
	}

	@Override
	public void insertMarque(Marque marque) {
		marqueDAO.insertMarque(marque);
	}

}
