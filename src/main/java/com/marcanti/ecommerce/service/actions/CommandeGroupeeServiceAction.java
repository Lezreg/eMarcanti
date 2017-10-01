package com.marcanti.ecommerce.service.actions;

import java.util.List;

import com.marcanti.ecommerce.model.CommandeGroupee;
import com.marcanti.ecommerce.model.CommandeGroupeeStatus;
import com.marcanti.ecommerce.model.CommandeIndividuelleStatus;

public interface CommandeGroupeeServiceAction {
	
	Long getIdDerniereCdeGoupee(Long idOrg);
	
	List<CommandeGroupee> getCmdGroupeesByOrg(Long idOrg);
	
	void saveCmdGroupee(CommandeGroupee commandeGroupee);

	
	CommandeGroupeeStatus getCommandeGroupeeStatusByCode(String statusCode);
}
