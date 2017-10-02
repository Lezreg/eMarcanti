package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.dao.CategorieDAO;
import com.marcanti.ecommerce.model.Categorie;
import com.marcanti.ecommerce.service.actions.CategorieServiceAction;

@Service("categorieServiceAction")
@Transactional
public class CategorieServiceActionImpl implements CategorieServiceAction {


	@Autowired
	private CategorieDAO categorieDAO;	
 
	public CategorieDAO getCategorieDAO() {
		return categorieDAO;
	}

	public void setCategorieDAO(CategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;
	}

	@Override
	public List<Categorie> getCategorieList() {
		return categorieDAO.getCategorieList();
	}

	@Override
	public Categorie getCategorie(Categorie categorie) {
		return categorieDAO.getCategorie(categorie);
	}

	@Override
	public void updateCategorie(Categorie categorie) {
		categorieDAO.updateCategorie(categorie);
	}

	@Override
	public void insertCategorie(Categorie categorie) {
		categorieDAO.insertCategorie(categorie);
	}
	
}
