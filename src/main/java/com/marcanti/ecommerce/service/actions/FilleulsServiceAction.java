package com.marcanti.ecommerce.service.actions;

import java.util.List;

import com.marcanti.ecommerce.model.Filleul;
import com.marcanti.ecommerce.model.Membre;

public interface FilleulsServiceAction {
 
	public List<Membre> getFilleulsList(Membre parrain);
	
	public Filleul getFilleul(Long idFilleul);
	
     
}