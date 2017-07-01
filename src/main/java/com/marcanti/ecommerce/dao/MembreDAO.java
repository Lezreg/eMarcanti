package com.marcanti.ecommerce.dao;

import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

public interface MembreDAO extends GenericDAO<Membre> {
	
	public Membre getMembre(Membre idMembre);
	
	public void updateFilleul(Membre filleul);
	
	public void insertFilleul(Membre filleul, UserSessionBean parrain);

}
