package com.marcanti.ecommerce.service.actions;

import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.model.Membre;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Profil;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

public interface MembreServiceAction {
 
	public Membre getMembre(Membre idMembre);

	public void updateFilleulMembre(Membre filleul);
	
	@Transactional
	public void insertFilleulMembre(Membre filleul, UserSessionBean parrain);
	
	public List<Membre> getMembreList();
	
	public List<Membre> getMembreByOrgaList(Organisation idOrga);
	
	public void updateMembre(Membre membre);
	
	public void insertMembre(Membre membre);
	
	public List<Membre> getParrainByOrgaList(Organisation idOrga, List<Profil> idProfilList);
	
	public void insertMembreFilleul(Membre filleul, Membre membreParrain);
	
	public void updateMembreFilleul(Membre filleul, Membre membreParrain);
	
     
}