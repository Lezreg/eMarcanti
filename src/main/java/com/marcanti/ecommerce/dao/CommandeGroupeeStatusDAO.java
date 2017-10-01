package com.marcanti.ecommerce.dao;

import com.marcanti.ecommerce.model.CommandeGroupeeStatus;
import com.marcanti.ecommerce.model.CommandeIndividuelleStatus;

public interface CommandeGroupeeStatusDAO extends GenericDAO<CommandeGroupeeStatus> {

	CommandeGroupeeStatus getCommandeGroupeeStatusByCode(String statusCode);

}
