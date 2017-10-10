package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.Departement;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Profil;
import com.marcanti.ecommerce.service.actions.AuthentificationServiceAction;
import com.marcanti.ecommerce.service.actions.MembreServiceAction;
import com.marcanti.ecommerce.service.actions.OrganisationServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "membresBean")
@SessionScoped
public class MembresBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(MembresBean.class);

	private List<Membre> membresList;

	private List<Organisation> organisationList;
	
	private Membre membre;
	
	private String orgaDisabled="true";
	
	private Long idMembre;
	
	private String oldMembreEmail;
	
	private Long idOrgaSelected;	
	
	@ManagedProperty("#{membreService}")
	private MembreServiceAction membreService;
	
	@ManagedProperty("#{authentificationService}")
	private AuthentificationServiceAction authentificationService;	
	
	@ManagedProperty("#{organisationService}")
	private OrganisationServiceAction organisationService;
	
	@ManagedProperty("#{referentielBean}")
	private ReferentielBean referentielBean;

	public MembresBean() {
	}

	@PostConstruct
	public void init() {
		
		UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();
		Organisation idOrgaUserConnected = new Organisation(userSessionBean.getIdOrga());
		
		this.membre = new Membre(0L);
		this.membre.setIdOrga(idOrgaUserConnected);
		this.organisationList = organisationService.getOrganisationList();
		
		if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_MANAGER){
			this.membresList = membreService.getMembreByOrgaList(idOrgaUserConnected);
			setOrgaDisabled("true");
		}else if(userSessionBean.getIdProfil()==ReferentielBean.PROFIL_ADMIN){
			Organisation orgaAll = new Organisation(0L);
			orgaAll.setOrgaNom(ParfumUtils.getBundleApplication().getString("libelle_toutes"));
			this.organisationList.add(this.organisationList.size(),orgaAll);
			this.membresList = membreService.getMembreByOrgaList(this.organisationList.get(0));
			setOrgaDisabled("false");
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
	
	public Profil getIdProfil() {
		return membre.getIdProfil();
	}

	public void setIdProfil(Profil idProfil) {
		membre.setIdProfil(idProfil);
	}

/*	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}*/

	public Long getIdMembre() {
		return idMembre;
	}

	public void setIdMembre(Long idMembre) {
		this.idMembre = idMembre;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
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
	
	public List<Organisation> getOrganisationList() {
		return organisationList;
	}

	public void setOrganisationList(List<Organisation> organisationList) {
		this.organisationList = organisationList;
	}

	public void setMembresList(List<Membre> membresList) {
		this.membresList = membresList;
	}

	public List<Membre> getMembresList() {
		return membresList;
	}

	
	public String editMembre() {

		Membre membre = new Membre(getIdMembre());
		membre= membreService.getMembre(membre);
		//UserSessionBean userSession = ParfumUtils.getUserSessionBean();
		//membre.setIdOrga(new Organisation(userSession.getIdOrga()));
		//membre.setIdDepartement(new Departement(userSession.getIdDepartement()));
		if(membre.getIdDepartement()==null){
			membre.setIdDepartement(new Departement(0L));
		}
		//membre.setIdOrga(new Organisation(0L));
		setMembre(membre);
		//ParfumUtils.getUserSessionBean().setIdOrgaSelected(getIdOrgaSelected());
		return "membre";
	}
	
	/*public String addMembreView() {
		return "membre";
	}	*/
	
	public void onOrganisationChange(ValueChangeEvent e){
        
		getIdOrga().setIdOrga(Long.valueOf(e.getNewValue().toString()));
		if(getIdOrga().getIdOrga()==0L){
			this.membresList = membreService.getMembreList();
		}else{
			this.membresList = membreService.getMembreByOrgaList(getIdOrga());
		}
		//return "membres";
    }
	

}
