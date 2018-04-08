package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.CommandeGroupee;
import com.marcanti.ecommerce.model.VCdeGroupeeDetail;
import com.marcanti.ecommerce.model.VReduction;

public interface CommandeGroupeeDAO extends GenericDAO<CommandeGroupee> {

	Long getIdDerniereCdeGoupee(Long idOrg);

	List<CommandeGroupee> getCmdGroupeesByOrg(Long idOrg);

	Long getIdDerniereCdeGoupeeALivrer(Long idOrg);

	List<VCdeGroupeeDetail> getCmdGroupeesFilleuls(List<Long> idsMembre);

	List<CommandeGroupee> getCmdGroupeesByOrganisation(Long idOrg, boolean isEncours);

	List<VReduction> getAllReductions();

	List<VCdeGroupeeDetail> getCmdGroupeesFilleulsByStatus(List<Long> idsMembre, String status);

	List<CommandeGroupee> getCmdGroupeesPaiementByOrganisation(Long idOrg, boolean isPaiementEffectue);

}
