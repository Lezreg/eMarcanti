package com.marcanti.ecommerce.service.actions;

import java.util.List;

import com.marcanti.ecommerce.model.Produit;
 
public interface ProduitServiceAction {
 
	public void AddProduit(Produit p);

	public List<Produit> listAllProduit();
     
}