package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.CommandeGroupeeStatus;

public interface CommandeGroupeeStatusDAO extends GenericDAO<CommandeGroupeeStatus> {

	CommandeGroupeeStatus getCommandeGroupeeStatusByCode(String statusCode);
	
	public List<CommandeGroupeeStatus> findAll();

}
