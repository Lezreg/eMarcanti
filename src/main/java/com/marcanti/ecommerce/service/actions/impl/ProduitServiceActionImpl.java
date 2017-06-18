package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.dao.MarqueDAO;
import com.marcanti.ecommerce.dao.ProduitDAO;
import com.marcanti.ecommerce.model.Marque;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.service.actions.ProduitServiceAction;

public class ProduitServiceActionImpl implements ProduitServiceAction {


	@Autowired
	private ProduitDAO produitDAO;

	@Autowired
	private MarqueDAO marqueDAO;

    @Override
    @Transactional
	public void AddProduit(Produit produit) {
		this.produitDAO.create(produit);
    }
 
    @Override
    @Transactional
	public List<Produit> listAllProduit() {
		return this.produitDAO.getAllProduits();
    }

	public void setProduitDAO(ProduitDAO produitDAO) {
		this.produitDAO = produitDAO;
	}

	@Override
	public List<Marque> getBrands() {
		return marqueDAO.findAll();
	}
 
}
