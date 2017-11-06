/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author admin
 */

@ManagedBean(name="USER_SESSION")
@SessionScoped
public class UserSessionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(UserSessionBean.class);

    private Long idMembre;
    
    private Short idProfil;
    
	private Long idOrga;
    
    private Long idDepartement;
    
    private String membreNom;
    
    private String membrePrenom;
    
    private String membreEmail;
    
    private String orgaNom;
    
    private String departementNom;
    
    //MENU
    private String MENU_CATALOGUE="false";
    private String MENU_NOUVEAUTE="false";
    private String MENU_PROMOS="false";
    private String MENU_DECOUVRIR="false";
    private String MENU_COMMANDES="false";
    private String MENU_PARRAINAGE="false";
    private String MENU_COMMANDES_GRP="false";
    private String MENU_COMMANDES_GRP_RC="false";
    private String MENU_COMMANDES_GRP_RP="false";
    private String MENU_COMMANDES_GRP_REDUC="false";
    private String MENU_ADMIN="false";
    private String MENU_ADMIN_MBR="false";
    private String MENU_ADMIN_ORGA="false";
    private String MENU_ADMIN_DPT="false";
    private String MENU_ADMIN_MARQUE="false";
    private String MENU_ADMIN_CATEG="false";
    private String MENU_ADMIN_SS_CATEG="false";
    private String MENU_ADMIN_CAROUSEL="false";
    private String MENU_ADMIN_CATALOGUE="false";
    private String MENU_REPORTING="false";
    
    //private Long idOrgaSelected;

    public UserSessionBean() {
    }

    public UserSessionBean(Long idMembre) {
        this.idMembre = idMembre;
    }

	public UserSessionBean(Long idMembre, Short idProfil, Long idOrga, Long idDepartement, String membreNom,
			String membrePrenom, String membreEmail, String orgaNom, String departementNom) {
		super();
		this.idMembre = idMembre;
		this.idProfil = idProfil;
		this.idOrga = idOrga;
		this.idDepartement = idDepartement;
		this.membreNom = membreNom;
		this.membrePrenom = membrePrenom;
		this.membreEmail = membreEmail;
		this.orgaNom = orgaNom;
		this.departementNom = departementNom;
	}
	
	public Long getIdMembre() {
		return idMembre;
	}

	public void setIdMembre(Long idMembre) {
		this.idMembre = idMembre;
	}

	public Short getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(Short idProfil) {
		this.idProfil = idProfil;
	}

	public Long getIdOrga() {
		return idOrga;
	}

	public void setIdOrga(Long idOrga) {
		this.idOrga = idOrga;
	}

	public Long getIdDepartement() {
		return idDepartement;
	}

	public void setIdDepartement(Long idDepartement) {
		this.idDepartement = idDepartement;
	}

	public String getMembreNom() {
		return membreNom;
	}

	public void setMembreNom(String membreNom) {
		this.membreNom = membreNom;
	}

	public String getMembrePrenom() {
		return membrePrenom;
	}

	public void setMembrePrenom(String membrePrenom) {
		this.membrePrenom = membrePrenom;
	}

	public String getMembreEmail() {
		return membreEmail;
	}

	public void setMembreEmail(String membreEmail) {
		this.membreEmail = membreEmail;
	}
	
	public String getOrgaNom() {
		return orgaNom;
	}

	public void setOrgaNom(String orgaNom) {
		this.orgaNom = orgaNom;
	}

	public String getDepartementNom() {
		return departementNom;
	}

	public void setDepartementNom(String departementNom) {
		this.departementNom = departementNom;
	}
	
	/*public Long getIdOrgaSelected() {
		return idOrgaSelected;
	}

	public void setIdOrgaSelected(Long idOrgaSelected) {
		this.idOrgaSelected = idOrgaSelected;
	}*/
	
	public String getMENU_CATALOGUE() {
		return MENU_CATALOGUE;
	}

	public void setMENU_CATALOGUE(String mENU_CATALOGUE) {
		MENU_CATALOGUE = mENU_CATALOGUE;
	}

	public String getMENU_NOUVEAUTE() {
		return MENU_NOUVEAUTE;
	}

	public void setMENU_NOUVEAUTE(String mENU_NOUVEAUTE) {
		MENU_NOUVEAUTE = mENU_NOUVEAUTE;
	}

	public String getMENU_PROMOS() {
		return MENU_PROMOS;
	}

	public void setMENU_PROMOS(String mENU_PROMOS) {
		MENU_PROMOS = mENU_PROMOS;
	}

	public String getMENU_DECOUVRIR() {
		return MENU_DECOUVRIR;
	}

	public void setMENU_DECOUVRIR(String mENU_DECOUVRIR) {
		MENU_DECOUVRIR = mENU_DECOUVRIR;
	}

	public String getMENU_COMMANDES() {
		return MENU_COMMANDES;
	}

	public void setMENU_COMMANDES(String mENU_COMMANDES) {
		MENU_COMMANDES = mENU_COMMANDES;
	}

	public String getMENU_PARRAINAGE() {
		return MENU_PARRAINAGE;
	}

	public void setMENU_PARRAINAGE(String mENU_PARRAINAGE) {
		MENU_PARRAINAGE = mENU_PARRAINAGE;
	}

	public String getMENU_COMMANDES_GRP() {
		return MENU_COMMANDES_GRP;
	}

	public void setMENU_COMMANDES_GRP(String mENU_COMMANDES_GRP) {
		MENU_COMMANDES_GRP = mENU_COMMANDES_GRP;
	}

	public String getMENU_COMMANDES_GRP_RC() {
		return MENU_COMMANDES_GRP_RC;
	}

	public void setMENU_COMMANDES_GRP_RC(String mENU_COMMANDES_GRP_RC) {
		MENU_COMMANDES_GRP_RC = mENU_COMMANDES_GRP_RC;
	}

	public String getMENU_COMMANDES_GRP_RP() {
		return MENU_COMMANDES_GRP_RP;
	}

	public void setMENU_COMMANDES_GRP_RP(String mENU_COMMANDES_GRP_RP) {
		MENU_COMMANDES_GRP_RP = mENU_COMMANDES_GRP_RP;
	}

	public String getMENU_COMMANDES_GRP_REDUC() {
		return MENU_COMMANDES_GRP_REDUC;
	}

	public void setMENU_COMMANDES_GRP_REDUC(String mENU_COMMANDES_GRP_REDUC) {
		MENU_COMMANDES_GRP_REDUC = mENU_COMMANDES_GRP_REDUC;
	}

	public String getMENU_ADMIN() {
		return MENU_ADMIN;
	}

	public void setMENU_ADMIN(String mENU_ADMIN) {
		MENU_ADMIN = mENU_ADMIN;
	}

	public String getMENU_ADMIN_MBR() {
		return MENU_ADMIN_MBR;
	}

	public void setMENU_ADMIN_MBR(String mENU_ADMIN_MBR) {
		MENU_ADMIN_MBR = mENU_ADMIN_MBR;
	}

	public String getMENU_ADMIN_ORGA() {
		return MENU_ADMIN_ORGA;
	}

	public void setMENU_ADMIN_ORGA(String mENU_ADMIN_ORGA) {
		MENU_ADMIN_ORGA = mENU_ADMIN_ORGA;
	}

	public String getMENU_ADMIN_DPT() {
		return MENU_ADMIN_DPT;
	}

	public void setMENU_ADMIN_DPT(String mENU_ADMIN_DPT) {
		MENU_ADMIN_DPT = mENU_ADMIN_DPT;
	}

	public String getMENU_ADMIN_MARQUE() {
		return MENU_ADMIN_MARQUE;
	}

	public void setMENU_ADMIN_MARQUE(String mENU_ADMIN_MARQUE) {
		MENU_ADMIN_MARQUE = mENU_ADMIN_MARQUE;
	}

	public String getMENU_ADMIN_CATEG() {
		return MENU_ADMIN_CATEG;
	}

	public void setMENU_ADMIN_CATEG(String mENU_ADMIN_CATEG) {
		MENU_ADMIN_CATEG = mENU_ADMIN_CATEG;
	}

	public String getMENU_ADMIN_SS_CATEG() {
		return MENU_ADMIN_SS_CATEG;
	}

	public void setMENU_ADMIN_SS_CATEG(String mENU_ADMIN_SS_CATEG) {
		MENU_ADMIN_SS_CATEG = mENU_ADMIN_SS_CATEG;
	}

	public String getMENU_ADMIN_CAROUSEL() {
		return MENU_ADMIN_CAROUSEL;
	}

	public void setMENU_ADMIN_CAROUSEL(String mENU_ADMIN_CAROUSEL) {
		MENU_ADMIN_CAROUSEL = mENU_ADMIN_CAROUSEL;
	}
	
	public String getMENU_ADMIN_CATALOGUE() {
		return MENU_ADMIN_CATALOGUE;
	}

	public void setMENU_ADMIN_CATALOGUE(String mENU_ADMIN_CATALOGUE) {
		MENU_ADMIN_CATALOGUE = mENU_ADMIN_CATALOGUE;
	}

	public String getMENU_REPORTING() {
		return MENU_REPORTING;
	}

	public void setMENU_REPORTING(String mENU_REPORTING) {
		MENU_REPORTING = mENU_REPORTING;
	}
	
	
	public void setMenuRight() {
		if(this.idProfil==ReferentielBean.PROFIL_FILLEUL){
			MENU_CATALOGUE="true";
		    MENU_NOUVEAUTE="true";
		    MENU_PROMOS="true";
		    MENU_DECOUVRIR="true";
		    MENU_COMMANDES="true";
		    //MENU_PARRAINAGE="false";
		    //MENU_COMMANDES_GRP="false";
		    //MENU_COMMANDES_GRP_RC="false";
		    //MENU_COMMANDES_GRP_RP="false";
		    //MENU_COMMANDES_GRP_REDUC="false";
		    //MENU_ADMIN="false";
		    //MENU_ADMIN_MBR="false";
		    //MENU_ADMIN_ORGA="false";
		    //MENU_ADMIN_DPT="false";
		    //MENU_ADMIN_MARQUE="false";
		    //MENU_ADMIN_CATEG="false";
		    //MENU_ADMIN_SS_CATEG="false";
		    //MENU_ADMIN_CAROUSEL="false";		    
		    //MENU_REPORTING="false";
		}else if(this.idProfil==ReferentielBean.PROFIL_MEMBRE){
			MENU_CATALOGUE="true";
		    MENU_NOUVEAUTE="true";
		    MENU_PROMOS="true";
		    MENU_DECOUVRIR="true";
		    MENU_COMMANDES="true";
		    MENU_PARRAINAGE="true";
		    //MENU_COMMANDES_GRP="false";
		    //MENU_COMMANDES_GRP_RC="false";
		    //MENU_COMMANDES_GRP_RP="false";
		    //MENU_COMMANDES_GRP_REDUC="false";		    
		    //MENU_ADMIN="false";
		    //MENU_ADMIN_MBR="false";
		    //MENU_ADMIN_ORGA="false";
		    //MENU_ADMIN_DPT="false";
		    //MENU_ADMIN_MARQUE="false";
		    //MENU_ADMIN_CATEG="false";
		    //MENU_ADMIN_SS_CATEG="false";
		    //MENU_ADMIN_CAROUSEL="false";		    
		    //MENU_REPORTING="false";			
		}else if(this.idProfil==ReferentielBean.PROFIL_MANAGER){
			MENU_CATALOGUE="true";
		    MENU_NOUVEAUTE="true";
		    MENU_PROMOS="true";
		    MENU_DECOUVRIR="true";
		    MENU_COMMANDES="true";
		    MENU_PARRAINAGE="true";
		    MENU_COMMANDES_GRP="true";
		    MENU_COMMANDES_GRP_RC="true";
		    MENU_COMMANDES_GRP_RP="true";
		    //MENU_COMMANDES_GRP_REDUC="false";		    
		    MENU_ADMIN="true";
		    MENU_ADMIN_MBR="true";
		    //MENU_ADMIN_ORGA="false";
		    MENU_ADMIN_DPT="true";
		    //MENU_ADMIN_MARQUE="false";
		    //MENU_ADMIN_CATEG="false";
		    //MENU_ADMIN_SS_CATEG="false";
		    //MENU_ADMIN_CAROUSEL="false";		    
		    //MENU_REPORTING="false";			
		}else if(this.idProfil==ReferentielBean.PROFIL_ADMIN){
			this.MENU_CATALOGUE="true";
		    MENU_NOUVEAUTE="true";
		    MENU_PROMOS="true";
		    MENU_DECOUVRIR="true";
		    MENU_COMMANDES="true";
		    MENU_PARRAINAGE="true";
		    MENU_COMMANDES_GRP="true";
		    MENU_COMMANDES_GRP_RC="true";
		    MENU_COMMANDES_GRP_RP="true";
		    MENU_COMMANDES_GRP_REDUC="true";		    
		    MENU_ADMIN="true";
		    MENU_ADMIN_MBR="true";
		    MENU_ADMIN_ORGA="true";
		    MENU_ADMIN_DPT="true";
		    MENU_ADMIN_MARQUE="true";
		    MENU_ADMIN_CATEG="true";
		    MENU_ADMIN_SS_CATEG="true";
		    MENU_ADMIN_CAROUSEL="true";
		    MENU_ADMIN_CATALOGUE="true";
		    MENU_REPORTING="true";			
		}
	}	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void deconnection() throws Exception
	{

		logger.info("deconnexion : " + getMembreNom());
	    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.sendRedirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/pages/login.xhtml");
		
	    //return "/pages/login.xhtml";
	}

}
