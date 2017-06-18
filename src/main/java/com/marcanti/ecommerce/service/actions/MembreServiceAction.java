package com.marcanti.ecommerce.service.actions;

import com.marcanti.ecommerce.model.Membre;

public interface MembreServiceAction {
 
	public Membre getMembre(Membre idMembre);
	
	public void updateFilleul(Membre filleul);
	
	public void insertFilleul(Membre filleul);
	
     
}