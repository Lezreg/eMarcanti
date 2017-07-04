package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.CommandeGroupee;
import com.marcanti.ecommerce.model.CommandeIndividuelle;
import com.marcanti.ecommerce.model.Membre;

public interface CommandeIndividuelleDAO extends GenericDAO<CommandeIndividuelle> {
	/**
	 * 
	 * verifier si la commande individuelle existe
	 * 
	 * @param membre
	 * @param commandeGroupee
	 * @return true or false
	 */
	boolean verifyExistingCommandeIndividuelle(Membre membre, CommandeGroupee commandeGroupee);

	/**
	 * rechercher commande individuelle par commande groupee et utlisateur
	 * connecté
	 * 
	 * @param membre
	 * @param commandeGroupee
	 * @return CommandeIndividuelle
	 */
	CommandeIndividuelle getCommandeIndividuellByMembreAndCmdGroupe(Membre membre, CommandeGroupee commandeGroupee);

	/**
	 * 
	 * @param idMembre
	 * @param idCdeGroupee
	 * @return
	 */
	List<CommandeIndividuelle> getCommandeIndivListByMembreAndCmdGroupe(Long idMembre, Long idCdeGroupee);
}
