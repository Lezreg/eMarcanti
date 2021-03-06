package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Panier;
import com.marcanti.ecommerce.model.Profil;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

public interface MembreDAO extends GenericDAO<Membre>{
	
	public List<Membre> findAll();
	
	public List<Membre> getMembreByOrgaList(Organisation idOrga);
	
	public Membre getMembre(Membre idMembre);
	
	public void updateFilleulMembre(Membre filleul);
	
	public void insertFilleulMembre(Membre filleul, UserSessionBean parrain);
	
	public void updateMembre(Membre membre);
	
	public void insertMembre(Membre membre);	
	
	public List<Membre> getParrainByOrgaList(Organisation idOrga, List<Profil> idProfilList);
	
	public void insertMembreFilleul(Membre filleul, Membre membreParrain);
	
	public void insertFilleul(Membre filleul, Membre membreParrain);
	
	public void updateMembreFilleul(Membre filleul, Membre membreParrain);
	
	public void updateFilleul(Membre filleul, Membre membreParrain);

}
