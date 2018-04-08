package com.marcanti.ecommerce.service.actions;

import java.util.List;

import com.marcanti.ecommerce.model.CommandeGroupee;
import com.marcanti.ecommerce.model.CommandeGroupeeStatus;
import com.marcanti.ecommerce.model.VCdeGroupeeDetail;
import com.marcanti.ecommerce.model.VReduction;

public interface CommandeGroupeeServiceAction {

	Long getIdDerniereCdeGoupee(Long idOrg);

	List<CommandeGroupee> getCmdGroupeesByOrg(Long idOrg);

	void saveCmdGroupee(CommandeGroupee commandeGroupee);

	CommandeGroupeeStatus getCommandeGroupeeStatusByCode(String statusCode);

	List<VCdeGroupeeDetail> getCommandeGroupeesFilleulsByMembre(Long membreId);

	List<CommandeGroupee> getCmdGroupeesByOrganisation(Long idOrg, boolean isEnCours);

	List<VReduction> getAllReductions();

	List<VCdeGroupeeDetail> getCmdGroupeesFilleulsByStatus(Long membreId, String status);

	List<CommandeGroupeeStatus> getAllCommandeGroupeeStatus();

	List<CommandeGroupee> getCmdGroupeesPaiementByOrganisation(Long idOrg, boolean isPaiementEffectue);
}
