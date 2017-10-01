package com.marcanti.ecommerce.service.actions;

import java.util.List;

import com.marcanti.ecommerce.model.Marque;

public interface MarqueServiceAction {
 
	public List<Marque> getMarqueList();
	
	public Marque getMarque(Marque marque);
	
	public void updateMarque(Marque marque);
	
	public void insertMarque(Marque marque);
	
     
}