package com.marcanti.ecommerce.service.actions.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcanti.ecommerce.dao.CommandeGroupeeDAO;
import com.marcanti.ecommerce.dao.CommandeGroupeeStatusDAO;
import com.marcanti.ecommerce.dao.FilleulDAO;
import com.marcanti.ecommerce.dao.MembreDAO;
import com.marcanti.ecommerce.model.CommandeGroupee;
import com.marcanti.ecommerce.model.CommandeGroupeeStatus;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.VCdeGroupeeDetail;
import com.marcanti.ecommerce.model.VReduction;
import com.marcanti.ecommerce.service.actions.CommandeGroupeeServiceAction;

@Service("commandeGroupeeServiceAction")
public class CommandeGroupeeServiceActionImpl implements CommandeGroupeeServiceAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandeGroupeeServiceActionImpl.class);

	@Autowired
	private CommandeGroupeeDAO commandeGroupeeDAO;

	@Autowired
	private CommandeGroupeeStatusDAO commandeGroupeeStatusDAO;

	@Autowired
	private FilleulDAO filleulDAO;

	@Autowired
	private MembreDAO membreDAO;

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
			LOGGER.info("edit commande groupée: id/" + commandeGroupee.getIdCdeGroupee());
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

	@Override
	public List<VCdeGroupeeDetail> getCommandeGroupeesFilleulsByMembre(Long membreId) {
		List<Long> idMembres = getFilleulsById(membreId);
		List<VCdeGroupeeDetail> cmdGroupeesFilleuls = commandeGroupeeDAO.getCmdGroupeesFilleuls(idMembres);
		if (cmdGroupeesFilleuls == null) {
			cmdGroupeesFilleuls = Collections.emptyList();
		}
		return cmdGroupeesFilleuls;
	}

	private List<Long> getFilleulsById(Long membreId) {
		Membre Parrain = membreDAO.find(membreId);
		List<Membre> idsMembre = filleulDAO.getFilleulsList(Parrain);
		List<Long> idMembres = new ArrayList<>();
		for (Membre membre : idsMembre) {
			idMembres.add(membre.getIdMembre());
		}
		return idMembres;
	}

	@Override
	public List<VCdeGroupeeDetail> getCmdGroupeesFilleulsByStatus(Long membreId, String status) {
		List<Long> idMembres = getFilleulsById(membreId);
		List<VCdeGroupeeDetail> cmdGroupeesFilleuls = commandeGroupeeDAO.getCmdGroupeesFilleulsByStatus(idMembres,
				status);
		if (cmdGroupeesFilleuls == null) {
			cmdGroupeesFilleuls = Collections.emptyList();
		}
		return cmdGroupeesFilleuls;
	}

	@Override
	public List<CommandeGroupee> getCmdGroupeesByOrganisation(Long idOrg, boolean isEnCours) {
		return commandeGroupeeDAO.getCmdGroupeesByOrganisation(idOrg, isEnCours);
	}

	@Override
	public List<VReduction> getAllReductions() {
		return commandeGroupeeDAO.getAllReductions();
	}

	public MembreDAO getMembreDAO() {
		return membreDAO;
	}

	public void setMembreDAO(MembreDAO membreDAO) {
		this.membreDAO = membreDAO;
	}

}
