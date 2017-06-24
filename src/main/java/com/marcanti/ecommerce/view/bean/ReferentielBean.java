package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Profil;
import com.marcanti.ecommerce.service.actions.ReferentielServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "referentielBean")
@ApplicationScoped
public class ReferentielBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(ReferentielBean.class);


	private List<Organisation> organisationList;
	
	private List<Profil> profilList;
	
	private String defaultPassword;
	
	public Map<String,Object> radioButtonOuiNon;
	{
		radioButtonOuiNon = new LinkedHashMap<String,Object>();
		radioButtonOuiNon.put(ParfumUtils.getBundleLibelle().getString("libelle_oui"), "true"); //label, value
		radioButtonOuiNon.put(ParfumUtils.getBundleLibelle().getString("libelle_non"), "false");
	}
	

	@ManagedProperty("#{referentielService}")
	private ReferentielServiceAction referentielService;
	
	public List<Profil> getProfilList() {
		return profilList;
	}

	public void setProfilList(List<Profil> profilList) {
		this.profilList = profilList;
	}
	
	public List<Organisation> getOrganisationList() {
		return organisationList;
	}

	public void setOrganisationList(List<Organisation> organisationList) {
		this.organisationList = organisationList;
	}

	public ReferentielServiceAction getReferentielService() {
		return referentielService;
	}

	public void setReferentielService(ReferentielServiceAction referentielService) {
		this.referentielService = referentielService;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}
	

	public ReferentielBean() {
	}

	@PostConstruct
	public void init() {
		this.organisationList = referentielService.getOrganisationList();
		this.profilList = referentielService.getProfilList();
		this.defaultPassword = referentielService.getDefaultPassword();
	}
	
}
