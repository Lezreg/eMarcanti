package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.Categorie;

public interface CategorieDAO {
	
	public List<Categorie> getCategorieList();
	
	public Categorie getCategorie(Categorie categorie);
	
	public void insertCategorie(Categorie categorie);
	
	public void updateCategorie(Categorie categorie);

}
