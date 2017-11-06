package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.CommandeGroupee;
import com.marcanti.ecommerce.model.VCdeGroupeeDetail;

public interface CommandeGroupeeDAO extends GenericDAO<CommandeGroupee> {

	Long getIdDerniereCdeGoupee(Long idOrg);

	List<CommandeGroupee> getCmdGroupeesByOrg(Long idOrg);

	Long getIdDerniereCdeGoupeeALivrer(Long idOrg);

	List<VCdeGroupeeDetail> getCmdGroupeesFilleuls(List<Long> idsMembre);

}
