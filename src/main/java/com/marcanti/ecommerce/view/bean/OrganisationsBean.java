package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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

import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.service.actions.OrganisationServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "organisationsBean")
@SessionScoped
public class OrganisationsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(OrganisationsBean.class);

	private List<Organisation> organisationList;

	private List<Organisation> filteredOrganisationList;
	
	private Organisation organisation;
	
	private String titre="";
	
	//@ManagedProperty("#{param.idOrga}")
	private long idOrga;
	
	@ManagedProperty("#{organisationService}")
	private OrganisationServiceAction organisationService;
	
	@ManagedProperty("#{referentielBean}")
	private ReferentielBean referentielBean;

	public OrganisationsBean() {
	}

	@PostConstruct
	public void init() {
		this.organisationList = organisationService.getOrganisationList();
		this.organisation = new Organisation(0L);
		this.organisation.setIsActive(true);
		this.organisation.setIsAlivrerAvantPaiement(true);
		this.organisation.setAccesCatalogueComplet(true);
		this.organisation.setEnvoiMailConfirmation(true);
	}

	
	public ReferentielBean getReferentielBean() {
		return referentielBean;
	}

	public void setReferentielBean(ReferentielBean referentielBean) {
		this.referentielBean = referentielBean;
	}

	
	public List<Organisation> getOrganisationList() {
		return organisationList;
	}

	public void setOrganisationList(List<Organisation> organisationList) {
		this.organisationList = organisationList;
	}

	public List<Organisation> getFilteredOrganisationList() {
		return filteredOrganisationList;
	}

	public void setFilteredOrganisationList(List<Organisation> filteredOrganisationList) {
		this.filteredOrganisationList = filteredOrganisationList;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public long getIdOrga() {
		return idOrga;
	}

	public void setIdOrga(long idOrga) {
		this.idOrga = idOrga;
	}
	
    public String getIsActive() {
    	if (organisation.getIsActive()) return "y";
    	else return "n";
    }

	public OrganisationServiceAction getOrganisationService() {
		return organisationService;
	}

	public void setOrganisationService(OrganisationServiceAction organisationService) {
		this.organisationService = organisationService;
	}

	public Map<String, Object> getRadioButtonOuiNon() {
		return referentielBean.getRadioButtonOuiNon();
	}

	public void setRadioButtonOuiNon(Map<String, Object> radioButtonOuiNon) {
		referentielBean.setRadioButtonOuiNon(radioButtonOuiNon);
	}

	public String editOrganisation() {

		Organisation organisation = new Organisation(getIdOrga());
		organisation= organisationService.getOrganisation(organisation);
		setOrganisation(organisation);
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_orga");
		return "organisation";

	}
	
	public String addOrganisationView() {
		logger.info("addOrganisationView");
		this.organisation = new Organisation();
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_ajouter_orga");
		return "organisation";

	}	
	
	
	public String insertOrUpdateOrganisation() {
		
		FacesMessage facesMessage = new FacesMessage();
		Calendar calendar = Calendar.getInstance();
		Date dateToday =  calendar.getTime();
		String msg;
		String ecran ="organisation";
		
		logger.info("insertOrUpdateOrganisation");

		if(organisation.getIdOrga()==null || organisation.getIdOrga()==0L){
				
			organisation.setDateCreation(dateToday);
			organisationService.insertOrganisation(organisation);
			this.organisationList=organisationService.getOrganisationList();
			msg = ParfumUtils.getBundleApplication().getString("message.ajouter.orga");
			this.titre = ParfumUtils.getBundleApplication().getString("libelle_ajouter_membre");
		    ecran="organisations";
		    
		}else{
				
			organisation.setDateModification(dateToday);
			organisationService.updateOrganisation(organisation);
			msg = ParfumUtils.getBundleApplication().getString("message.modif.orga");
			this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_orga");

		}
		facesMessage.setSummary(msg); 
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
	    FacesContext.getCurrentInstance().addMessage(null, facesMessage);		
		return ecran;
	}
	
	
	

}
