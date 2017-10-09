package com.marcanti.ecommerce.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.marcanti.ecommerce.controller.BasketController;
import com.marcanti.ecommerce.model.Marque;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.model.VCatalogueAvecStock;
import com.marcanti.ecommerce.service.actions.ProduitServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

@ManagedBean(name = "NovFilterView")
@ViewScoped
public class Nouveautes {

	private List<Produit> produits;

	private List<Produit> filteredProduits;

	private VCatalogueAvecStock selectedProduit;

	private List<VCatalogueAvecStock> newProducts;

	public VCatalogueAvecStock getSelectedProduit() {
		return selectedProduit;
	}

	public void setSelectedProduit(VCatalogueAvecStock selectedProduit) {
		this.selectedProduit = selectedProduit;
	}

	public ProduitServiceAction getProduitServiceAction() {
		return produitServiceAction;
	}

	public void setProduitServiceAction(ProduitServiceAction produitServiceAction) {
		this.produitServiceAction = produitServiceAction;
	}

	@Autowired
	private ProduitServiceAction produitServiceAction;

	@ManagedProperty("#{basketView}")
	private BasketController basket;
	
	
	UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();


	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

	public boolean filterByPrice(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
	}

	public List<String> getBrands() {
		List<String> brands = new ArrayList<>();
		for (Marque m : produitServiceAction.getBrands()) {
			brands.add(m.getMarqueNom());
		}
		return brands;

	}

	public void addToBasket() {
		Produit produit = produitServiceAction.getProduitById(selectedProduit.getIdProduit());
		basket.addPoduct(produit, 1);
	}

	public List<Produit> getFilteredProduits() {
		return filteredProduits;
	}

	public void setFilteredProduits(List<Produit> filteredProduits) {
		this.filteredProduits = filteredProduits;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public BasketController getBasket() {
		return basket;
	}

	public void setBasket(BasketController basket) {
		this.basket = basket;
	}

	public ProduitServiceAction getService() {
		return produitServiceAction;
	}
	public void setService(ProduitServiceAction service) {
		this.produitServiceAction = service;
	}

	public void addItemToBasket() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Result:", "Item added to basket"));
	}

	public List<VCatalogueAvecStock> getNewProducts() {
		return produitServiceAction.getNewProducts(userSessionBean.getIdOrga());
	}

	public void setNewProducts(List<VCatalogueAvecStock> newProducts) {
		this.newProducts = newProducts;
	}

}
