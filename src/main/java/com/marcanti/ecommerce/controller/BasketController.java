package com.marcanti.ecommerce.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.marcanti.ecommerce.model.Panier;
import com.marcanti.ecommerce.model.PanierProduit;
import com.marcanti.ecommerce.model.PanierProduitPK;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.service.PanierService;

@ManagedBean(name = "basketView")
@SessionScoped
public class BasketController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1960368412588524596L;

	private static final Logger logger = LoggerFactory.getLogger(BasketController.class);

	private List<Produit> produitsAdded;


	@Autowired
	private PanierService panierService;

	@PostConstruct
	private void init() {
		produitsAdded = new ArrayList<>();
	}
	public void addPoduct(Produit produit, int quantite) {
		// FIXME not support quantity
		logger.info(produit.toString());

		if (produitsAdded.isEmpty()) {

			Panier panier = new Panier();
			
			 PanierProduitPK panierProduitPK = new PanierProduitPK();
			PanierProduit panierProduit = new PanierProduit(panierProduitPK);

			panier.setPanierNom("testpanier");
			// panier.getPanierProduitCollection().add(panierProduit);

			panierService.create(panier);
		}
		produitsAdded.add(produit);
	}

	public List<Produit> getProduitsAdded() {
		return produitsAdded;
	}

	public void setProduitsAdded(List<Produit> produitsAdded) {
		this.produitsAdded = produitsAdded;
	}
	public String redirect() {
		return "panier";
	}

	public PanierService getPanierService() {
		return panierService;
	}

	public void setPanierService(PanierService panierService) {
		this.panierService = panierService;
	}
}
