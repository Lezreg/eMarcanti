package com.marcanti.ecommerce.service.actions;

import java.util.List;

import com.marcanti.ecommerce.model.CommandeIndividuelle;

public interface CommandeIndividuelleServiceAction {
	/**
	 * 
	 * @param idMembre
	 * @param idCdeGroupee
	 * @param isCurrentCmd
	 *            true pour récupérer la liste de commande en cours
	 * @return
	 */
	List<CommandeIndividuelle> getCmdEnCoursParMembre(Long idMembre, Long idCdeGroupee, boolean isCurrentCmd);

	/**
	 * 
	 * @param idCde
	 * @return
	 */
	CommandeIndividuelle getCommandeIndividuelleById(Long idCde);

}
