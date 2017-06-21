package com.marcanti.ecommerce.service.actions;

import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.model.Membre;

public interface MembreServiceAction {
 
	public Membre getMembre(Membre idMembre);
	
	@Transactional
	public void updateFilleul(Membre filleul);
	
	@Transactional
	public void insertFilleul(Membre filleul);
	
     
}