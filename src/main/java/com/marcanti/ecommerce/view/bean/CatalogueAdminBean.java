package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.model.VCatalogueAdmin;
import com.marcanti.ecommerce.service.actions.ProduitServiceAction;
import com.marcanti.ecommerce.service.actions.VCatalogueAdminServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "catalogueAdminBean")
@SessionScoped
public class CatalogueAdminBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(CatalogueAdminBean.class);

	private List<VCatalogueAdmin> catalogueList;

	private VCatalogueAdmin catalogue;
	
	private Produit produit;	
	
	private String titre="";
	
	private Long idProduit;	
	
	
	@ManagedProperty("#{catalogueService}")
	private VCatalogueAdminServiceAction catalogueService;
	
	@ManagedProperty("#{produitService}")
	private ProduitServiceAction produitService;	
	
	public CatalogueAdminBean() {
	}

	@PostConstruct
	public void init() {
		this.catalogueList = catalogueService.getCatalogueList();
		System.out.println("size : " + catalogueList.size());
	}

	public List<VCatalogueAdmin> getCatalogueList() {
		return catalogueList;
	}

	public void setCatalogueList(List<VCatalogueAdmin> catalogueList) {
		this.catalogueList = catalogueList;
	}

	public VCatalogueAdmin getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(VCatalogueAdmin catalogue) {
		this.catalogue = catalogue;
	}

	public VCatalogueAdminServiceAction getCatalogueService() {
		return catalogueService;
	}

	public void setCatalogueService(VCatalogueAdminServiceAction catalogueService) {
		this.catalogueService = catalogueService;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}
	
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public ProduitServiceAction getProduitService() {
		return produitService;
	}

	public void setProduitService(ProduitServiceAction produitService) {
		this.produitService = produitService;
	}

	public String editProduit() {

		Produit produit = produitService.getProduit(getIdProduit());
		setProduit(produit);
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_produit");
		return "produit";

	}
	
	public String addProduitView() {
		logger.info("addProduitView");
		this.produit=new Produit();
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_ajouter_produit");
		return "produit";

	}	
	
	
	public String insertOrUpdateProduit() {
		
		FacesMessage facesMessage = new FacesMessage();
		String msg;
		String ecran ="produit";
		
		logger.info("insertOrUpdateProduit");

		if(this.produit.getIdProduit()==null || this.produit.getIdProduit()==0L){
				
			produitService.insertProduit(this.produit);
			msg = ParfumUtils.getBundleApplication().getString("message.ajouter.ss_categorie");
			this.titre = ParfumUtils.getBundleApplication().getString("libelle_ajouter_produit");
		    ecran="sousCategories";
		    
		}else{
				
			produitService.updateProduit(this.produit);
			msg = ParfumUtils.getBundleApplication().getString("message.modif.ss_categorie");
			this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_produit");
		}
		//on rafraichit la liste des sous-categories
		this.catalogueList=catalogueService.getCatalogueList();
		facesMessage.setSummary(msg); 
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
	    FacesContext.getCurrentInstance().addMessage(null, facesMessage);		
		return ecran;
	}	

}
