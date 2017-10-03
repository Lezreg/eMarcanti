package com.marcanti.ecommerce.service.actions;

import java.util.List;

import com.marcanti.ecommerce.model.Marque;
import com.marcanti.ecommerce.model.Produit;
 
public interface ProduitServiceAction {
 
	public void AddProduit(Produit p);

	public List<Produit> listAllProduit();

	public List<Produit> listProduitByCategorie(String code);

	public List<Marque> getBrands();

	public List<Produit> getParfumHomme();

	public List<Produit> getParfumFemme();

	public List<Produit> getParfumEnfant();

	public List<Produit> getTesteurs();
	
	public List<Produit> getCoffrets();
	
	public List<Produit> getSoins();
	
	public List<Produit> getAccessoires();
	
	public List<Produit> getLots();

     
}