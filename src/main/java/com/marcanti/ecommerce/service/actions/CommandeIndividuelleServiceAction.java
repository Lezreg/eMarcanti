package com.marcanti.ecommerce.service.actions;

import java.util.List;

import com.marcanti.ecommerce.model.CommandeIndividuelle;

public interface CommandeIndividuelleServiceAction {

	List<CommandeIndividuelle> getCmdEnCoursParMembre(Long idMembre, Long idCdeGroupee);

	CommandeIndividuelle getCommandeIndividuelleById(Long idCde);

}
