package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.Departement;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.UserSession;
import com.marcanti.ecommerce.service.actions.AuthentificationServiceAction;
import com.marcanti.ecommerce.service.actions.FilleulsServiceAction;
import com.marcanti.ecommerce.service.actions.MembreServiceAction;
import com.marcanti.ecommerce.service.actions.ReferentielServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "filleulsBean")
@RequestScoped
public class FilleulsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(FilleulsBean.class);

	private List<Membre> filleulsList;

	private List<Membre> filteredFilleulsList;
	
	private Membre filleul;
	
	private Map<String,Object> radioButtonOuiNon;
	
	@ManagedProperty("#{param.idMembre}")
	private Long idMembre;

	@ManagedProperty("#{filleulsService}")
	private FilleulsServiceAction filleulsService;
	
	@ManagedProperty("#{membreService}")
	private MembreServiceAction membreService;	
	
	@ManagedProperty("#{referentielService}")
	private ReferentielServiceAction referentielService;	
	
	@ManagedProperty("#{authentificationService}")
	private AuthentificationServiceAction authentificationService;

	public FilleulsBean() {
	}

	@PostConstruct
	public void init() {
		//TODO : récupéré l'objet session pour renseigner idMembre
		//ParfumUtils.getUserSessionBean().getIdMembre();
		Membre membre = new Membre(1L);
		this.filleulsList = filleulsService.getFilleulsList(membre);
		this.radioButtonOuiNon = ReferentielBean.radioButtonOuiNon;
		this.filleul = new Membre(0L);
	}
	
	
	public String getMembreNom() {
		return filleul.getMembreNom();
	}

	public void setMembreNom(String membreNom) {
		filleul.setMembreNom(membreNom);
	}

	
	public Map<String,Object> getRadioButtonOuiNon() {
		return radioButtonOuiNon;
	}

	public void setRadioButtonOuiNon(Map<String,Object> radioButtonOuiNon) {
		this.radioButtonOuiNon = radioButtonOuiNon;
	}

	public String getMembrePrenom() {
		return filleul.getMembrePrenom();
	}

	public String getMembreEmail() {
		return filleul.getMembreEmail();
	}

	public String getMembreTel() {
		return filleul.getMembreTel();
	}

	public Organisation getIdOrga() {
		return filleul.getIdOrga();
	}

	public Long getIdMembre() {
		return idMembre;
	}

	public void setIdMembre(Long idMembre) {
		this.idMembre = idMembre;
	}
	
	public Membre getFilleul() {
		return filleul;
	}

	public void setFilleul(Membre filleul) {
		this.filleul = filleul;
	}

	public FilleulsServiceAction getFilleulsService() {
		return filleulsService;
	}

	public void setFilleulsService(FilleulsServiceAction filleulsService) {
		this.filleulsService = filleulsService;
	}

	public MembreServiceAction getMembreService() {
		return membreService;
	}

	public void setMembreService(MembreServiceAction membreService) {
		this.membreService = membreService;
	}

	public ReferentielServiceAction getReferentielService() {
		return referentielService;
	}

	public void setReferentielService(ReferentielServiceAction referentielService) {
		this.referentielService = referentielService;
	}
	

	public AuthentificationServiceAction getAuthentificationService() {
		return authentificationService;
	}

	public void setAuthentificationService(AuthentificationServiceAction authentificationService) {
		this.authentificationService = authentificationService;
	}

	public void setFilleulsList(List<Membre> filleulsList) {
		this.filleulsList = filleulsList;
	}

	public List<Membre> getFilleulsList() {
		return filleulsList;
	}

	public List<Membre> getFilteredFilleulsList() {
		return filteredFilleulsList;
	}

	public void setFilteredFilleulsList(List<Membre> filteredFilleulsList) {
		this.filteredFilleulsList = filteredFilleulsList;
	}

	
	public String editFilleul() {

		//TODO : renseigner idOrga du membre connecté et non pas idOrga du filleuil
		Membre membre = new Membre(getIdMembre());
		membre= membreService.getMembre(membre);
		//UserSession userSession = ParfumUtils.getUserSessionBean();
		//membre.setIdOrga(new Organisation(userSession.getIdOrga()));
		//membre.setIdDepartement(new Departement(userSession.getIdDepartement()));
		setFilleul(membre);
		return "filleul";

	}
	
	public String addFilleulView() {
		System.out.println("addFilleulView");
		return "filleul";

	}
	
	public String insertFilleul() {
		
		FacesMessage facesMessage = new FacesMessage();
		
		System.out.println("insertFilleul");
		System.out.println("id  : " + getIdMembre());
		System.out.println("nom : " + getMembreNom());
		System.out.println("prenom : " + getMembrePrenom());
		System.out.println("email : " + getMembreEmail());
		System.out.println("tel : " + getMembreTel());
		
		if(authentificationService.emailExist(getMembreEmail())){
			String msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_emailExist");
			facesMessage.setDetail(msg); 
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}else{
			membreService.insertFilleul(filleul);
		}
		return "filleul";
	}	
	
	public String updateFilleul() {
		System.out.println("updateFilleul");
		System.out.println("id membre : " + this.filleul.getIdMembre());
		System.out.println("nom : " + this.filleul.getMembreNom());
		System.out.println("prenom : " + this.filleul.getMembrePrenom());
		System.out.println("email : " + this.filleul.getMembreEmail());
		System.out.println("tel : " + this.filleul.getMembreTel());
		System.out.println("isActif : " + this.filleul.getIsActif());
		System.out.println("isDefaultPassword : " + this.filleul.getIsDefaultPassword());
		membreService.updateFilleul(filleul);
		return "filleul";

	}	
	
	
	

}
