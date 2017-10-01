package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.SousCategorie;

public interface SousCategorieDAO {
	
	public List<SousCategorie> getSousCategorieList();
	
	public SousCategorie getSousCategorie(SousCategorie sousCategorie);
	
	public void updateSousCategorie(SousCategorie sousCategorie);
	
	public void insertSousCategorie(SousCategorie sousCategorie);

}
