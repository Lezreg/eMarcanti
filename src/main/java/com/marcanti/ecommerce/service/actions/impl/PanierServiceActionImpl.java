package com.marcanti.ecommerce.service.actions.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.constants.CommandeGroupeeStatusEnum;
import com.marcanti.ecommerce.constants.CommandeIndividuelleStatusEnum;
import com.marcanti.ecommerce.dao.CommandeGroupeeDAO;
import com.marcanti.ecommerce.dao.CommandeIndividuelleDAO;
import com.marcanti.ecommerce.dao.CommandeIndividuelleStatusDAO;
import com.marcanti.ecommerce.dao.MembreDAO;
import com.marcanti.ecommerce.dao.OrganisationDAO;
import com.marcanti.ecommerce.dao.PanierDAO;
import com.marcanti.ecommerce.dao.PanierProduitDAO;
import com.marcanti.ecommerce.dao.ProduitDAO;
import com.marcanti.ecommerce.exception.CommandeGroupeeNotFoundException;
import com.marcanti.ecommerce.exception.CommandeGroupeeValidatedExeception;
import com.marcanti.ecommerce.exception.ProductNotAvailableException;
import com.marcanti.ecommerce.model.CommandeGroupee;
import com.marcanti.ecommerce.model.CommandeIndividuelle;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Panier;
import com.marcanti.ecommerce.model.PanierProduit;
import com.marcanti.ecommerce.model.PanierProduitPK;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.service.actions.PanierActionService;
import com.marcanti.ecommerce.utils.BigDecimalUtils;
import com.marcanti.ecommerce.utils.ParametersChecker;
import com.marcanti.ecommerce.utils.ShortUtils;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

