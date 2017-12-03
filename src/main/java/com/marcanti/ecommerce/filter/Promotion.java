package com.marcanti.ecommerce.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.marcanti.ecommerce.beans.ProduitBean;
import com.marcanti.ecommerce.controller.BasketController;
import com.marcanti.ecommerce.exception.CommandeGroupeeNotFoundException;
import com.marcanti.ecommerce.exception.CommandeGroupeeValidatedExeception;
import com.marcanti.ecommerce.model.Categorie;
import com.marcanti.ecommerce.model.Marque;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.service.actions.CategorieServiceAction;
import com.marcanti.ecommerce.service.actions.ProduitServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

@ManagedBean(name = "PromFilterView")
@SessionScoped
public class Promotion {

	private static final Logger LOGGER = LoggerFactory.getLogger(Promotion.class);

	private List<ProduitBean> produits;

	private List<ProduitBean> filteredProduits;

	private ProduitBean selectedProduit;

	private List<Marque> brands;

	private String selectedBrandName;

	List<Categorie> categories;

	private String selectedCategorieName;

	@Autowired
	private ProduitServiceAction produitServiceAction;

	@Autowired
	private CategorieServiceAction categorieServiceAction;

	@ManagedProperty("#{basketView}")
	private BasketController basket;

	UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();

	public BasketController getBasket() {
		return basket;
	}

	public void setBasket(BasketController basket) {
		this.basket = basket;
	}

	public ProduitServiceAction getService() {
		return produitServiceAction;
	}

	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

	public List<Marque> getBrands() {
		if (brands == null) {
			brands = produitServiceAction.getBrands();
		}
		return brands;
	}

	public String addToBasket() {
		Produit produit = produitServiceAction.getProduitById(selectedProduit.getIdProduit());
		try {
			basket.addPoduct(produit, 1);
		} catch (CommandeGroupeeNotFoundException e) {
			LOGGER.info(e.getMessage());
			return "/pages/private/errors/cmdNotFoundError.xhtml?faces-redirect=true";
		} catch (CommandeGroupeeValidatedExeception e) {
			LOGGER.info(e.getMessage());
			return "/pages/private/errors/cmdValidatedError.xhtml?faces-redirect=true";
		}
		return null;
	}

	public List<ProduitBean> getFilteredProduits() {
		return filteredProduits;
	}

	public void setFilteredProduits(List<ProduitBean> filteredProduits) {
		this.filteredProduits = filteredProduits;
	}

	public List<ProduitBean> getProduits() {
		List<ProduitBean> filtredProducts = new ArrayList<>();
		// if (newProducts == null || newProducts.isEmpty()) {
		produits = produitServiceAction.getPromoProducts(userSessionBean.getIdOrga());
		// }
		if ((selectedBrandName == null || selectedBrandName.isEmpty())
				&& (selectedCategorieName == null || selectedCategorieName.isEmpty())) {
			return produits;
		}

		if (selectedBrandName != null && !selectedBrandName.isEmpty()) {

			for (ProduitBean produitBean : produits) {

				if (selectedBrandName.equals(produitBean.getMarqueNom())) {
					filtredProducts.add(produitBean);
				}
			}
		} else {
			filtredProducts.addAll(produits);
		}

		if (selectedCategorieName != null && !selectedCategorieName.isEmpty()) {
			for (Iterator<ProduitBean> iterator = filtredProducts.iterator(); iterator.hasNext();) {
				ProduitBean next = iterator.next();
				if (!selectedCategorieName.equals(next.getCategorieNom())) {
					iterator.remove();
				}
			}
		}
		return filtredProducts;
	}

	public List<Categorie> getCategories() {
		if (categories == null) {
			categories = categorieServiceAction.getCategorieList();
		}
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	public void setProduits(List<ProduitBean> produits) {
		this.produits = produits;
	}

	public void setService(ProduitServiceAction service) {
		this.produitServiceAction = service;
	}

	public void addItemToBasket() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Result:", "Item added to basket"));
	}

	public ProduitBean getSelectedProduit() {
		return selectedProduit;
	}

	public void setSelectedProduit(ProduitBean selectedProduit) {
		this.selectedProduit = selectedProduit;
	}

	public String getSelectedBrandName() {
		return selectedBrandName;
	}

	public void setSelectedBrandName(String selectedBrandName) {
		this.selectedBrandName = selectedBrandName;
	}

	public String getSelectedCategorieName() {
		return selectedCategorieName;
	}

	public void setSelectedCategorieName(String selectedCategorieName) {
		this.selectedCategorieName = selectedCategorieName;
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

	public void setBrands(List<Marque> brands) {
		this.brands = brands;
	}
}
