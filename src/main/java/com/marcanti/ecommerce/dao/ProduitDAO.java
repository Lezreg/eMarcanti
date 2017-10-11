package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.model.VCatalogueAvecStock;
import com.marcanti.ecommerce.model.VCatalogueRestreintAvecStock;

public interface ProduitDAO extends GenericDAO<Produit> {

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

	/**
	 * 
	 * @param CatCOde
	 * @return
	 */
	public List<Produit> findProduitByCategorie(String CatCOde);

	/**
	 * all product list
	 * @return VCatalogueAvecStock View
	 */
	public List<VCatalogueAvecStock> getNewProducts();

	/**
	 *  restricted product list
	 * @return
	 */
	List<VCatalogueRestreintAvecStock> getRestrictedNewProduit();

}
