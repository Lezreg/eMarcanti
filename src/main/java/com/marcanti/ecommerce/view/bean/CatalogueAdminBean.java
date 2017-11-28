package com.marcanti.ecommerce.view.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.Categorie;
import com.marcanti.ecommerce.model.Marque;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.model.SousCategorie;
import com.marcanti.ecommerce.model.VCatalogueAdmin;
import com.marcanti.ecommerce.service.actions.CategorieServiceAction;
import com.marcanti.ecommerce.service.actions.MarqueServiceAction;
import com.marcanti.ecommerce.service.actions.ProduitServiceAction;
import com.marcanti.ecommerce.service.actions.SousCategorieServiceAction;
import com.marcanti.ecommerce.service.actions.VCatalogueAdminServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "catalogueAdminBean")
@SessionScoped
public class CatalogueAdminBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(CatalogueAdminBean.class);

	private List<VCatalogueAdmin> catalogueList;
	
	private List<Marque> marqueList;
	
	private List<Categorie> categorieList;
	
	private List<SousCategorie> sousCategorieList;	

	private VCatalogueAdmin catalogue;
	
	private Produit produit;	
	
	private String titre="";
	
	private Long idProduit;	
	
	private UploadedFile uploadedPhoto;
	
	private UploadedFile uploadedPhotoMoyenne;
	
	private UploadedFile uploadedPhotoLarge;
	
	@ManagedProperty("#{catalogueService}")
	private VCatalogueAdminServiceAction catalogueService;
	
	@ManagedProperty("#{marqueService}")
	private MarqueServiceAction marqueService;
	
	@ManagedProperty("#{categorieService}")
	private CategorieServiceAction categorieService;	
	
	@ManagedProperty("#{sousCategorieService}")
	private SousCategorieServiceAction sousCategorieService;	
	
	@ManagedProperty("#{produitService}")
	private ProduitServiceAction produitService;
	
	@ManagedProperty("#{referentielBean}")
	private ReferentielBean referentielBean;	
	
	public CatalogueAdminBean() {
	}

	@PostConstruct
	public void init() {
		this.catalogueList = catalogueService.getCatalogueList();
		this.marqueList = marqueService.getMarqueList();
		this.categorieList = categorieService.getCategorieList();
		this.sousCategorieList = sousCategorieService.getSousCategorieList();
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
	
	public List<Marque> getMarqueList() {
		return marqueList;
	}

	public void setMarqueList(List<Marque> marqueList) {
		this.marqueList = marqueList;
	}

	public List<Categorie> getCategorieList() {
		return categorieList;
	}

	public void setCategorieList(List<Categorie> categorieList) {
		this.categorieList = categorieList;
	}

	public List<SousCategorie> getSousCategorieList() {
		return sousCategorieList;
	}

	public void setSousCategorieList(List<SousCategorie> sousCategorieList) {
		this.sousCategorieList = sousCategorieList;
	}

	public MarqueServiceAction getMarqueService() {
		return marqueService;
	}

	public void setMarqueService(MarqueServiceAction marqueService) {
		this.marqueService = marqueService;
	}

	public CategorieServiceAction getCategorieService() {
		return categorieService;
	}

	public void setCategorieService(CategorieServiceAction categorieService) {
		this.categorieService = categorieService;
	}

	public SousCategorieServiceAction getSousCategorieService() {
		return sousCategorieService;
	}

	public void setSousCategorieService(SousCategorieServiceAction sousCategorieService) {
		this.sousCategorieService = sousCategorieService;
	}
	
	public String getQteEnStock() {
		if(String.valueOf(this.produit.getQteEnStock()).equals("0")){
			return "";
		}else
			return String.valueOf(this.produit.getQteEnStock());
	}

	public void setQteEnStock(String qteEnStock) {
		this.produit.setQteEnStock(Short.valueOf(qteEnStock));
	}
	

	public UploadedFile getUploadedPhoto() {
		return uploadedPhoto;
	}

	public void setUploadedPhoto(UploadedFile uploadedPhoto) {
		this.uploadedPhoto = uploadedPhoto;
	}

	public UploadedFile getUploadedPhotoMoyenne() {
		return uploadedPhotoMoyenne;
	}

	public void setUploadedPhotoMoyenne(UploadedFile uploadedPhotoMoyenne) {
		this.uploadedPhotoMoyenne = uploadedPhotoMoyenne;
	}

	public UploadedFile getUploadedPhotoLarge() {
		return uploadedPhotoLarge;
	}

	public void setUploadedPhotoLarge(UploadedFile uploadedPhotoLarge) {
		this.uploadedPhotoLarge = uploadedPhotoLarge;
	}

	public ReferentielBean getReferentielBean() {
		return referentielBean;
	}

	public void setReferentielBean(ReferentielBean referentielBean) {
		this.referentielBean = referentielBean;
	}

	public String editProduit() {

		Produit produit = produitService.getProduit(getIdProduit());
		setProduit(produit);
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_produit");
		return "produit";

	}
	
	public String addProduitView() {
		logger.info("addProduitView");
		this.produit = new Produit();
		this.produit.setIdMarque(new Marque((short)0));
		this.produit.setIdCategorie(new Categorie((short)0));
		this.produit.setIdSousCategorie(new SousCategorie((short)0));
		this.uploadedPhoto = null;
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
			msg = ParfumUtils.getBundleApplication().getString("message.ajouter.produit");
			this.titre = ParfumUtils.getBundleApplication().getString("libelle_ajouter_produit");
		    ecran="produits";
		    
		}else{
				
			produitService.updateProduit(this.produit);
			msg = ParfumUtils.getBundleApplication().getString("message.modif.produit");
			this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_produit");
		}
		//on rafraichit la liste des sous-categories
		this.catalogueList=catalogueService.getCatalogueList();
		facesMessage.setSummary(msg); 
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
	    FacesContext.getCurrentInstance().addMessage(null, facesMessage);		
		return ecran;
	}	
	
	public void uploadHandlerPhoto(FileUploadEvent ev) {

		FileOutputStream fileOuputStream=null;
		this.uploadedPhoto = ev.getFile();
		String photoCatalogue = ParfumUtils.getUniqueName(referentielBean.getUploadFolderPath(), uploadedPhoto.getFileName());
		File fileDest = new File(referentielBean.getUploadFolderPath()+File.separator+photoCatalogue);
		this.produit.setProduitPhotoURL(photoCatalogue);
		byte[] content = uploadedPhoto.getContents();
		try {
			fileOuputStream = new FileOutputStream(fileDest);
			fileOuputStream.write(content);
			fileOuputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fileOuputStream!=null) {
				try {
					fileOuputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void uploadHandlerPhotoMoyenne(FileUploadEvent ev) {

		FileOutputStream fileOuputStream=null;
		this.uploadedPhotoMoyenne = ev.getFile();
		String photoCatalogue = ParfumUtils.getUniqueName(referentielBean.getUploadFolderPath(), uploadedPhotoMoyenne.getFileName());
		File fileDest = new File(referentielBean.getUploadFolderPath()+File.separator+photoCatalogue);
		this.produit.setProduitPhotoTailleMediumURL(photoCatalogue);
		byte[] content = uploadedPhotoMoyenne.getContents();
		try {
			fileOuputStream = new FileOutputStream(fileDest);
			fileOuputStream.write(content);
			fileOuputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fileOuputStream!=null) {
				try {
					fileOuputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}	
	
	public void uploadHandlerPhotoLarge(FileUploadEvent ev) {

		FileOutputStream fileOuputStream=null;
		this.uploadedPhotoLarge = ev.getFile();
		String photoCatalogue = ParfumUtils.getUniqueName(referentielBean.getUploadFolderPath(), uploadedPhotoLarge.getFileName());
		File fileDest = new File(referentielBean.getUploadFolderPath()+File.separator+photoCatalogue);
		this.produit.setProduitPhotoTailleLargeURL(photoCatalogue);
		byte[] content = uploadedPhotoLarge.getContents();
		try {
			fileOuputStream = new FileOutputStream(fileDest);
			fileOuputStream.write(content);
			fileOuputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fileOuputStream!=null) {
				try {
					fileOuputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
