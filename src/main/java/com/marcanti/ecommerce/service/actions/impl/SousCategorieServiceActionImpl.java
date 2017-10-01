package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcanti.ecommerce.dao.CategorieDAO;
import com.marcanti.ecommerce.dao.SousCategorieDAO;
import com.marcanti.ecommerce.model.Categorie;
import com.marcanti.ecommerce.model.SousCategorie;
import com.marcanti.ecommerce.service.actions.SousCategorieServiceAction;


public class SousCategorieServiceActionImpl implements SousCategorieServiceAction {


	@Autowired
	private SousCategorieDAO sousCategorieDAO;
	
	@Autowired
	private CategorieDAO categorieDAO;	
 
	public SousCategorieDAO getSousCategorieDAO() {
		return sousCategorieDAO;
	}

	public void setSousCategorieDAO(SousCategorieDAO sousCategorieDAO) {
		this.sousCategorieDAO = sousCategorieDAO;
	}

	@Override
	public List<SousCategorie> getSousCategorieList() {
		return sousCategorieDAO.getSousCategorieList();
	}

	@Override
	public SousCategorie getSousCategorie(SousCategorie sousCategorie) {
		return sousCategorieDAO.getSousCategorie(sousCategorie);
	}

	@Override
	public List<Categorie> getCategorieList() {
		return categorieDAO.getCategorieList();
	}

	@Override
	public void updateSousCategorie(SousCategorie sousCategorie) {
		sousCategorieDAO.updateSousCategorie(sousCategorie);
	}

	@Override
	public void insertSousCategorie(SousCategorie sousCategorie) {
		sousCategorieDAO.insertSousCategorie(sousCategorie);
	}
	
}
