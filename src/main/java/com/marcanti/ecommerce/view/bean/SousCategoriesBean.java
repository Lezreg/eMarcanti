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
import com.marcanti.ecommerce.model.SousCategorie;
import com.marcanti.ecommerce.service.actions.SousCategorieServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "sousCategoriesBean")
@RequestScoped
public class SousCategoriesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(SousCategoriesBean.class);

	private List<SousCategorie> sousCategorieList;

	private List<SousCategorie> filteredSousCategorieList;
	
	private SousCategorie sousCategorie;
	
	
	@ManagedProperty("#{param.idSousCategorie}")
	private Short idSousCategorie;
	
	@ManagedProperty("#{sousCategorieService}")
	private SousCategorieServiceAction sousCategorieService;
	
	@ManagedProperty("#{referentielBean}")
	private ReferentielBean referentielBean;

	public SousCategoriesBean() {
	}

	@PostConstruct
	public void init() {
		this.sousCategorieList = sousCategorieService.getSousCategorieList();
		this.sousCategorie = new SousCategorie((short)0);
		Categorie categorie = new Categorie((short)0);
		this.sousCategorie.setIdCategorieProduit(categorie);
	}

	
	
	public ReferentielBean getReferentielBean() {
		return referentielBean;
	}

	public void setReferentielBean(ReferentielBean referentielBean) {
		this.referentielBean = referentielBean;
	}

	public List<SousCategorie> getSousCategorieList() {
		return sousCategorieList;
	}

	public void setSousCategorieList(List<SousCategorie> sousCategorieList) {
		this.sousCategorieList = sousCategorieList;
	}

	public List<SousCategorie> getFilteredSousCategorieList() {
		return filteredSousCategorieList;
	}

	public void setFilteredSousCategorieList(List<SousCategorie> filteredSousCategorieList) {
		this.filteredSousCategorieList = filteredSousCategorieList;
	}

	public SousCategorie getSousCategorie() {
		return sousCategorie;
	}

	public void setSousCategorie(SousCategorie sousCategorie) {
		this.sousCategorie = sousCategorie;
	}

	public Short getIdSousCategorie() {
		return idSousCategorie;
	}

	public void setIdSousCategorie(Short idSousCategorie) {
		this.idSousCategorie = idSousCategorie;
	}

	public SousCategorieServiceAction getSousCategorieService() {
		return sousCategorieService;
	}

	public void setSousCategorieService(SousCategorieServiceAction sousCategorieService) {
		this.sousCategorieService = sousCategorieService;
	}
	
	public Short getIdCategorie() {
		return this.sousCategorie.getIdCategorieProduit().getIdCategorie();
	}

	public void setIdCategorie(Short idCategorie) {
		Categorie categorie = new Categorie(idCategorie);
		this.sousCategorie.setIdCategorieProduit(categorie);
	}

	
	public String editSousCategorie() {

		SousCategorie sousCategorie = new SousCategorie(getIdSousCategorie().shortValue());
		sousCategorie= sousCategorieService.getSousCategorie(sousCategorie);
		setSousCategorie(sousCategorie);
		return "sousCategorie";

	}
	
	public String addSousCategorieView() {
		logger.info("addSousCategorieView");
		return "sousCategorie";

	}	
	
	
	public String insertOrUpdateSousCategorie() {
		
		FacesMessage facesMessage = new FacesMessage();
		String msg;
		String ecran ="sousCategorie";
		
		logger.info("insertOrUpdateSousCategorie");

		if(this.sousCategorie.getIdSousCategorie()==null || this.sousCategorie.getIdSousCategorie().shortValue()==0){
				
			sousCategorieService.insertSousCategorie(sousCategorie);
			this.sousCategorieList=sousCategorieService.getSousCategorieList();
			msg = ParfumUtils.getBundleApplication().getString("message.ajouter.ss_categorie");
		    ecran="sousCategories";
		    
		}else{
				
			sousCategorieService.updateSousCategorie(this.sousCategorie);;
			msg = ParfumUtils.getBundleApplication().getString("message.modif.ss_categorie");
		}
		facesMessage.setDetail(msg); 
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
	    FacesContext.getCurrentInstance().addMessage(null, facesMessage);		
		return ecran;
	}
	
	
	

}
