package com.marcanti.ecommerce.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.marcanti.ecommerce.controller.BasketController;
import com.marcanti.ecommerce.model.Marque;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.service.ProduitServiceAction;

@ManagedBean(name = "PHFilterView")
@ViewScoped
public class ParfumHomme {
	
	
	private List<Produit> produits;
	
	private List<Produit> filteredProduits;
	
	private Produit selectedProduit;

	@ManagedProperty("#{produitService}")
	private ProduitServiceAction service;
	
	@ManagedProperty("#{basketView}")
	private BasketController basket;
	
	
	public BasketController getBasket() {
		return basket;
	}

	public void setBasket(BasketController basket) {
		this.basket = basket;
	}

	public ProduitServiceAction getService() {
		return service;
	}

	@PostConstruct
	public void init() {
		setProduits(service.getParfumHomme());
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
		for (Marque m : service.getBrands()) {
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
		this.service = service;
	}

	public void addItemToBasket() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Result:", "Item added to basket"));
	}

	public Produit getSelectedProduit() {
		return selectedProduit;
	}

	public void setSelectedProduit(Produit selectedProduit) {
		this.selectedProduit = selectedProduit;
	}
}

