package com.marcanti.ecommerce.service.actions;

import java.util.List;

import com.marcanti.ecommerce.model.Categorie;
import com.marcanti.ecommerce.model.Departement;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.SousCategorie;

public interface SousCategorieServiceAction {
 
	public List<SousCategorie> getSousCategorieList();
	
	public List<SousCategorie> getSousCategorieByCategorieList(Categorie idCategorie);
	
	public List<Categorie> getCategorieList();
	
	public SousCategorie getSousCategorie(SousCategorie sousCategorie);
	
	public void updateSousCategorie(SousCategorie sousCategorie);
	
	public void insertSousCategorie(SousCategorie sousCategorie);
	
     
}