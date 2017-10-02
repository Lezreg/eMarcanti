package com.marcanti.ecommerce.service.actions;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcanti.ecommerce.dao.CommandeGroupeeDAO;
import com.marcanti.ecommerce.dao.CommandeGroupeeStatusDAO;
import com.marcanti.ecommerce.model.CommandeGroupee;
import com.marcanti.ecommerce.model.CommandeGroupeeStatus;

@Service("commandeGroupeeServiceAction")
public class CommandeGroupeeServiceActionImpl implements CommandeGroupeeServiceAction {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandeGroupeeServiceActionImpl.class);

	@Autowired
	private CommandeGroupeeDAO commandeGroupeeDAO;

	@Autowired
	private CommandeGroupeeStatusDAO commandeGroupeeStatusDAO;

	@Override
	public Long getIdDerniereCdeGoupee(Long idOrg) {
		return commandeGroupeeDAO.getIdDerniereCdeGoupee(idOrg);
	}

	@Override
	public List<CommandeGroupee> getCmdGroupeesByOrg(Long idOrg) {
		return commandeGroupeeDAO.getCmdGroupeesByOrg(idOrg);
	}

	public CommandeGroupeeDAO getCommandeGroupeeDAO() {
		return commandeGroupeeDAO;
	}

	public void setCommandeGroupeeDAO(CommandeGroupeeDAO commandeGroupeeDAO) {
		this.commandeGroupeeDAO = commandeGroupeeDAO;
	}

	@Override
	public void saveCmdGroupee(CommandeGroupee commandeGroupee) {
		if (commandeGroupee != null && commandeGroupee.getIdCdeGroupee() != null) {
			LOGGER.info("edit commande groupée: id/"+commandeGroupee.getIdCdeGroupee());
			commandeGroupeeDAO.edit(commandeGroupee);
		} else {
			LOGGER.info("create commande groupée");
			commandeGroupeeDAO.create(commandeGroupee);
		}
	}

	public CommandeGroupeeStatusDAO getCommandeGroupeeStatusDAO() {
		return commandeGroupeeStatusDAO;
	}

	public void setCommandeGroupeeStatusDAO(CommandeGroupeeStatusDAO commandeGroupeeStatusDAO) {
		this.commandeGroupeeStatusDAO = commandeGroupeeStatusDAO;
	}

	@Override
	public CommandeGroupeeStatus getCommandeGroupeeStatusByCode(String statusCode) {
		return commandeGroupeeStatusDAO.getCommandeGroupeeStatusByCode(statusCode);
	}

}
