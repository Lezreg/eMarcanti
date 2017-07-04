package com.marcanti.ecommerce.service.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.dao.CommandeGroupeeDAO;
import com.marcanti.ecommerce.dao.CommandeIndividuelleDAO;
import com.marcanti.ecommerce.model.CommandeIndividuelle;

@Transactional
@Service("commandeIndividuelleServiceAction")
public class CommandeIndividuelleServiceActionImpl implements CommandeIndividuelleServiceAction {

	@Autowired
	private CommandeIndividuelleDAO CommandeIndividuelleDAO;
	@Autowired
	private CommandeGroupeeDAO commandeGroupeeDAO;
	
	
	@Override
	public List<CommandeIndividuelle> getCmdEnCoursParMembre(Long idMembre, Long idCdeGroupee) {
		return CommandeIndividuelleDAO.getCommandeIndivListByMembreAndCmdGroupe(idMembre, idCdeGroupee);
	}


	public CommandeIndividuelleDAO getCommandeIndividuelleDAO() {
		return CommandeIndividuelleDAO;
	}


	public void setCommandeIndividuelleDAO(CommandeIndividuelleDAO commandeIndividuelleDAO) {
		CommandeIndividuelleDAO = commandeIndividuelleDAO;
	}

}
