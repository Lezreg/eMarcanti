package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.beans.ProduitBean;
import com.marcanti.ecommerce.constants.Categories;
import com.marcanti.ecommerce.dao.MarqueDAO;
import com.marcanti.ecommerce.dao.OrganisationDAO;
import com.marcanti.ecommerce.dao.ProduitDAO;
import com.marcanti.ecommerce.model.Marque;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.service.actions.ProduitServiceAction;
import com.marcanti.ecommerce.utils.ProduitConvertor;

@Service("produitService")
public class ProduitServiceActionImpl implements ProduitServiceAction {

	@Autowired
	private ProduitDAO produitDAO;

	@Autowired
	private MarqueDAO marqueDAO;

	@Autowired
	private OrganisationDAO organisationDAO;
	
	public MarqueDAO getMarqueDAO() {
		return marqueDAO;
	}

	public void setMarqueDAO(MarqueDAO marqueDAO) {
		this.marqueDAO = marqueDAO;
	}

	public ProduitDAO getProduitDAO() {
		return produitDAO;
	}

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
		return produitDAO.findProduitByCategorie(Categories.COFFRET.getCode());
	}

	@Override
	public List<Produit> getSoins() {
		// Miniatures et soins
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

	@Override
	public List<ProduitBean> getNewProducts(Long orgId) {
		Organisation organisation = organisationDAO.find(orgId);

		if (organisation != null && organisation.getAccesCatalogueComplet()) {
			return ProduitConvertor.convertVCatalogueAvecStock(produitDAO.getNewProducts());
		} else {
			return ProduitConvertor.convertVCatalogueRestreintAvecStock(produitDAO.getRestrictedNewProduit());
		}

	}

	@Override
	public Produit getProduitById(long idProduit) {
		return produitDAO.find(idProduit);
	}

	@Override
	public List<ProduitBean> getProductsByCategorie(Long orgId, String codeCategorie) {
		Organisation organisation = organisationDAO.find(orgId);
		if (organisation != null && organisation.getAccesCatalogueComplet()) {
			return ProduitConvertor.convertVCatalogueAvecStock(produitDAO.getProductsByCategorie(codeCategorie));
		} else {
			return ProduitConvertor.convertVCatalogueRestreintAvecStock(produitDAO.getRestrictedProduitsByCategorie(codeCategorie));
		}
	}

	@Override
	public List<ProduitBean> getPromoProducts(Long orgId) {
		Organisation organisation = organisationDAO.find(orgId);
		if (organisation != null && organisation.getAccesCatalogueComplet()) {
			return ProduitConvertor.convertVCatalogueAvecStock(produitDAO.getPromoProducts());
		} else {
			return ProduitConvertor.convertVCatalogueRestreintAvecStock(produitDAO.getRestrictedPromoProduit());
		}
	}

	@Override
	public List<ProduitBean> getDecouvProducts(Long orgId) {
		Organisation organisation = organisationDAO.find(orgId);
		if (organisation != null && organisation.getAccesCatalogueComplet()) {
			return ProduitConvertor.convertVCatalogueRestreintAvecStock(produitDAO.getRestrictedDecouvProduit());
		} else {
			return ProduitConvertor.convertVCatalogueAvecStock(produitDAO.getDecouvProducts());
		}
	}

	public OrganisationDAO getOrganisationDAO() {
		return organisationDAO;
	}

	public void setOrganisationDAO(OrganisationDAO organisationDAO) {
		this.organisationDAO = organisationDAO;
	}

	@Override
	public Produit getProduit(Long idProduit) {
		return produitDAO.getProduit(idProduit);
	}

	@Override
	public void updateProduit(Produit produit) {
		produitDAO.updateProduit(produit);
		
	}

	@Override
	public void insertProduit(Produit produit) {
		produitDAO.insertProduit(produit);
		
	}

}