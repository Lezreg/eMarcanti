package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.marcanti.ecommerce.model.Filleul;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Profil;
import com.marcanti.ecommerce.service.actions.AuthentificationServiceAction;
import com.marcanti.ecommerce.service.actions.DepartementServiceAction;
import com.marcanti.ecommerce.service.actions.FilleulsServiceAction;
import com.marcanti.ecommerce.service.actions.MembreServiceAction;
import com.marcanti.ecommerce.service.actions.OrganisationServiceAction;
import com.marcanti.ecommerce.utils.Mail;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "membreBean")
@SessionScoped
public class MembreBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(MembreBean.class);
	
	private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	private List<Organisation> organisationList;
	
	private List<Departement> departementList;
	
	private List<Profil> roleList;
	
	private List<Membre> parrainList;
	
	private Membre membre;
	
	private String orgaDisabled="true";
	
	private String hasReducRendered="false";
	
	private String parrainRendered="false";
	
	private Long idMembre;
	
	private Long idMembreParrain;	
	
	private String oldMembreEmail;
	
	private Long idOrgaSelected;	
	
	private String isAddAction="false";
	
	private String titre="";
	
	@ManagedProperty("#{membreService}")
	private MembreServiceAction membreService;
	
	@ManagedProperty("#{authentificationService}")
	private AuthentificationServiceAction authentificationService;	
	
	@ManagedProperty("#{organisationService}")
	private OrganisationServiceAction organisationService;
	
	@ManagedProperty("#{departementService}")
	private DepartementServiceAction departementService;	
	
	@ManagedProperty("#{filleulsService}")
	private FilleulsServiceAction filleulsService;
	
	@ManagedProperty("#{referentielBean}")
	private ReferentielBean referentielBean;
	
	//@ManagedProperty("#{messageBean}")
	//private MessageBean messageBean;	

	public MembreBean() {
	}

	@PostConstruct
	public void init() {
		
		UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();
		Organisation idOrgaUserConnected = new Organisation(userSessionBean.getIdOrga());
		
		this.membre = new Membre(0L);
		this.membre.setIdDepartement(new Departement(0L));
		this.membre.setIdProfil(new Profil((new Short("0"))));
		this.membre.setIdOrga(new Organisation(0L));
		
		if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_MANAGER){
			this.membre.setIdOrga(idOrgaUserConnected);
			setHasReducRendered("false");
			setOrgaDisabled("true");
			this.roleList=ReferentielBean.getManagerRoleList();
		}else if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_ADMIN){
			setHasReducRendered("true");
			setOrgaDisabled("false");
			this.roleList=ReferentielBean.getAdminRoleList();
		}else if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_MEMBRE){
			this.membre.setIdOrga(idOrgaUserConnected);
			setHasReducRendered("false");
			setOrgaDisabled("true");
			this.roleList=ReferentielBean.getMemberRoleList();
		}
		this.organisationList = organisationService.getOrganisationList();
		this.departementList = departementService.getDepartementByOrgaList(organisationList.get(0));
		this.parrainList = new ArrayList<Membre>();
		if(this.membre!=null && this.membre.getIdProfil().getIdProfil()==ReferentielBean.PROFIL_FILLEUL){
			setParrainRendered("true");
			//Filleul filleul = filleulsServiceAction.getFilleul(getIdMembre());
			//setIdMembreParrain(filleul.getIdMembreParrain().getIdMembre());
			
		}else{
			setParrainRendered("false");
		}
	}
	
	public String getMembreNom() {
		return membre.getMembreNom();
	}

	public void setMembreNom(String membreNom) {
		membre.setMembreNom(membreNom);
	}

	public String getMembrePrenom() {
		return membre.getMembrePrenom();
	}

	public String getMembreEmail() {
		return membre.getMembreEmail();
	}

	public String getMembreTel() {
		return membre.getMembreTel();
	}

	public Organisation getIdOrga() {
		return membre.getIdOrga();
	}

	public long getIdDepartement() {
		return membre.getIdDepartement().getIdDepartement();
	}

	public void setIdDepartement(long idDepartement) {
		membre.getIdDepartement().setIdDepartement(idDepartement);
	}
	
	public boolean getIsDefaultPassword() {
		return membre.getIsDefaultPassword();
	}

	public void setIsDefaultPassword(boolean isDefaultPassword) {
		membre.setIsDefaultPassword(isDefaultPassword);
	}
	
	public Date getDateCreation() {
		return membre.getDateCreation();
	}
	
	public String getDateCreationStr() {
		if(membre.getDateCreation()!=null)
			return formatDate.format(membre.getDateCreation());
		else
			return "";
	}	
	
	public void setDateCreation(Date dateCreation) {
		membre.setDateCreation(dateCreation);
	}
	
	public Date getDateModification() {
		return membre.getDateModification();
	}
	
	public String getDateModificationStr() {
		if(membre.getDateModification()!=null)
			return formatDate.format(membre.getDateModification());
		else
			return "";		
	}	

	public void setDateModification(Date dateModification) {
		membre.setDateModification(dateModification);
	}
	
	public Date getDateDerniereConnexion() {
		return membre.getDateDerniereConnexion();
	}
	
	public String getDateDerniereConnexionStr() {
		if(membre.getDateDerniereConnexion()!=null)
			return formatDate.format(membre.getDateDerniereConnexion());
		else 
			return "";
	}	

	public void setDateDerniereConnexion(Date dateDerniereConnexion) {
		membre.setDateDerniereConnexion(dateDerniereConnexion);
	}

	public List<Profil> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Profil> roleList) {
		this.roleList = roleList;
	}
	
	public List<Membre> getParrainList() {
		return parrainList;
	}

	public void setParrainList(List<Membre> parrainList) {
		this.parrainList = parrainList;
	}

	public Profil getIdProfil() {
		return membre.getIdProfil();
	}

	public void setIdProfil(Profil idProfil) {
		membre.setIdProfil(idProfil);
	}

	public Long getIdMembre() {
		return idMembre;
	}

	public void setIdMembre(Long idMembre) {
		this.idMembre = idMembre;
	}
	
	public Long getIdMembreParrain() {
		return idMembreParrain;
	}

	public void setIdMembreParrain(Long idMembreParrain) {
		this.idMembreParrain = idMembreParrain;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}
	
	public String getHasReducRendered() {
		return hasReducRendered;
	}

	public void setHasReducRendered(String hasReducRendered) {
		this.hasReducRendered = hasReducRendered;
	}
	
	public String getParrainRendered() {
		return parrainRendered;
	}

	public void setParrainRendered(String parrainRendered) {
		this.parrainRendered = parrainRendered;
	}

	public String getOldMembreEmail() {
		return oldMembreEmail;
	}

	public void setOldMembreEmail(String oldMembreEmail) {
		this.oldMembreEmail = oldMembreEmail;
	}
	
	public Long getIdOrgaSelected() {
		return idOrgaSelected;
	}

	public void setIdOrgaSelected(Long idOrgaSelected) {
		this.idOrgaSelected = idOrgaSelected;
	}
	

	public String getIsAddAction() {
		return isAddAction;
	}

	public void setIsAddAction(String isAddAction) {
		this.isAddAction = isAddAction;
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getOrgaDisabled() {
		return orgaDisabled;
	}

	public void setOrgaDisabled(String orgaDisabled) {
		this.orgaDisabled = orgaDisabled;
	}

	public OrganisationServiceAction getOrganisationService() {
		return organisationService;
	}

	public void setOrganisationService(OrganisationServiceAction organisationService) {
		this.organisationService = organisationService;
	}

	public AuthentificationServiceAction getAuthentificationService() {
		return authentificationService;
	}

	public void setAuthentificationService(AuthentificationServiceAction authentificationService) {
		this.authentificationService = authentificationService;
	}

	public MembreServiceAction getMembresService() {
		return membreService;
	}

	public void setMembresService(MembreServiceAction membreService) {
		this.membreService = membreService;
	}

	public MembreServiceAction getMembreService() {
		return membreService;
	}

	public void setMembreService(MembreServiceAction membreService) {
		this.membreService = membreService;
	}

	public ReferentielBean getReferentielBean() {
		return referentielBean;
	}

	public void setReferentielBean(ReferentielBean referentielBean) {
		this.referentielBean = referentielBean;
	}
	
	/*public MessageBean getMessageBean() {
		return messageBean;
	}

	public void setMessageBean(MessageBean messageBean) {
		this.messageBean = messageBean;
	}*/

	public List<Organisation> getOrganisationList() {
		return organisationList;
	}

	public void setOrganisationList(List<Organisation> organisationList) {
		this.organisationList = organisationList;
	}

	public List<Departement> getDepartementList() {
		return departementList;
	}

	public void setDepartementList(List<Departement> departementList) {
		this.departementList = departementList;
	}

	public DepartementServiceAction getDepartementService() {
		return departementService;
	}

	public void setDepartementService(DepartementServiceAction departementService) {
		this.departementService = departementService;
	}

	public FilleulsServiceAction getFilleulsService() {
		return filleulsService;
	}

	public void setFilleulsService(FilleulsServiceAction filleulsService) {
		this.filleulsService = filleulsService;
	}


	public String editMembre() {

		Membre membre = new Membre(getIdMembre());
		membre= membreService.getMembre(membre);
		if(membre.getIdDepartement()==null){
			membre.setIdDepartement(new Departement(0L));
		}
		setMembre(membre);
		this.departementList = departementService.getDepartementByOrgaList(new Organisation(getIdOrgaSelected()));
		onRoleChange();
		if(this.membre!=null && this.membre.getIdProfil().getIdProfil()==ReferentielBean.PROFIL_FILLEUL){
			Filleul filleul = filleulsService.getFilleul(getIdMembre());
			if(filleul!=null){
				setIdMembreParrain(filleul.getIdMembreParrain().getIdMembre());
			}
		}
		setIsAddAction("false");
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_membre");
		return "membre";
	}
	
	public String editMembreConnected() {

		UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();
		membre = new Membre(userSessionBean.getIdMembre());
		membre= membreService.getMembre(membre);
		if(membre.getIdDepartement()==null){
			membre.setIdDepartement(new Departement(0L));
		}
		//setMembre(membre);
		this.oldMembreEmail=membre.getMembreEmail();
		this.departementList = departementService.getDepartementByOrgaList(membre.getIdOrga());
		onRoleChange();
		if(this.membre!=null && this.membre.getIdProfil().getIdProfil()==ReferentielBean.PROFIL_FILLEUL){
			Filleul filleul = filleulsService.getFilleul(getIdMembre());
			if(filleul!=null){
				setIdMembreParrain(filleul.getIdMembreParrain().getIdMembre());
			}
		}
		setIsAddAction("false");
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_mon_compte");
		return "membre";
	}	
	
	public String addMembreView() {
		
		UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();
		this.membre = new Membre(0L);
		this.membre.setIdDepartement(new Departement(0L));
		this.membre.setIdProfil(new Profil());
		this.membre.setIdOrga(new Organisation(0L));
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_ajouter_membre");
		setParrainRendered("false");
		if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_MANAGER){
			this.membre.setIdOrga(new Organisation((userSessionBean.getIdOrga())));
			this.departementList = departementService.getDepartementByOrgaList(new Organisation((userSessionBean.getIdOrga())));
		}else{
			this.departementList = departementService.getDepartementByOrgaList(organisationList.get(0));
			
		}
		setIsAddAction("true");
		return "membre";
	}	
	
	public String insertOrUpdateMembre() {
		
		FacesMessage facesMessage = new FacesMessage();
		Calendar calendar = Calendar.getInstance();
		Date dateToday =  calendar.getTime();
		String msg;
		String ecran ="membre";
		
		if(membre.getIdDepartement().getIdDepartement()==0L){
			membre.getIdDepartement().setIdDepartement(null);
		}

		if(membre.getIdMembre()==null || membre.getIdMembre()==0L){
			if(authentificationService.emailExist(getMembreEmail())){
				msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_emailExist");
				facesMessage.setSummary(msg); 
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}else{
				
				UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();
				String password = referentielBean.getDefaultPassword();
				membre.setPassword(DigestUtils.sha512Hex(password));
				membre.setDateCreation(dateToday);
				if(getIdProfil() !=null && getIdProfil().getIdProfil()==ReferentielBean.PROFIL_FILLEUL){
					Membre membreParrain = membreService.getMembre(new Membre(getIdMembreParrain()));
					membreService.insertMembreFilleul(membre, membreParrain);
				}else{
					membreService.insertMembre(membre);
				}
				this.titre = ParfumUtils.getBundleApplication().getString("libelle_ajouter_membre");
				if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_MANAGER){
					setHasReducRendered("false");
					setOrgaDisabled("true");
				}else if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_ADMIN){
					setHasReducRendered("true");
					setOrgaDisabled("false");
				}
				try {
					logger.info("send mail membre with password : " + password);
					Mail.send(membre.getMembreEmail(), ParfumUtils.getBundleApplication().getString("message.membre.topic"),MessageFormat.format(ParfumUtils.getBundleApplication().getString("message.nouveau.membre.cree"),userSessionBean.getMembrePrenom(),userSessionBean.getMembreNom(),membre.getMembreEmail(),password));
				} catch (MessagingException e) {
					logger.error("ERROR send mail membre with password : ",e);
				}
				MembresBean membresBean =  (MembresBean)ParfumUtils.getSessionObject("membresBean");
				if(membresBean.getIdOrga().getIdOrga()==0L){
					membresBean.setMembresList(membreService.getMembreList());
				}else{
					membresBean.setMembresList(membreService.getMembreByOrgaList(membresBean.getIdOrga()));
				}
				msg = ParfumUtils.getBundleApplication().getString("message.ajouter.membre");
				facesMessage.setSummary(msg); 
				facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			    ecran="membres";
			}
		}else{
			if(getMembreEmail().equals(getOldMembreEmail())){
				membre.setDateModification(dateToday);
				if(getIdProfil() !=null && getIdProfil().getIdProfil()==ReferentielBean.PROFIL_FILLEUL){
					Membre membreParrain = membreService.getMembre(new Membre(getIdMembreParrain()));
					membreService.updateMembreFilleul(membre, membreParrain);
				}else{
					membreService.updateMembre(membre);
				}
				
				msg = ParfumUtils.getBundleApplication().getString("message.modif.membre");
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
					membre.setDateModification(dateToday);
					membreService.updateMembre(membre);
					msg = ParfumUtils.getBundleApplication().getString("message.modif.membre");
					facesMessage.setSummary(msg); 
					facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
				    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				}
			}
			this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_membre");
		}
		
		this.departementList = departementService.getDepartementByOrgaList(getIdOrga());
		return ecran;
	}	
	
	public void onOrganisationChange() {
        if(getIdOrga() !=null && !getIdOrga().equals("0")){
        	this.departementList = departementService.getDepartementByOrgaList(getIdOrga());
        }else{
        	this.departementList = new ArrayList<Departement>();
        }
        onRoleChange();
    }
	
	public void onRoleChange() {
		
        if(getIdProfil() !=null && getIdProfil().getIdProfil()!=null && getIdProfil().getIdProfil()==ReferentielBean.PROFIL_FILLEUL){
        	UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();
        	if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_MANAGER || userSessionBean.getIdProfil()==ReferentielBean.PROFIL_ADMIN){
	        	List<Profil> idProfilList = new ArrayList<Profil>();
	        	idProfilList.add(new Profil(ReferentielBean.PROFIL_MANAGER));
	        	idProfilList.add(new Profil(ReferentielBean.PROFIL_MEMBRE));
	        	this.parrainList = membreService.getParrainByOrgaList(getIdOrga(),idProfilList);
        	}else if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_MEMBRE){
        		this.parrainList = new ArrayList<Membre>();
        		Membre userConnected = new Membre(userSessionBean.getIdMembre());
        		userConnected.setMembreNom(userSessionBean.getMembreNom());
        		userConnected.setMembrePrenom(userSessionBean.getMembrePrenom());
        		parrainList.add(userConnected);
        	}
        	setParrainRendered("true");
        }else{
        	setParrainRendered("false");
        }
    }	
	

}
