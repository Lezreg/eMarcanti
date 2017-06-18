package com.marcanti.ecommerce.dao;

import com.marcanti.ecommerce.model.Membre;

public interface MembreDAO extends GenericDAO<Membre> {
	
	public Membre getMembre(Membre idMembre);
	
	public void updateFilleul(Membre filleul);
	
	public void insertFilleul(Membre filleul);

}
