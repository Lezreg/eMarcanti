package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.Produit;

public interface ProduitDAO {

	/**
	 * ajouter un nouveau produit
	 * 
	 * @param produit
	 */
	void create(Produit produit);

	/**
	 * 
	 * @return toute la liste des produits
	 */
	List<Produit> getAllProduits();

}
