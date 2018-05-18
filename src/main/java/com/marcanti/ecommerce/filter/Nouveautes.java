package com.marcanti.ecommerce.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
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

@ManagedBean(name = "NovFilterView")
@ViewScoped
public class Nouveautes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4035287012655004471L;

	private static final Logger LOGGER = LoggerFactory.getLogger(Nouveautes.class);

	private List<ProduitBean> produits;

	private List<ProduitBean> filteredProduits;

	private ProduitBean selectedProduit;

	private Produit produit;

	private List<ProduitBean> newProducts;

	private List<Marque> brands;

	private String hello;

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

	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
		getBrands();
	}

	public List<Marque> getBrands() {
		if (brands == null) {
			brands = produitServiceAction.getBrands();
		}
		return brands;
	}

	public void addToBasket() {

		try {
			Produit produit = produitServiceAction.getProduitById(selectedProduit.getIdProduit());
			basket.addPoduct(produit, 1);
		} catch (CommandeGroupeeNotFoundException e) {
			LOGGER.info(e.getMessage());
			// return "/pages/private/errors/cmdNotFoundError.xhtml?faces-redirect=true";
		} catch (CommandeGroupeeValidatedExeception e) {
			LOGGER.info(e.getMessage());
			// return "/pages/private/errors/cmdValidatedError.xhtml?faces-redirect=true";
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			// return "/pages/private/errors/cmdValidatedError.xhtml?faces-redirect=true";
		}

	}

	public String getHello() {
		// HttpServletRequest req = (HttpServletRequest)
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getRequest();

		// String attribute = (String) req.getAttribute("id");

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String id = paramMap.get("id");

		return "hello" + id;
	}

	public List<ProduitBean> getFilteredProduits() {
		return filteredProduits;
	}

	public void setFilteredProduits(List<ProduitBean> filteredProduits) {
		this.filteredProduits = filteredProduits;
	}

	public List<ProduitBean> getProduits() {
		return produits;
	}

	public void setProduits(List<ProduitBean> produits) {
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

	public List<ProduitBean> getNewProducts() {

		List<ProduitBean> filtredProducts = new ArrayList<>();
		// if (newProducts == null || newProducts.isEmpty()) {
		newProducts = produitServiceAction.getNewProducts(userSessionBean.getIdOrga());
		// }
		if ((selectedBrandName == null || selectedBrandName.isEmpty())
				&& (selectedCategorieName == null || selectedCategorieName.isEmpty())) {
			return newProducts;
		}

		if (selectedBrandName != null && !selectedBrandName.isEmpty()) {

			for (ProduitBean produitBean : newProducts) {

				if (selectedBrandName.equals(produitBean.getMarqueNom())) {
					filtredProducts.add(produitBean);
				}
			}
		} else {
			filtredProducts.addAll(newProducts);
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
	
	public List<ProduitBean> getNewProductsIndex() {

		List<ProduitBean> filtredProducts = new ArrayList<ProduitBean>();
		newProducts = produitServiceAction.getNewProducts(userSessionBean.getIdOrga());
		/*ProduitBean produitBean = null;

		for (Iterator<ProduitBean> iterator = newProducts.iterator(); iterator.hasNext();) {
			produitBean = (ProduitBean) iterator.next();
			if (produitBean.isNouveauteSurHomePage()) {
				filtredProducts.add(produitBean);
			}
			
		}*/
		for (ProduitBean produitBean : newProducts) {

			if (produitBean.isNouveauteSurHomePage()) {
				filtredProducts.add(produitBean);
			}
		}
		return filtredProducts;
	}	

	public void setNewProducts(List<ProduitBean> newProducts) {
		this.newProducts = newProducts;
	}

	public ProduitServiceAction getProduitServiceAction() {
		return produitServiceAction;
	}

	public void setProduitServiceAction(ProduitServiceAction produitServiceAction) {
		this.produitServiceAction = produitServiceAction;
	}

	public ProduitBean getSelectedProduit() {
		return selectedProduit;
	}

	public void setSelectedProduit(ProduitBean selectedProduit) {
		this.selectedProduit = selectedProduit;
	}

	public String getSelectedBrand() {
		return selectedBrandName;
	}

	public void setSelectedBrand(String selectedBrand) {
		this.selectedBrandName = selectedBrand;
	}

	public CategorieServiceAction getCategorieServiceAction() {
		return categorieServiceAction;
	}

	public void setCategorieServiceAction(CategorieServiceAction categorieServiceAction) {
		this.categorieServiceAction = categorieServiceAction;
	}

	public String getSelectedCategorieName() {
		return selectedCategorieName;
	}

	public void setSelectedCategorieName(String selectedCategorie) {
		this.selectedCategorieName = selectedCategorie;
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

	public void setHello(String hello) {
		this.hello = hello;
	}

	public Produit getProduit() {

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String id = paramMap.get("id");
		LOGGER.info(id);

		return produitServiceAction.getProduit(new Long(id));
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

}
