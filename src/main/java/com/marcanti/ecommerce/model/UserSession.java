/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;

/**
 *
 * @author admin
 */

public class UserSession implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idMembre;
    
    private Short idProfil;
    
	private Long idOrga;
    
    private Long idDepartement;
    
    private String membreNom;
    
    private String membrePrenom;
    
    private String membreEmail;
    
    private String orgaNom;
    
    private String departementNom;

    public UserSession() {
    }

    public UserSession(Long idMembre) {
        this.idMembre = idMembre;
    }

	public UserSession(Long idMembre, Short idProfil, Long idOrga, Long idDepartement, String membreNom,
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
