package com.marcanti.ecommerce.service.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.constants.Categories;
import com.marcanti.ecommerce.dao.MarqueDAO;
import com.marcanti.ecommerce.dao.ProduitDAO;
import com.marcanti.ecommerce.model.Marque;
import com.marcanti.ecommerce.model.Produit;

@Service("produitServiceAction")
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

	@Override
	public List<Produit> listProduitByCategorie(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Produit> getParfumHomme() {
		return produitDAO.findProduitByCategorie(Categories.PARFUM_HOMME.getCode());
	}


	public List<Produit> getParfumFemme() {
		return produitDAO.findProduitByCategorie(Categories.PARFUM_FEMME.getCode());

	}

	public List<Produit> getParfumEnfant() {
		return produitDAO.findProduitByCategorie(Categories.PARFUM_ENFANT.getCode());
	}

	public List<Produit> getTesteurs() {
			return produitDAO.findProduitByCategorie(Categories.TESTER.getCode());
	}

	@Override
	public List<Produit> getCoffrets() {
		return produitDAO.findProduitByCategorie(Categories.COFFRET.getCode());	}

	@Override
	public List<Produit> getSoins() {
		//Miniatures et soins
		return produitDAO.findProduitByCategorie(Categories.CREME.getCode());
	}

	@Override
	public List<Produit> getAccessoires() {
		return produitDAO.findProduitByCategorie(Categories.PILE_ACCESSOIRE.getCode());
	}

	@Override
	public List<Produit> getLots() {
		return produitDAO.findProduitByCategorie(Categories.LOTS.getCode());
	}

	@Override
	public List<Produit> getMiniatures() {
		return produitDAO.findProduitByCategorie(Categories.MINIATURE.getCode());
	}

}
