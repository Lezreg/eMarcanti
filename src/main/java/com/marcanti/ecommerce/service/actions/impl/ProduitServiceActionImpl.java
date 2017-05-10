package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.dao.ProduitDAO;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.service.actions.ProduitServiceAction;

@Service
@ManagedBean(name = "produitService")
@SessionScoped
public class ProduitServiceActionImpl implements ProduitServiceAction {


	@Autowired
	private ProduitDAO produitDAO;
	 
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
 
}
