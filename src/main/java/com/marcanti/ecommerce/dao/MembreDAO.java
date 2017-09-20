package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Profil;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

public interface MembreDAO {
	
	public List<Membre> findAll();
	
	public List<Membre> getMembreByOrgaList(Organisation idOrga);
	
	public Membre getMembre(Membre idMembre);
	
	public void updateFilleul(Membre filleul);
	
	public void insertFilleul(Membre filleul, UserSessionBean parrain);
	
	public void updateMembre(Membre membre);
	
	public void insertMembre(Membre membre);	
	
	public List<Membre> getParrainByOrgaList(Organisation idOrga, List<Profil> idProfilList);
	
	public void insertMembreFilleul(Membre filleul, Membre membreParrain);
	
	public void updateMembreFilleul(Membre filleul, Membre membreParrain);

}
