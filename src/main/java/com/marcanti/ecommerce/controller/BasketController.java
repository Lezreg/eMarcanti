package com.marcanti.ecommerce.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.marcanti.ecommerce.model.Panier;
import com.marcanti.ecommerce.model.PanierProduit;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.service.actions.PanierActionService;
import com.marcanti.ecommerce.service.actions.ProduitServiceAction;

@ManagedBean(name = "basketView")
@SessionScoped
public class BasketController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1960368412588524596L;

	private static final Logger logger = LoggerFactory.getLogger(BasketController.class);

	@Autowired
	private PanierActionService panierService;

	@Autowired
	private ProduitServiceAction produitServiceAction;

	private Panier panierEnCours;

	public Panier getPanierEnCours() {
		return panierEnCours;
	}

	public void setPanierEnCours(Panier panierEnCours) {
		this.panierEnCours = panierEnCours;
	}

	private List<PanierProduit> panierProduitList;

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);

		setPanierProduitList(new ArrayList<PanierProduit>());
	}

	public void addPoduct(Produit produit, int quantite) {
		// FIXME not support quantity
		logger.info(produit.toString());
		// TODO return panier after update
		panierEnCours = panierService.addProduct(produit, null);
		Collection<PanierProduit> panierProduitCollection = null;
		if (panierEnCours != null && panierEnCours.getPanierProduitCollection() != null) {
			panierProduitCollection = panierEnCours.getPanierProduitCollection();
			// FIXME reload database
			setPanierProduitList(new ArrayList<PanierProduit>(panierProduitCollection));
		}
	}


	public String redirect() {
		return "panier";
	}

	public PanierActionService getPanierService() {
		return panierService;
	}

	public void setPanierService(PanierActionService panierService) {
		this.panierService = panierService;
	}

	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
		init();
	}

	public List<PanierProduit> getPanierProduitList() {
		return panierProduitList;
	}

	public void setPanierProduitList(List<PanierProduit> panierProduit) {
		this.panierProduitList = panierProduit;
	}

}
