package com.marcanti.ecommerce.service.actions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.constants.CommandeIndividuelleStatusEnum;
import com.marcanti.ecommerce.dao.CommandeGroupeeDAO;
import com.marcanti.ecommerce.dao.CommandeGroupeeStatusDAO;
import com.marcanti.ecommerce.dao.CommandeIndividuelleDAO;
import com.marcanti.ecommerce.dao.CommandeIndividuelleStatusDAO;
import com.marcanti.ecommerce.dao.FilleulDAO;
import com.marcanti.ecommerce.dao.MembreDAO;
import com.marcanti.ecommerce.dao.PanierProduitDAO;
import com.marcanti.ecommerce.dao.ProduitDAO;
import com.marcanti.ecommerce.model.CommandeGroupee;
import com.marcanti.ecommerce.model.CommandeGroupeeStatus;
import com.marcanti.ecommerce.model.CommandeIndividuelle;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.PanierProduit;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.model.VCdeGroupeeDetail;
import com.marcanti.ecommerce.model.VReduction;
import com.marcanti.ecommerce.service.actions.CommandeGroupeeServiceAction;
import com.marcanti.ecommerce.utils.ShortUtils;

@Service("commandeGroupeeServiceAction")
@Transactional
public class CommandeGroupeeServiceActionImpl implements CommandeGroupeeServiceAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandeGroupeeServiceActionImpl.class);

	@Autowired
	private CommandeGroupeeDAO commandeGroupeeDAO;

	@Autowired
	private CommandeGroupeeStatusDAO commandeGroupeeStatusDAO;

	@Autowired
	private CommandeIndividuelleDAO commandeIndividuelleDAO;

	@Autowired
	private CommandeIndividuelleStatusDAO commandeIndividuelleStatusDAO;

	@Autowired
	private FilleulDAO filleulDAO;

	@Autowired
	private MembreDAO membreDAO;

	@Autowired
	private ProduitDAO produitDAO;

	@Autowired
	private PanierProduitDAO panierProduitDAO;

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

	@Override
	public List<CommandeGroupeeStatus> getAllCommandeGroupeeStatus() {
		return commandeGroupeeStatusDAO.findAll();
	}

	@Override
	public List<CommandeGroupee> getCmdGroupeesPaiementByOrganisation(Long idOrg, boolean isPaiementEffectue) {
		return commandeGroupeeDAO.getCmdGroupeesPaiementByOrganisation(idOrg, isPaiementEffectue);
	}

	@Override
	public void annulerCmdGroupee(CommandeGroupee commandeGroupee) {
		// On met à jour l’état de la commande groupée : cdeGroupee.idStatus = xx
		// (récupéré par code = ‘CDE_GROUPEE_ANNULEE’

		List<CommandeIndividuelle> commandeIndivList = commandeIndividuelleDAO
				.getCommandeIndivListByCmdGroupe(commandeGroupee.getIdCdeGroupee());

		for (CommandeIndividuelle commandeIndividuelle : commandeIndivList) {
			// On met à jour l’état des commandes individuelles associées à la commande
			// groupée :CDE_INDIVID_ANNULEE’

			commandeIndividuelle.setIdStatus(commandeIndividuelleStatusDAO
					.getCommandeIndividuelleStatusByCode(CommandeIndividuelleStatusEnum.CDE_INDIVID_ANNULEE.getCode()));
			commandeIndividuelle.setTotalAPayer(BigDecimal.ZERO);

			if (commandeIndividuelle.getIdPanier() != null
					&& commandeIndividuelle.getIdPanier().getPanierProduitCollection() != null) {
				for (PanierProduit panierProduit : commandeIndividuelle.getIdPanier().getPanierProduitCollection()) {
					// On met à jour le panier associé à chaque commande individuelle :
					// panier.NbreProduit à 0, panier.panierMontant à 0
					Produit produit = panierProduit.getProduit();
					// met à jour produit.qteEnStock = produit.qteEnStock +
					// panierProduit.qteSouhaitee
					produit.setQteEnStock(
							ShortUtils.sum2Short(panierProduit.getQteSouhaitee(), produit.getQteEnStock()));
					panierProduit.setQteSouhaitee(ShortUtils.toShort(0));
					panierProduit.setSousTotal(BigDecimal.ZERO);
					produitDAO.edit(produit);
					panierProduitDAO.edit(panierProduit);

				}
			}
			commandeIndividuelleDAO.edit(commandeIndividuelle);
		}
		commandeGroupee.setCommentaire("ANNULEE");
		commandeGroupee.setIdStatus(commandeGroupeeStatusDAO.getCommandeGroupeeStatusByCode(
				com.marcanti.ecommerce.constants.CommandeGroupeeStatus.CDE_GROUPEE_ANNULEE.getCode()));
		commandeGroupee.setIsEnCours(Boolean.FALSE);

		commandeGroupeeDAO.edit(commandeGroupee);
	}

	public CommandeIndividuelleDAO getCommandeIndividuelleDAO() {
		return commandeIndividuelleDAO;
	}

	public void setCommandeIndividuelleDAO(CommandeIndividuelleDAO commandeIndividuelleDAO) {
		this.commandeIndividuelleDAO = commandeIndividuelleDAO;
	}

	public CommandeIndividuelleStatusDAO getCommandeIndividuelleStatusDAO() {
		return commandeIndividuelleStatusDAO;
	}

	public void setCommandeIndividuelleStatusDAO(CommandeIndividuelleStatusDAO commandeIndividuelleStatusDAO) {
		this.commandeIndividuelleStatusDAO = commandeIndividuelleStatusDAO;
	}

	public ProduitDAO getProduitDAO() {
		return produitDAO;
	}

	public void setProduitDAO(ProduitDAO produitDAO) {
		this.produitDAO = produitDAO;
	}

	public PanierProduitDAO getPanierProduitDAO() {
		return panierProduitDAO;
	}

	public void setPanierProduitDAO(PanierProduitDAO panierProduitDAO) {
		this.panierProduitDAO = panierProduitDAO;
	}

}
