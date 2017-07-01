package com.marcanti.ecommerce.dao;

import com.marcanti.ecommerce.model.CommandeIndividuelleStatus;

public interface CommandeIndividuelleStatusDAO extends GenericDAO<CommandeIndividuelleStatus> {

	CommandeIndividuelleStatus getCommandeIndividuelleStatusByCode(String code);
}