@Service("panierActionService")
public class PanierServiceActionImpl implements PanierActionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PanierServiceActionImpl.class);

	@Autowired
	private PanierDAO panierDao;
	@Autowired
	private PanierProduitDAO panierProduitDAO;
	@Autowired
	private CommandeIndividuelleDAO commandeIndividuelleDAO;
	@Autowired
	private CommandeGroupeeDAO commandeGroupeeDAO;
	@Autowired
	private MembreDAO membreDAO;
	@Autowired
	private ProduitDAO produitDAO;
	@Autowired
	private CommandeIndividuelleStatusDAO commandeIndividuelleStatusDAO;
	@Autowired
	private OrganisationDAO organisationDAO;

	@Override
	public void createPanier(Panier panier) {
		panierDao.create(panier);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Panier addProduct(Produit produit, UserSessionBean userSessionBean)
			throws CommandeGroupeeNotFoundException, CommandeGroupeeValidatedExeception {
		ParametersChecker.checkParameter("produit is null ", produit);
		ParametersChecker.checkParameter("userSessionBean is null ", userSessionBean);
		Panier panierEnCours = null;
		Long cmdgroupeeId = commandeGroupeeDAO.getIdDerniereCdeGoupee(userSessionBean.getIdOrga());
		if (cmdgroupeeId == null || cmdgroupeeId == 0L) {
			throw new CommandeGroupeeNotFoundException(
					"Commande groupee avec statut à livrer n'existe pas pour l organisation: "
							+ userSessionBean.getIdOrga());
		}
		// FIXME search directly last commande
		CommandeGroupee currentCmdGroupee = commandeGroupeeDAO.find(cmdgroupeeId);

		String statusCode = currentCmdGroupee.getIdStatus().getStatusCode();
		if (!CommandeGroupeeStatusEnum.CDE_GROUPEE_CONFIRMEE.getCode().equals(statusCode)
				&& !CommandeGroupeeStatusEnum.CDE_GROUPEE_NON_CONFIRMEE.getCode().equals(statusCode)) {
			throw new CommandeGroupeeValidatedExeception(
					"Commande groupee a deja été validé pour l organisation: " + userSessionBean.getIdOrga());
		}

		Membre membre = membreDAO.find(userSessionBean.getIdMembre());
		// verifier si panier existe deja (commande indiv)
		// FIXME verifer le statut de commande indiv
		CommandeIndividuelle commandeIndividuel = commandeIndividuelleDAO
				.getCommandeIndividuellByMembreAndCmdGroupe(membre, currentCmdGroupee);
		if (commandeIndividuel != null) {
			LOGGER.info("commandeIndividuel exist id: " + cmdgroupeeId);
			PanierProduit panierProduit = panierProduitDAO.getPanierProduitByPanierAndProduit(
					commandeIndividuel.getIdPanier().getIdPanier(), produit.getIdProduit());
			if (panierProduit != null) {
				LOGGER.info("panierProduit exist qte before : " + panierProduit.getQteSouhaitee());
				panierProduit.setQteSouhaitee(ShortUtils.incrementShort(panierProduit.getQteSouhaitee()));
				panierProduitDAO.edit(panierProduit);
				// update panier
				panierEnCours = panierProduit.getPanier();
				LOGGER.info("panierProduit exist qte after : " + panierProduit.getQteSouhaitee());

			} else {
				LOGGER.info("panierProduit not exist");
				// si le produit n'est pas encore ajouté , alors on cree un
				// objet panierproduit
				panierEnCours = commandeIndividuel.getIdPanier();
				panierProduit = getNewPanierProduit(commandeIndividuel.getIdPanier(), produit);
				panierProduitDAO.create(panierProduit);
			}
			if (commandeIndividuel.isConfirmed()) {
				// update panier (quantite et Montant)
				updatePanier(produit, panierEnCours);
			}
			// update commande individuelle
			updateCommandeIndividuelle(userSessionBean, commandeIndividuel, panierEnCours);
		} else {
			LOGGER.info("commandeIndividuel not exist id");
			// create panier
			panierEnCours = getNewPanier(produit, membre);
			createPanier(panierEnCours);
			// create panierproduit
			PanierProduit panierProduit = getNewPanierProduit(panierEnCours, produit);
			panierProduitDAO.create(panierProduit);
			// create commande indiv
			saveCommandeIndividuelle(panierEnCours, currentCmdGroupee, membre);

		}

		return panierEnCours;

	}

	private void saveCommandeIndividuelle(Panier panierEnCours, CommandeGroupee currentCmdGroupee, Membre membre) {
		CommandeIndividuelle commandeIndividuelle = getNewCommandeIndividuelle(membre, panierEnCours,
				currentCmdGroupee);
		commandeIndividuelleDAO.create(commandeIndividuelle);
		// update nom commande Individuelle
		commandeIndividuelle
				.setCdeIndivNom(commandeIndividuelle.getCdeIndivNom() + "_" + commandeIndividuelle.getIdCdeIndiv());
		commandeIndividuelleDAO.edit(commandeIndividuelle);
	}

	@Override
	public CommandeIndividuelle getLastCommandeIndividuelle(UserSessionBean userSessionBean) {
		ParametersChecker.checkParameter("userSessionBean is null ", userSessionBean);
		Long cmdgroupeeId = commandeGroupeeDAO.getIdDerniereCdeGoupee(userSessionBean.getIdOrga());
		CommandeIndividuelle commandeIndividuel = null;
		if (cmdgroupeeId != null) {
			CommandeGroupee currentCmdGroupee = commandeGroupeeDAO.find(cmdgroupeeId);
			Membre membre = membreDAO.find(userSessionBean.getIdMembre());
			if (membre != null && currentCmdGroupee != null) {
				commandeIndividuel = commandeIndividuelleDAO.getCommandeIndividuellByMembreAndCmdGroupe(membre,
						currentCmdGroupee);
			}
		}

		return commandeIndividuel;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<PanierProduit> recalculer(List<PanierProduit> panierProduitList, BigDecimal reduction,
			UserSessionBean userSessionBean, boolean isConfirmation) throws ProductNotAvailableException {

		if (panierProduitList == null || panierProduitList.isEmpty()) {
			LOGGER.info("panierProduitList is empty");
			return panierProduitList;
		}
		Panier panier = null;
		CommandeIndividuelle commandeIndividuel = null;
		BigDecimal totalPanier = BigDecimal.ZERO;
		Short panierNbreProduit = ShortUtils.DEFAULT;
		for (PanierProduit panierProduit : panierProduitList) {
			if (panierProduit != null) {
				if (panier == null) {
					panier = panierProduit.getPanier();
				}

				if (commandeIndividuel == null) {
					commandeIndividuel = commandeIndividuelleDAO.find(panier.getCommandeIndividuelle().getIdCdeIndiv());
				}
				// id qte zero then delete panierProduit
				short qtedemande = panierProduit.getQteSouhaitee();
				LOGGER.info("short qtedemande : " + qtedemande);
				if (qtedemande == 0) {
					LOGGER.info("Produit supprimé qte souhaité 0 :"
							+ panierProduit.getProduit().getIdMarque().getMarqueNom());
					removeProduit(commandeIndividuel, panierProduit);
					continue;
				}

				checkNbreMaxProduitParItem(panierProduit, qtedemande, userSessionBean);

				checkQteDemandeAndUpdateStock(commandeIndividuel, panierProduit, isConfirmation);

				// sous total panier produit
				BigDecimal notrePrix = panierProduit.getProduit().getNotrePrix();
				// total panier
				totalPanier = totalPanier.add(notrePrix.multiply(new BigDecimal(qtedemande)));
				panierNbreProduit = ShortUtils.sum2Short(panierNbreProduit, qtedemande);

				panierProduitDAO.edit(panierProduit);
			}
		}
		// update montant panier
		CommandeIndividuelle cmdIndiv = updatePanierAndCommande(userSessionBean, panier, commandeIndividuel,
				totalPanier, reduction, panierNbreProduit);

		// return updated list of panier produit
		return getProduitsByCmdIndiv(cmdIndiv.getIdCdeIndiv());
	}

	private void checkQteDemandeAndUpdateStock(CommandeIndividuelle commandeIndividuel, PanierProduit panierProduit,
			boolean isConfirmation) throws ProductNotAvailableException {

		PanierProduit panierBeforeEdit = null;

		Produit produit = panierProduit.getProduit();
		Short qtStock = produit.getQteEnStock();
		Short qtDisponible = ShortUtils.substract2Short(qtStock, panierProduit.getQteSouhaitee());

		// si la commande individuelle confirmée, on calcule le adding qte:

		// QtéDispo (id Produit) = produit.qteEnStock(id Produit) – qDemandee
		// (idProduit)

		// qDemandee (idProduit) =Quantité récupéré du formulaire – Quantité
		// initiale récupérée du formulaire pour chaque idProduit

		if (commandeIndividuel.isConfirmed()) {
			panierBeforeEdit = panierProduitDAO.find(panierProduit.getPanierProduitPK());
			Short qteDemande = ShortUtils.substract2Short(panierProduit.getQteSouhaitee(),
					panierBeforeEdit.getQteSouhaitee());
			qtDisponible = ShortUtils.substract2Short(produit.getQteEnStock(), qteDemande);

			qtStock = ShortUtils.substract2Short(qtStock, qteDemande);

			LOGGER.info("short qtedemande after calculation with adding qte : " + qteDemande);
		}

		if (qtDisponible < 0) {
			// 4.1.10.1 mise a jour panier par la qte disponible !
			Short qtePossible = produit.getQteEnStock();
			if (commandeIndividuel.isConfirmed()) {
				qtePossible = ShortUtils.sum2Short(produit.getQteEnStock(), panierBeforeEdit.getQteSouhaitee());
			}
			// panierProduit.setQteSouhaitee(qtePossible);
			throw new ProductNotAvailableException("Vérifier votre commande ! Quantité disponible pour le produit "
					+ produit.getProduitDescription() + " est : " + qtePossible);
		}

		if (commandeIndividuel.isConfirmed() || isConfirmation) {
			// update stock
			produit.setQteEnStock(qtStock);
			produitDAO.edit(produit);
		}

		// udate panier
		// panierProduitDAO.edit(panierProduit);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void confirmerCommandeIndiv(CommandeIndividuelle commandeIndividuelle, List<PanierProduit> panierProduitList,
			BigDecimal reduction, UserSessionBean userSessionBean) throws ProductNotAvailableException {
		// recalculer et verifier la disponibilité de produit en stock
		recalculer(panierProduitList, reduction, userSessionBean, true);
		// mettre à jour la quantité dispo
		// updateAllStock(panierProduitList);
		commandeIndividuelle.setIdStatus(commandeIndividuelleStatusDAO
				.getCommandeIndividuelleStatusByCode(CommandeIndividuelleStatusEnum.CDE_INDIVID_CONFIRMEE.getCode()));

		// cdeIndiv.dateModification
		commandeIndividuelle.setDateModification(new Date());
		// save
		commandeIndividuelleDAO.edit(commandeIndividuelle);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void updatePanierProduits(List<PanierProduit> panierProduitList) {
		// save panierProduit
		for (PanierProduit panierProduit : panierProduitList) {
			panierProduitDAO.edit(panierProduit);
		}

	}

	/**
	 * update montant panier et commande indiv
	 * 
	 * @param userSessionBean
	 * @param panier
	 * @param commandeIndividuel
	 * @param totalPanier
	 * @param panierNbreProduit
	 * @return
	 */

	private CommandeIndividuelle updatePanierAndCommande(UserSessionBean userSessionBean, Panier panier,
			CommandeIndividuelle commandeIndividuel, BigDecimal totalPanier, BigDecimal reduction,
			Short panierNbreProduit) {
		panier = updateMontantNbreProduit(totalPanier, panier, panierNbreProduit);

		commandeIndividuel.setReduction(reduction);
		commandeIndividuel.setCommentaire("MAJ PANIER");
		// update commande individuelle
		CommandeIndividuelle cmdIndiv = updateCommandeIndividuelle(userSessionBean, commandeIndividuel, panier);
		return cmdIndiv;
	}

	/**
	 * supprimer un produit de panier
	 * 
	 * @param commandeIndividuel
	 * @param panierProduit
	 */
	private void removeProduit(CommandeIndividuelle commandeIndividuel, PanierProduit panierProduit) {
		panierProduitDAO.removeById(panierProduit.getPanierProduitPK());

		if (commandeIndividuel != null && commandeIndividuel.getIdPanier() != null
				&& commandeIndividuel.getIdPanier().getPanierProduitCollection() != null) {
			commandeIndividuel.getIdPanier().getPanierProduitCollection().remove(panierProduit);
		}
		commandeIndividuelleDAO.edit(commandeIndividuel);
	}

	public void removeProduitFromPanier(CommandeIndividuelle commandeIndividuel, PanierProduit panierProduit) {

		removeProduit(commandeIndividuel, panierProduit);
		// FIXME
		// updatePanierAndCommande( panierProduit.getPanier(), commandeIndividuel,
		// totalPanier, panierNbreProduit)

	}

	private void checkNbreMaxProduitParItem(PanierProduit panierProduit, short qteSouhaitee,
			UserSessionBean userSessionBean) throws ProductNotAvailableException {

		Organisation organisation = organisationDAO.find(userSessionBean.getIdOrga());

		// 4.1.10.4 nombre max de produit a commander par item

		if (organisation.getNbreMaxProduitParItem() < qteSouhaitee) {
			throw new ProductNotAvailableException(
					"Vérifiez votre commande ! " + panierProduit.getProduit().getProduitDescription()
							+ " | quantité max par produit : " + organisation.getNbreMaxProduitParItem());
		}

	}

	private Panier updateMontantNbreProduit(BigDecimal totalPanier, Panier panier, Short panierNbreProduit) {
		LOGGER.info("------------------ panierNbreProduit :" + panierNbreProduit);
		if (panier != null) {
			panier.setPanierNbreProduit(panierNbreProduit);
			panier.setPanierMontant(totalPanier);
			panier = panierDao.edit(panier);
		}
		return panier;
	}

	/**
	 * 
	 * @param panier
	 * @param produit
	 * @return
	 */
	private PanierProduit getNewPanierProduit(Panier panier, Produit produit) {
		PanierProduitPK panierProduitPK = new PanierProduitPK(panier.getIdPanier(), produit.getIdProduit());
		PanierProduit panierProduit = new PanierProduit(panierProduitPK);
		panierProduit.setPanier(panier);
		panierProduit.setProduit(produit);
		panierProduit.setQteSouhaitee(ShortUtils.ONE);
		return panierProduit;
	}

	/**
	 * 
	 * @param produit
	 * @param utilisateur
	 * @return
	 */
	private Panier getNewPanier(Produit produit, Membre utilisateur) {
		Panier panier = new Panier();
		// TODO fix nom de panier
		Organisation organisation = utilisateur.getIdOrga();
		panier.setPanierNom(organisation.getOrgaAlias() + "_" + utilisateur.getMembreNom());
		panier.setPanierMontant(produit.getNotrePrix());
		panier.setPanierNbreProduit(ShortUtils.ONE);
		return panier;
	}

	/**
	 * 
	 * @param userSessionBean
	 * @param commandeIndividuel
	 * @param panierEnCours
	 * @return
	 */
	private CommandeIndividuelle updateCommandeIndividuelle(UserSessionBean userSessionBean,
			CommandeIndividuelle commandeIndividuel, Panier panierEnCours) {
		commandeIndividuel.setDateModification(new Date());
		// nom et prenom modifieur
		commandeIndividuel.setNomModifieur(userSessionBean.getMembreNom());
		commandeIndividuel.setPrenomModifieur(userSessionBean.getMembrePrenom());
		if (commandeIndividuel.getReduction() == null) {
			commandeIndividuel.setReduction(new BigDecimal(0));
		}
		BigDecimal total = panierEnCours.getPanierMontant().subtract(commandeIndividuel.getReduction());
		if (total.compareTo(BigDecimal.ZERO) < 0) {
			total = BigDecimal.ZERO;
		}
		commandeIndividuel.setTotalAPayer(total);
		// update
		commandeIndividuelleDAO.edit(commandeIndividuel);
		return commandeIndividuel;
	}

	/**
	 * 
	 * @param produit
	 * @param panierEnCours
	 */
	private void updatePanier(Produit produit, Panier panierEnCours) {
		// update panier en cours
		panierEnCours.setPanierMontant(BigDecimalUtils.sum(panierEnCours.getPanierMontant(), produit.getNotrePrix()));
		panierEnCours.setPanierNbreProduit(ShortUtils.incrementShort(panierEnCours.getPanierNbreProduit()));
		panierDao.edit(panierEnCours);

		updateQteProduit(produit);
	}

	private void updateQteProduit(Produit produit) {
		// update qte produit
		produit.setQteEnStock(ShortUtils.incrementShort(produit.getQteEnStock(), -1));
		produitDAO.edit(produit);
	}

	/**
	 * 
	 * @param utilisateur
	 * @param panier
	 * @param cmdgroupee
	 * @return
	 */
	private CommandeIndividuelle getNewCommandeIndividuelle(Membre utilisateur, Panier panier,
			CommandeGroupee cmdgroupee) {
		CommandeIndividuelle commandeIndividuelle = new CommandeIndividuelle();
		commandeIndividuelle.setDateCreation(new Date());
		commandeIndividuelle.setIsPaiementEffectue(false);
		commandeIndividuelle.setIdMembre(utilisateur);
		commandeIndividuelle.setIdPanier(panier);
		commandeIndividuelle.setIdCdeGroupee(cmdgroupee);

		commandeIndividuelle.setCdeIndivNom(
				cmdgroupee.getCdeGroupeeNom() + "_" + utilisateur.getMembrePrenom() + "_" + utilisateur.getMembreNom());

		commandeIndividuelle.setIdStatus(commandeIndividuelleStatusDAO.getCommandeIndividuelleStatusByCode(
				CommandeIndividuelleStatusEnum.CDE_INDIVID_NON_CONFIRMEE.getCode()));

		commandeIndividuelle.setTotalAPayer(panier.getPanierMontant());

		return commandeIndividuelle;
	}

	@Override
	public List<PanierProduit> getProduitsByCmdIndiv(Long idCmdIndiv) {
		return panierProduitDAO.findByCmdIndiv(idCmdIndiv);
	}

	@Override
	public boolean isExistingCommandeGroupee(Membre utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PanierProduit> getProduitsByPanier(Panier panier) {
		ParametersChecker.checkParameter("------------------panier is null ", panier);
		return panierProduitDAO.getPanierProduitByPanier(panier.getIdPanier());
	}

	public PanierDAO getPanierDao() {
		return panierDao;
	}

	public void setPanierDao(PanierDAO panierDao) {
		this.panierDao = panierDao;
	}

	public PanierProduitDAO getPanierProduitDao() {
		return panierProduitDAO;
	}

	public void setPanierProduitDao(PanierProduitDAO panierProduitDao) {
		this.panierProduitDAO = panierProduitDao;
	}

	public CommandeIndividuelleDAO getCommandeIndividuelleDao() {
		return commandeIndividuelleDAO;
	}

	public void setCommandeIndividuelleDao(CommandeIndividuelleDAO commandeIndividuelleDao) {
		this.commandeIndividuelleDAO = commandeIndividuelleDao;
	}

	public CommandeGroupeeDAO getCommandeGroupeeDAO() {
		return commandeGroupeeDAO;
	}

	public void setCommandeGroupeeDAO(CommandeGroupeeDAO commandeGroupeeDAO) {
		this.commandeGroupeeDAO = commandeGroupeeDAO;
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

	public PanierProduitDAO getPanierProduitDAO() {
		return panierProduitDAO;
	}

	public void setPanierProduitDAO(PanierProduitDAO panierProduitDAO) {
		this.panierProduitDAO = panierProduitDAO;
	}

	public MembreDAO getMembreDAO() {
		return membreDAO;
	}

	public void setMembreDAO(MembreDAO membreDAO) {
		this.membreDAO = membreDAO;
	}

	public OrganisationDAO getOrganisationDAO() {
		return organisationDAO;
	}

	public void setOrganisationDAO(OrganisationDAO organisationDAO) {
		this.organisationDAO = organisationDAO;
	}

}
