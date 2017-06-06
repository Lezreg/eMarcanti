package com.marcanti.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.marcanti.ecommerce.dao.PanierDAO;
import com.marcanti.ecommerce.model.Panier;

@Controller
public class PanierServiceActionImpl implements PanierService {

	@Autowired
	private PanierDAO panierDao;

	@Override
	public Panier create(Panier panier) {
		return panierDao.edit(panier);
	}

	public PanierDAO getPanierDao() {
		return panierDao;
	}

	public void setPanierDao(PanierDAO panierDao) {
		this.panierDao = panierDao;
	}

}
