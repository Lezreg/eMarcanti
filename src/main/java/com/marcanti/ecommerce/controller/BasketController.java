package com.marcanti.ecommerce.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
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

	private Panier panier;

	private List<Produit> produitsAdded;

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);

		produitsAdded = new ArrayList<>();
	}

	public void addPoduct(Produit produit, int quantite) {
		// FIXME not support quantity
		logger.info(produit.toString());
		// TODO return panier after update
			panierService.addProduct(produit, null);

		// produit.setPanierProduitCollection(panier.getPanierProduitCollection());
		// produitServiceAction.AddProduit(produit);
		// FIXME reload database
		produitsAdded.add(produit);

		// produitsAdded = panierService.getallProduitbyPanier();
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
}
