package com.marcanti.ecommerce.filter;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.marcanti.ecommerce.controller.BasketController;
import com.marcanti.ecommerce.exception.CommandeGroupeeNotFoundException;
import com.marcanti.ecommerce.exception.CommandeGroupeeValidatedExeception;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.service.actions.CategorieServiceAction;
import com.marcanti.ecommerce.service.actions.ProduitServiceAction;

@ManagedBean(name = "ModalController")
@RequestScoped
public class ModalController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2707311747399783891L;

	private static final Logger LOGGER = LoggerFactory.getLogger(Nouveautes.class);

	@Autowired
	private ProduitServiceAction produitServiceAction;
	@Autowired
	private CategorieServiceAction categorieServiceAction;

	@ManagedProperty("#{basketView}")
	private BasketController basket;

	private Produit selectedProduit;

	private String ajoutPanier = "false";

	private String cmdNotFoundError = "false";

	private String cmdValidatedError = "false";

	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

	private void addToBasket(Produit selectedProduit) {

		try {
			Produit produit = produitServiceAction.getProduitById(selectedProduit.getIdProduit());
			basket.addPoduct(produit, 1);
		} catch (CommandeGroupeeNotFoundException e) {
			LOGGER.info(e.getMessage());
			cmdNotFoundError = "true";
			ajoutPanier = "false";
			// return "/pages/private/errors/cmdNotFoundError.xhtml?faces-redirect=true";
		} catch (CommandeGroupeeValidatedExeception e) {
			LOGGER.info(e.getMessage());
			cmdValidatedError = "true";
			ajoutPanier = "false";
			// return "/pages/private/errors/cmdValidatedError.xhtml?faces-redirect=true";
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			cmdValidatedError = "true";
			ajoutPanier = "false";
			// return "/pages/private/errors/cmdValidatedError.xhtml?faces-redirect=true";
		}

	}

	public ProduitServiceAction getProduitServiceAction() {
		return produitServiceAction;
	}

	public void setProduitServiceAction(ProduitServiceAction produitServiceAction) {
		this.produitServiceAction = produitServiceAction;
	}

	public CategorieServiceAction getCategorieServiceAction() {
		return categorieServiceAction;
	}

	public void setCategorieServiceAction(CategorieServiceAction categorieServiceAction) {
		this.categorieServiceAction = categorieServiceAction;
	}

	public BasketController getBasket() {
		return basket;
	}

	public void setBasket(BasketController basket) {
		this.basket = basket;
	}

	public Produit getSelectedProduit() {
		return selectedProduit;
	}

	public void setSelectedProduit(Produit selectedProduit) {
		this.selectedProduit = selectedProduit;
	}

	public String getAjoutPanier() {

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String id = paramMap.get("id");
		LOGGER.info("-----------------------------------id:" + id);

		selectedProduit = produitServiceAction.getProduit(new Long(id));

		if (selectedProduit != null && "false".equals(ajoutPanier) && selectedProduit.getQteEnStock() > 0) {
			ajoutPanier = "true";
			addToBasket(selectedProduit);
		}

		return ajoutPanier;
	}

	public void setAjoutPanier(String ajoutPanier) {
		this.ajoutPanier = ajoutPanier;
	}

	public String getCmdNotFoundError() {
		return cmdNotFoundError;
	}

	public void setCmdNotFoundError(String cmdNotFoundError) {
		this.cmdNotFoundError = cmdNotFoundError;
	}

	public String getCmdValidatedError() {
		return cmdValidatedError;
	}

	public void setCmdValidatedError(String cmdValidatedError) {
		this.cmdValidatedError = cmdValidatedError;
	}

}
