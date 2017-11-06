package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.dao.CommandeGroupeeDAO;
import com.marcanti.ecommerce.dao.CommandeIndividuelleDAO;
import com.marcanti.ecommerce.model.CommandeIndividuelle;
import com.marcanti.ecommerce.service.actions.CommandeIndividuelleServiceAction;

@Transactional
@Service("commandeIndividuelleServiceAction")
public class CommandeIndividuelleServiceActionImpl implements CommandeIndividuelleServiceAction {

	@Autowired
	private CommandeIndividuelleDAO CommandeIndividuelleDAO;
	@Autowired
	private CommandeGroupeeDAO commandeGroupeeDAO;
	
	
	@Override
	public List<CommandeIndividuelle> getCmdEnCoursParMembre(Long idMembre, Long idCdeGroupee, boolean isCurrentCmd) {
		if (isCurrentCmd) {
			return CommandeIndividuelleDAO.getCommandeIndivListByMembreAndCmdGroupe(idMembre, idCdeGroupee);
		}
		return CommandeIndividuelleDAO.getCommandeIndivLivreListByMembre(idMembre);
	}

	@Override
	public CommandeIndividuelle getCommandeIndividuelleById(Long idCde) {
		return CommandeIndividuelleDAO.find(idCde);
	}


	public CommandeIndividuelleDAO getCommandeIndividuelleDAO() {
		return CommandeIndividuelleDAO;
	}


	public void setCommandeIndividuelleDAO(CommandeIndividuelleDAO commandeIndividuelleDAO) {
		CommandeIndividuelleDAO = commandeIndividuelleDAO;
	}
}
