package com.marcanti.ecommerce.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.marcanti.ecommerce.model.Marque;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.service.actions.ProduitServiceAction;

@ManagedBean(name = "dtFilterView")
@ViewScoped
public class ProductFilter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1194480801457255131L;

	private List<Produit> produits;

	private List<Produit> filteredProduits;

	@ManagedProperty("#{produitService}")
	private ProduitServiceAction service;

	@PostConstruct
	public void init() {
		setProduits(service.listAllProduit());
	}

	 public boolean filterByPrice(Object value, Object filter, Locale locale) {
	        String filterText = (filter == null) ? null : filter.toString().trim();
	        if(filterText == null||filterText.equals("")) {
	            return true;
	        }
	        if(value == null) {
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
}
