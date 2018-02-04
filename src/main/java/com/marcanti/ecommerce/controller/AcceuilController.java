package com.marcanti.ecommerce.controller;

import java.io.Serializable;
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

import com.marcanti.ecommerce.beans.ProduitBean;
import com.marcanti.ecommerce.service.actions.ProduitServiceAction;

@ManagedBean(name = "acceuilView")
@SessionScoped
public class AcceuilController implements Serializable {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AcceuilController.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 5444941236886680049L;

	@Autowired
	private ProduitServiceAction produitServiceAction;

	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

	@SuppressWarnings("unused")
	private List<ProduitBean> products;

	private String searchChar;

	private ProduitBean selectedProduit;

	public ProduitBean getSelectedProduit() {
		return selectedProduit;
	}

	public void setSelectedProduit(ProduitBean selectedProduit) {
		this.selectedProduit = selectedProduit;
	}

	public String search() {
		LOGGER.info("--search --- searchChar:" + searchChar);
		return "resultat";
	}

	public void addToBasket() {
		LOGGER.info("--addToBasket ---");
	}

	public ProduitServiceAction getProduitServiceAction() {
		return produitServiceAction;
	}

	public void setProduitServiceAction(ProduitServiceAction produitServiceAction) {
		this.produitServiceAction = produitServiceAction;
	}

	public List<ProduitBean> getProducts() {
		LOGGER.info("--getProducts --- searchChar:" + searchChar);
		return produitServiceAction.searchProduct(searchChar);
	}

	public String getSearchChar() {
		return searchChar;
	}

	public void setSearchChar(String searchChar) {
		this.searchChar = searchChar;
	}

	public void setProducts(List<ProduitBean> products) {
		this.products = products;
	}

}
