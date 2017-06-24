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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.Departement;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Profil;
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
	
	@ManagedProperty("#{param.oldMembreEmail}")
	private String oldMembreEmail;	

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
		UserSessionBean userSession = ParfumUtils.getUserSessionBean();
		Membre membre = new Membre(userSession.getIdMembre());
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
	
	public String getOldMembreEmail() {
		return oldMembreEmail;
	}

	public void setOldMembreEmail(String oldMembreEmail) {
		this.oldMembreEmail = oldMembreEmail;
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

		Membre membre = new Membre(getIdMembre());
		membre= membreService.getMembre(membre);
		UserSessionBean userSession = ParfumUtils.getUserSessionBean();
		membre.setIdOrga(new Organisation(userSession.getIdOrga()));
		membre.setIdDepartement(new Departement(userSession.getIdDepartement()));
		setFilleul(membre);
		return "filleul";

	}
	
	public String addFilleulView() {
		System.out.println("addFilleulView");
		return "filleul";

	}	
	
	public String listFilleulView() {
		System.out.println("listFilleulView");
		return "filleuls";

	}	
	
	public String insertOrUpdateFilleul() {
		
		FacesMessage facesMessage = new FacesMessage();
		Calendar calendar = Calendar.getInstance();
		Date dateToday =  calendar.getTime();
		String msg;
		String ecran ="filleul";
		
		System.out.println("insertOrUpdateFilleul");

		if(filleul.getIdMembre()==null || filleul.getIdMembre()==0L){
			if(authentificationService.emailExist(getMembreEmail())){
				msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_emailExist");
				facesMessage.setDetail(msg); 
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}else{
				
				UserSessionBean userSession = ParfumUtils.getUserSessionBean();
				filleul.setIdOrga(new Organisation(userSession.getIdOrga()));
				filleul.setIdDepartement(new Departement(userSession.getIdDepartement()));
				//TODO : récupérer idProfil = filleul + password par défaut
				filleul.setIdProfil(new Profil((short) 2));	
				String password = "@AuBonParfum";
				filleul.setPassword(DigestUtils.sha512Hex(password));
				filleul.setDateCreation(dateToday);
				membreService.insertFilleul(filleul,userSession);
				this.filleulsList = filleulsService.getFilleulsList(new Membre(userSession.getIdMembre()));
				/*msg = ParfumUtils.getBundleApplication().getString("message.ajouter.filleul");
				facesMessage.setDetail(msg); 
				facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			    FacesContext.getCurrentInstance().addMessage(null, facesMessage);*/
			    ecran="filleuls";
			}
		}else{
			//TODO : traiter le cas ou l'email change et qu'il existe dejà
			if(getMembreEmail().equals(getOldMembreEmail())){
				filleul.setDateModification(dateToday);
				membreService.updateFilleul(filleul);
				msg = ParfumUtils.getBundleApplication().getString("message.modif.filleul");
				facesMessage.setDetail(msg); 
				facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				
			}else{
				
				if(authentificationService.emailExist(getMembreEmail())){
					msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_emailExist");
					facesMessage.setDetail(msg); 
					facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				}else{
					filleul.setDateModification(dateToday);
					membreService.updateFilleul(filleul);
					msg = ParfumUtils.getBundleApplication().getString("message.modif.filleul");
					facesMessage.setDetail(msg); 
					facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
				    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				}
			}
		}
		return ecran;
	}	
	
	
	

}
