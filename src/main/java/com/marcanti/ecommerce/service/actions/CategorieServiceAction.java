package com.marcanti.ecommerce.service.actions;

import java.util.List;

import com.marcanti.ecommerce.model.Categorie;

public interface CategorieServiceAction {
 
	public List<Categorie> getCategorieList();
	
	public Categorie getCategorie(Categorie categorie);
	
	public void updateCategorie(Categorie categorie);
	
	public void insertCategorie(Categorie categorie);
	
     
}