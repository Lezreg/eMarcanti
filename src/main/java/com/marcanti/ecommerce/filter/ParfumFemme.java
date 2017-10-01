package com.marcanti.ecommerce.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
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
import com.marcanti.ecommerce.service.actions.ProduitServiceAction;

@ManagedBean(name = "PFFilterView")
@ViewScoped
public class ParfumFemme {
	
	
	private List<Produit> produits;
	
	private List<Produit> filteredProduits;
	
	private Produit selectedProduit;

	@Autowired
	private ProduitServiceAction produitServiceAction;

	@ManagedProperty("#{basketView}")
	private BasketController basket;

	public BasketController getBasket() {
		return basket;
	}

	public void setBasket(BasketController basket) {
		this.basket = basket;
	}
	
	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
		setProduits(produitServiceAction.getParfumFemme());
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
	
	// FIXME extract this methods in other session scopped bean
	public List<String> getBrands() {
		List<String> brands = new ArrayList<>();
		for (Marque m : produitServiceAction.getBrands()) {
			brands.add(m.getMarqueNom());
		}
		return brands;
	
	}

	public void addToBasket() {
		basket.addPoduct(selectedProduit, 1);
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
	
	public void setService(ProduitServiceAction service) {
		this.produitServiceAction = service;
	}

	public Produit getSelectedProduit() {
		return selectedProduit;
	}

	public void setSelectedProduit(Produit selectedProduit) {
		this.selectedProduit = selectedProduit;
	}
	
}

