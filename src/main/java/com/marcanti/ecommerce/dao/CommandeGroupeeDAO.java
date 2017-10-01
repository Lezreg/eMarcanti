package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.CommandeGroupee;

public interface CommandeGroupeeDAO extends GenericDAO<CommandeGroupee> {
	
	Long getIdDerniereCdeGoupee(Long idOrg);

	List<CommandeGroupee> getCmdGroupeesByOrg(Long idOrg);

}
