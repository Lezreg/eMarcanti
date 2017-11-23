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

import com.marcanti.ecommerce.constants.CommandeGroupeeStatus;
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
@Transactional
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
		if (!CommandeGroupeeStatus.CDE_GROUPEE_CONFIRMEE.getCode().equals(statusCode)
				&& !CommandeGroupeeStatus.CDE_GROUPEE_NON_CONFIRMEE.getCode().equals(statusCode)) {
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
				updatePanier(produit, panierEnCours);
				LOGGER.info("panierProduit exist qte after : " + panierProduit.getQteSouhaitee());

			} else {
				LOGGER.info("panierProduit not exist");
				// si le produit n'est pas encore ajouté , alors on cree un
				// objet panierproduit
				panierEnCours = commandeIndividuel.getIdPanier();
				panierProduit = getNewPanierProduit(commandeIndividuel.getIdPanier(), produit);
				panierProduitDAO.create(panierProduit);
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
			CommandeIndividuelle commandeIndividuelle = getNewCommandeIndividuelle(membre, panierEnCours,
					currentCmdGroupee);
			commandeIndividuelleDAO.create(commandeIndividuelle);
		}

		return panierEnCours;

	}

	@Transactional
	@Override
	public List<PanierProduit> recalculer(List<PanierProduit> panierProduitList, UserSessionBean userSessionBean)
			throws ProductNotAvailableException {
		Panier panier = null;
		BigDecimal totalPanier = BigDecimal.ZERO;
		Short panierNbreProduit = ShortUtils.DEFAULT;
		for (PanierProduit panierProduit : panierProduitList) {
			if (panierProduit != null) {
				if (panier == null) {
					panier = panierProduit.getPanier();
				}
				// id qte zero then delete panierProduit
				short qteSouhaitee = panierProduit.getQteSouhaitee();
				if (qteSouhaitee == 0) {
					LOGGER.info("Produit supprimé qte souhaité 0 :"
							+ panierProduit.getProduit().getIdMarque().getMarqueNom());
					panierProduitDAO.removeById(panierProduit.getPanierProduitPK());
					continue;
				}
				checkPanierProduit(panierProduit, qteSouhaitee, userSessionBean);
				panierProduitDAO.edit(panierProduit);
				// sous total panier produit
				BigDecimal notrePrix = panierProduit.getProduit().getNotrePrix();
				short qteSouhaite = panierProduit.getQteSouhaitee();
				// total panier
				totalPanier = totalPanier.add(notrePrix.multiply(new BigDecimal(qteSouhaite)));
				panierNbreProduit = ShortUtils.sum2Short(panierNbreProduit, qteSouhaitee);
			}
		}
		// update montant panier
		panier = updateMontantNbreProduit(totalPanier, panier, panierNbreProduit);
		CommandeIndividuelle commandeIndividuel = commandeIndividuelleDAO
				.find(panier.getCommandeIndividuelle().getIdCdeIndiv());
		// TODO commandeIndividuelle.setReduction(reduction);
		// update commande individuelle
		CommandeIndividuelle cmdIndiv = updateCommandeIndividuelle(userSessionBean, commandeIndividuel, panier);
		// return updated list of panier produit
		return getProduitsByCmdIndiv(cmdIndiv.getIdCdeIndiv());
	}

	private void checkPanierProduit(PanierProduit panierProduit, short qteSouhaitee, UserSessionBean userSessionBean)
			throws ProductNotAvailableException {

		Organisation organisation = organisationDAO.find(userSessionBean.getIdOrga());

		// 4.1.10.4 nombre max de produit a commander par item

		if (organisation.getNbreMaxProduitParItem() < qteSouhaitee) {
			throw new ProductNotAvailableException(
					"Vérifiez votre commande ! " + panierProduit.getProduit().getProduitDescription()
							+ " quantité max par produit : " + organisation.getNbreMaxProduitParItem());
		}

		if (panierProduit.getProduit().getQteEnStock() - qteSouhaitee < 0) {
			throw new ProductNotAvailableException("Vérifiez votre commande : Quantité disponible pour le produit "
					+ panierProduit.getProduit().getProduitDescription() + " est : "
					+ panierProduit.getProduit().getQteEnStock());
		}
	}

	private Panier updateMontantNbreProduit(BigDecimal totalPanier, Panier panier, Short panierNbreProduit) {
		panier.setPanierMontant(totalPanier);
		LOGGER.info("------------------ panierNbreProduit :" + panierNbreProduit);
		panier.setPanierNbreProduit(panierNbreProduit);
		// TODO Total à payer montantTotal - reduction
		panier = panierDao.edit(panier);
		return panier;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void confirmerCommandeIndiv(CommandeIndividuelle commandeIndividuelle, List<PanierProduit> panierProduitList,
			UserSessionBean userSessionBean) throws ProductNotAvailableException {
		// recalculer et verifier la disponibilité de produit en stock
		recalculer(panierProduitList, userSessionBean);
		// mettre à jour la quantité dispo
		for (PanierProduit panierProduit : panierProduitList) {
			Produit produit = panierProduit.getProduit();
			Short qteDispo = ShortUtils.substract2Short(produit.getQteEnStock(), panierProduit.getQteSouhaitee());
			if (qteDispo < 0) {
				throw new ProductNotAvailableException("Vérifier votre commande : Quantité disponible pour le produit "
						+ produit.getProduitDescription() + " est : " + produit.getQteEnStock());
			}
			// update stock
			produit.setQteEnStock(qteDispo);
			produitDAO.edit(produit);
		}
		// mettre a jour statut
		commandeIndividuelle.setIdStatus(commandeIndividuelleStatusDAO
				.getCommandeIndividuelleStatusByCode(CommandeIndividuelleStatusEnum.CDE_INDIVID_CONFIRMEE.getCode()));
		// save
		commandeIndividuelleDAO.edit(commandeIndividuelle);
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
		commandeIndividuel.setTotalAPayer(panierEnCours.getPanierMontant());
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
		panierEnCours.setPanierMontant(BigDecimalUtils.sum(panierEnCours.getPanierMontant(), produit.getNotrePrix()));
		panierEnCours.setPanierNbreProduit(ShortUtils.incrementShort(panierEnCours.getPanierNbreProduit()));
		panierDao.edit(panierEnCours);
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
				utilisateur.getIdOrga().getOrgaNom() + "_" + utilisateur.getMembreNom() + "_" + new Date().toString());
		commandeIndividuelle.setIdStatus(commandeIndividuelleStatusDAO.getCommandeIndividuelleStatusByCode(
				CommandeIndividuelleStatusEnum.CDE_INDIVID_NON_CONFIRMEE.getCode()));

		commandeIndividuelle.setTotalAPayer(panier.getPanierMontant());
		// TODO
		// commandeIndividuelle.setReduction(reduction);

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
	public List<PanierProduit> getProduitsByPAnier(Panier panier) {
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
