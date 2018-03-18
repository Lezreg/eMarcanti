package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;

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
import com.marcanti.ecommerce.utils.Mail;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "filleulsBean")
@SessionScoped
public class FilleulsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(FilleulsBean.class);

	private List<Membre> filleulsList;

	private Membre filleul;
	
	private Long idMembre;
	
	private String oldMembreEmail;	
	
	private String titre="";

	@ManagedProperty("#{filleulsService}")
	private FilleulsServiceAction filleulsService;
	
	@ManagedProperty("#{membreService}")
	private MembreServiceAction membreService;	
	
	@ManagedProperty("#{authentificationService}")
	private AuthentificationServiceAction authentificationService;
	
	@ManagedProperty("#{referentielBean}")
	private ReferentielBean referentielBean;

	public FilleulsBean() {
	}

	@PostConstruct
	public void init() {
		UserSessionBean userSession = ParfumUtils.getUserSessionBean();
		Membre userConnected = new Membre(userSession.getIdMembre());
		this.filleulsList = filleulsService.getFilleulsList(userConnected);
		this.filleul = new Membre(0L);
		this.filleul.setIdOrga(new Organisation((userSession.getIdOrga())));
		this.filleul.setIdProfil((new Profil(ReferentielBean.PROFIL_FILLEUL)));
	}
	
	public String getMembreNom() {
		return filleul.getMembreNom();
	}

	public void setMembreNom(String membreNom) {
		filleul.setMembreNom(membreNom);
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
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
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

	public AuthentificationServiceAction getAuthentificationService() {
		return authentificationService;
	}

	public ReferentielBean getReferentielBean() {
		return referentielBean;
	}

	public void setReferentielBean(ReferentielBean referentielBean) {
		this.referentielBean = referentielBean;
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

	public String editFilleul() {

		Membre membre = new Membre(getIdMembre());
		membre= membreService.getMembre(membre);
		UserSessionBean userSession = ParfumUtils.getUserSessionBean();
		membre.setIdOrga(new Organisation(userSession.getIdOrga()));
		membre.setIdDepartement(new Departement(userSession.getIdDepartement()));
		setFilleul(membre);
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_filleul");
		return "filleul";
	}
	
	public String addFilleulView() {
		UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();
		this.filleul = new Membre(0L);
		//if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_MANAGER){
			this.filleul.setIdOrga(new Organisation((userSessionBean.getIdOrga())));
			this.filleul.setIdProfil((new Profil(ReferentielBean.PROFIL_FILLEUL)));
		//}
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_ajouter_filleul");
		return "filleul";
	}	
	
	public String insertOrUpdateFilleul() {
		
		FacesMessage facesMessage = new FacesMessage();
		Calendar calendar = Calendar.getInstance();
		Date dateToday =  calendar.getTime();
		String msg;
		String ecran ="filleul";
		
		if(filleul.getIdMembre()==null || filleul.getIdMembre()==0L){
			if(authentificationService.emailExist(getMembreEmail())){
				msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_emailExist");
				facesMessage.setSummary(msg); 
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}else{
				
				UserSessionBean userSession = ParfumUtils.getUserSessionBean();
				filleul.setIdOrga(new Organisation(userSession.getIdOrga()));
				filleul.setIdDepartement(new Departement(userSession.getIdDepartement()));
				filleul.setIdProfil(new Profil(ReferentielBean.PROFIL_FILLEUL));	
				String password = referentielBean.getDefaultPassword();
				filleul.setPassword(DigestUtils.sha512Hex(password));
				filleul.setDateCreation(dateToday);
				membreService.insertFilleulMembre(filleul,userSession);
				this.filleulsList = filleulsService.getFilleulsList(new Membre(userSession.getIdMembre()));
				try {
					logger.info("send mail filleul with password : " + password);
					Mail.send(filleul.getMembreEmail(), ParfumUtils.getBundleApplication().getString("message.membre.topic"),MessageFormat.format(ParfumUtils.getBundleApplication().getString("message.filleul.mail"),filleul.getMembreEmail(),password));
				} catch (MessagingException e) {
					logger.error("ERROR send mail filleul with password : ",e);
				}
				msg = ParfumUtils.getBundleApplication().getString("message.ajouter.filleul");
				facesMessage.setSummary(msg); 
				facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			    ecran="filleuls";
			}
			this.titre = ParfumUtils.getBundleApplication().getString("libelle_ajouter_filleul");
		}else{
			if(getMembreEmail().equals(getOldMembreEmail())){
				filleul.setDateModification(dateToday);
				membreService.updateFilleulMembre(filleul);
				msg = ParfumUtils.getBundleApplication().getString("message.modif.filleul");
				facesMessage.setSummary(msg); 
				facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				
			}else{
				
				if(authentificationService.emailExist(getMembreEmail())){
					msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_emailExist");
					facesMessage.setSummary(msg); 
					facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				}else{
					filleul.setDateModification(dateToday);
					membreService.updateFilleulMembre(filleul);
					msg = ParfumUtils.getBundleApplication().getString("message.modif.filleul");
					facesMessage.setSummary(msg); 
					facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
				    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				}
			}
			this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_filleul");
		}
		UserSessionBean userSession = ParfumUtils.getUserSessionBean();
		Membre userConnected = new Membre(userSession.getIdMembre());
		this.filleulsList = filleulsService.getFilleulsList(userConnected);
		return ecran;
	}	
	
	public String editFilleulsList() {
		
		UserSessionBean userSession = ParfumUtils.getUserSessionBean();
		Membre userConnected = new Membre(userSession.getIdMembre());
		this.filleulsList = filleulsService.getFilleulsList(userConnected);
		this.filleul = new Membre(0L);
		this.filleul.setIdOrga(new Organisation((userSession.getIdOrga())));
		this.filleul.setIdProfil((new Profil(ReferentielBean.PROFIL_FILLEUL)));
		return "filleuls";

	}
	

}
