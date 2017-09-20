package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.Marque;

public interface MarqueDAO extends GenericDAO<Marque> {
	
	public List<Marque> getMarqueList();
	
	public Marque getMarque(Marque marque);
	
	public void insertMarque(Marque marque);
	
	public void updateMarque(Marque marque);

}
