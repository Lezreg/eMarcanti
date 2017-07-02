package com.marcanti.ecommerce.service.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcanti.ecommerce.dao.CategorieDAO;
import com.marcanti.ecommerce.dao.SousCategorieDAO;
import com.marcanti.ecommerce.model.Categorie;
import com.marcanti.ecommerce.model.SousCategorie;


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
