package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.Departement;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.service.actions.DepartementServiceAction;
import com.marcanti.ecommerce.service.actions.OrganisationServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "departementsBean")
@SessionScoped
public class DepartementsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(DepartementsBean.class);

	private List<Departement> departementList;

	private List<Organisation> organisationList;
	
	private Departement departement;
	
	private String titre="";
	
	private String orgaDisabled="true";
	
	//@ManagedProperty("#{param.idDepartement}")
	private long idDepartement;
	
	@ManagedProperty("#{departementService}")
	private DepartementServiceAction departementService;
	
	@ManagedProperty("#{organisationService}")
	private OrganisationServiceAction organisationService;	
	
	@ManagedProperty("#{referentielBean}")
	private ReferentielBean referentielBean;

	public DepartementsBean() {
	}

	@PostConstruct
	public void init() {
		
		UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();
		
		if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_MANAGER){
			Organisation idOrga = new Organisation(userSessionBean.getIdOrga());
			this.departementList = departementService.getDepartementByOrgaList(idOrga);
			setOrgaDisabled("true");
		}else if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_ADMIN){
			this.departementList = departementService.getDepartementList();
			setOrgaDisabled("false");
		}
		setOrganisationList(organisationService.getOrganisationList());
		this.departement = new Departement(0L);
		this.departement.setIsActif(true);
		Organisation idOrga = new Organisation(userSessionBean.getIdOrga());
		this.departement.setIdOrga(idOrga);
		
	}
	
	public String getOrgaDisabled() {
		return orgaDisabled;
	}

	public void setOrgaDisabled(String orgaDisabled) {
		this.orgaDisabled = orgaDisabled;
	}

	public ReferentielBean getReferentielBean() {
		return referentielBean;
	}

	public void setReferentielBean(ReferentielBean referentielBean) {
		this.referentielBean = referentielBean;
	}

	public Map<String, Object> getRadioButtonOuiNon() {
		return referentielBean.getRadioButtonOuiNon();
	}

	public void setRadioButtonOuiNon(Map<String, Object> radioButtonOuiNon) {
		referentielBean.setRadioButtonOuiNon(radioButtonOuiNon);
	}
	
	public List<Departement> getDepartementList() {
		return departementList;
	}

	public void setDepartementList(List<Departement> departementList) {
		this.departementList = departementList;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public long getIdDepartement() {
		return idDepartement;
	}

	public void setIdDepartement(long idDepartement) {
		this.idDepartement = idDepartement;
	}

	public DepartementServiceAction getDepartementService() {
		return departementService;
	}

	public void setDepartementService(DepartementServiceAction departementService) {
		this.departementService = departementService;
	}
	
	public OrganisationServiceAction getOrganisationService() {
		return organisationService;
	}

	public void setOrganisationService(OrganisationServiceAction organisationService) {
		this.organisationService = organisationService;
	}

	public List<Organisation> getOrganisationList() {
		return organisationList;
	}

	public void setOrganisationList(List<Organisation> organisationList) {
		this.organisationList = organisationList;
	}

	public String editDepartement() {

		UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();
		if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_MANAGER){
			setOrgaDisabled("true");
		}else if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_ADMIN){
			setOrganisationList(organisationService.getActiveOrganisationList());
		}
		Departement departement = new Departement(getIdDepartement());
		departement= departementService.getDepartement(departement);
		setDepartement(departement);
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_dept");
		return "departement";

	}
	
	public String addDepartementView() {
		logger.info("addDepartementView");
		this.departement=new Departement();
		this.departement.setIdOrga(new Organisation(0L));
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_ajouter_dept");
		return "departement";

	}	
	
	public String insertOrUpdateDepartement() {
		
		FacesMessage facesMessage = new FacesMessage();
		String msg;
		String ecran ="departement";
		
		logger.info("insertOrUpdateDepartement");

		if(departement.getIdDepartement()==null || departement.getIdDepartement()==0L){
				
			departementService.insertDepartement(departement);
			this.titre = ParfumUtils.getBundleApplication().getString("libelle_ajouter_membre");
			msg = ParfumUtils.getBundleApplication().getString("message.ajouter.dept");
		    ecran="departements";
		    
		}else{
				
			departementService.updateDepartement(this.departement);
			this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_dept");
			msg = ParfumUtils.getBundleApplication().getString("message.modif.dept");
		}
		//on rafraichit la liste des departements
		if(ParfumUtils.getUserSessionBean().getIdProfil()==ReferentielBean.PROFIL_MANAGER){
			Organisation idOrga = new Organisation(ParfumUtils.getUserSessionBean().getIdOrga());
			this.departementList = departementService.getDepartementByOrgaList(idOrga);
		}else if(ParfumUtils.getUserSessionBean().getIdProfil()==ReferentielBean.PROFIL_ADMIN){
			this.departementList = departementService.getDepartementList();
		}
		facesMessage.setSummary(msg); 
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
	    FacesContext.getCurrentInstance().addMessage(null, facesMessage);		
		return ecran;
	}
	
	
	

}
