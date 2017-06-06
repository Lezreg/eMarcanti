package com.marcanti.ecommerce.service;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.constants.Categories;
import com.marcanti.ecommerce.dao.MarqueDAO;
import com.marcanti.ecommerce.dao.ProduitDAO;
import com.marcanti.ecommerce.model.Marque;
import com.marcanti.ecommerce.model.Produit;

@Service
@ManagedBean(name = "produitService")
@SessionScoped
public class ProduitServiceActionImpl implements ProduitServiceAction {

	@Autowired
	private ProduitDAO produitDAO;

	@Autowired
	private MarqueDAO marqueDAO;

	private List<Produit> parfumHomme;

	private List<Produit> parfumFemme;

	private List<Produit> parfumEnfant;

	private List<Produit> tester;

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
		// if (parfumHomme == null || parfumHomme.isEmpty()) {
			this.parfumHomme = produitDAO.findProduitByCategorie(Categories.PARFUM_HOMME.getCode());
		//// }
		return parfumHomme;
	}

	public void setParfumHomme(List<Produit> parfumHomme) {
		this.parfumHomme = parfumHomme;
	}

	public List<Produit> getParfumFemme() {
		if (parfumFemme == null || parfumFemme.isEmpty()) {
			this.parfumFemme = produitDAO.findProduitByCategorie(Categories.PARFUM_FEMME.getCode());
		}
		return parfumFemme;
	}

	public void setParfumFemme(List<Produit> parfumFemme) {
		this.parfumFemme = parfumFemme;
	}

	public List<Produit> getParfumEnfant() {
		if (parfumEnfant == null || parfumEnfant.isEmpty()) {
			this.parfumEnfant = produitDAO.findProduitByCategorie(Categories.PARFUM_ENFANT.getCode());
		}
		return parfumEnfant;
	}

	public void setParfumEnfant(List<Produit> parfumEnfant) {
		this.parfumEnfant = parfumEnfant;
	}

	public List<Produit> getTester() {
		if (tester == null || tester.isEmpty()) {
			this.tester = produitDAO.findProduitByCategorie(Categories.TESTER.getCode());
		}
		return tester;
	}

	public void setTester(List<Produit> tester) {
		this.tester = tester;
	}

}
