package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.Categorie;
import com.marcanti.ecommerce.service.actions.CategorieServiceAction;
<<<<<<< HEAD
import com.marcanti.ecommerce.service.actions.SousCategorieServiceAction;
=======
>>>>>>> adminstration: authentification + list,add,update filleul + integration charte graphique
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "categoriesBean")
@RequestScoped
public class CategoriesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(CategoriesBean.class);

	private List<Categorie> categorieList;

	private List<Categorie> filteredCategorieList;
	
	private Categorie categorie;
	
	
	@ManagedProperty("#{param.idCategorie}")
	private Short idCategorie;
	
	@ManagedProperty("#{categorieService}")
	private CategorieServiceAction categorieService;
	
	@ManagedProperty("#{referentielBean}")
	private ReferentielBean referentielBean;

	public CategoriesBean() {
	}

	@PostConstruct
	public void init() {
		this.categorieList = categorieService.getCategorieList();
		this.categorie = new Categorie((short)0);
	}

	
	public ReferentielBean getReferentielBean() {
		return referentielBean;
	}

	public void setReferentielBean(ReferentielBean referentielBean) {
		this.referentielBean = referentielBean;
	}

	
	public List<Categorie> getCategorieList() {
		return categorieList;
	}

	public void setCategorieList(List<Categorie> categorieList) {
		this.categorieList = categorieList;
	}

	public List<Categorie> getFilteredCategorieList() {
		return filteredCategorieList;
	}

	public void setFilteredCategorieList(List<Categorie> filteredCategorieList) {
		this.filteredCategorieList = filteredCategorieList;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Short getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Short idCategorie) {
		this.idCategorie = idCategorie;
	}

	public CategorieServiceAction getCategorieService() {
		return categorieService;
	}

	public void setCategorieService(CategorieServiceAction categorieService) {
		this.categorieService = categorieService;
	}

	public String editCategorie() {

		Categorie categorie = new Categorie(getIdCategorie().shortValue());
		categorie= categorieService.getCategorie(categorie);
		setCategorie(categorie);
		return "categorie";

	}
	
	public String addCategorieView() {
		logger.info("addCategorieView");
		return "categorie";

	}	
	
	public String listCategorieView() {
		logger.info("listCategorieView");
		return "categories";

	}	
	
	public String insertOrUpdateCategorie() {
		
		FacesMessage facesMessage = new FacesMessage();
		String msg;
		String ecran ="categorie";
		
		logger.info("insertOrUpdateCategorie");

		if(this.categorie.getIdCategorie()==null || this.categorie.getIdCategorie().shortValue()==0){
				
			categorieService.insertCategorie(categorie);
			this.categorieList=categorieService.getCategorieList();
			msg = ParfumUtils.getBundleApplication().getString("message.ajouter.categorie");
		    ecran="categories";
		    
		}else{
				
			categorieService.updateCategorie(this.categorie);;
			msg = ParfumUtils.getBundleApplication().getString("message.modif.categorie");
		}
		facesMessage.setDetail(msg); 
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
	    FacesContext.getCurrentInstance().addMessage(null, facesMessage);		
		return ecran;
	}
	
	
	

}
