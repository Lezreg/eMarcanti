package com.marcanti.ecommerce.service.actions;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.constants.CommandeIndividuelleStatusEnum;
import com.marcanti.ecommerce.dao.CommandeGroupeeDAO;
import com.marcanti.ecommerce.dao.CommandeIndividuelleDAO;
import com.marcanti.ecommerce.dao.CommandeIndividuelleStatusDAO;
import com.marcanti.ecommerce.dao.MembreDAO;
import com.marcanti.ecommerce.dao.PanierDAO;
import com.marcanti.ecommerce.dao.PanierProduitDAO;
import com.marcanti.ecommerce.dao.ProduitDAO;
import com.marcanti.ecommerce.exception.ProductNotAvailableException;
import com.marcanti.ecommerce.model.CommandeGroupee;
import com.marcanti.ecommerce.model.CommandeIndividuelle;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Panier;
import com.marcanti.ecommerce.model.PanierProduit;
import com.marcanti.ecommerce.model.PanierProduitPK;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.utils.BigDecimalUtils;
import com.marcanti.ecommerce.utils.ParametersChecker;
import com.marcanti.ecommerce.utils.ShortUtils;
import com.marcanti.ecommerce.view.bean.UserSessionBean;


@Service("panierActionService")
@Transactional
public class PanierServiceActionImpl implements PanierActionService {

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

	@Override
	public Panier create(Panier panier) {
		return panierDao.edit(panier);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Panier addProduct(Produit produit, Long idMembre, Long idOrg) {
		ParametersChecker.checkParameter("produit is null ", produit);
		Panier panierEnCours = null;
		CommandeGroupee cmdgroupee = commandeGroupeeDAO.find(1L);// with id
		Membre member = membreDAO.find(idMembre);

		// verifier si panier existe deja (commande indiv)
		CommandeIndividuelle commandeIndividuel = commandeIndividuelleDAO
				.getCommandeIndividuellByMembreAndCmdGroupe(member, cmdgroupee);

		if (commandeIndividuel != null) {
			PanierProduit panierProduit = panierProduitDAO.getPanierProduitByPanierAndProduit(
					commandeIndividuel.getIdPanier().getIdPanier(),
					produit.getIdProduit());
			if (panierProduit != null) {
				panierProduit.setQteSouhaitee(ShortUtils.incrementShort(panierProduit.getQteSouhaitee()));
				panierProduitDAO.edit(panierProduit);
				// update panier
				panierEnCours = panierProduit.getPanier();
				updatePanier(produit, panierEnCours);
				// update commande individuelle
				updateCommandeIndividuel(member, commandeIndividuel, panierEnCours);

			} else {
				// si le produit n'est pas encore ajouté , alors on cree un
				// objet panierproduit
				panierEnCours = commandeIndividuel.getIdPanier();
				panierProduit = getNewPanierProduit(commandeIndividuel.getIdPanier(), produit);
				panierProduitDAO.create(panierProduit);
				// update panier (quantite et Montant)
				updatePanier(produit, panierEnCours);

				Collection<PanierProduit> panierProduitCollection = panierEnCours.getPanierProduitCollection();
				for (PanierProduit panierProduit2 : panierProduitCollection) {
					panierProduit2.getProduit();
					// TODO
				}
			}


		}else{
			// else: create panier
			// create panier
			Panier panier = getNewPanier(produit, member);
			panier = create(panier);
			// create panierproduit
			PanierProduit panierProduit = getNewPanierProduit(panier, produit);
			panierProduitDAO.create(panierProduit);

			// create commande indiv
			CommandeIndividuelle commandeIndividuelle = getNewCommandeIndividuelle(member, panier, cmdgroupee);
			commandeIndividuelleDAO.create(commandeIndividuelle);
		}
		
		return panierEnCours;

	}

	private void updateCommandeIndividuel(Membre utilisateur, CommandeIndividuelle commandeIndividuel,
			Panier panierEnCours) {
		commandeIndividuel.setDateModification(new Date());
		// nom et prenom modifieur
		commandeIndividuel.setNomModifieur(utilisateur.getMembreNom());
		commandeIndividuel.setPrenomModifieur(utilisateur.getMembrePrenom());
		commandeIndividuel.setTotalAPayer(panierEnCours.getPanierMontant());
		// update
		commandeIndividuelleDAO.edit(commandeIndividuel);
	}

	private void updatePanier(Produit produit, Panier panierEnCours) {
		panierEnCours.setPanierMontant(
				BigDecimalUtils.sum(panierEnCours.getPanierMontant(), produit.getNotrePrix()));
		panierEnCours.setPanierNbreProduit(ShortUtils.incrementShort(panierEnCours.getPanierNbreProduit()));
		panierDao.edit(panierEnCours);
	}


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
		commandeIndividuelle.setIdStatus(commandeIndividuelleStatusDAO
				.getCommandeIndividuelleStatusByCode(CommandeIndividuelleStatusEnum.CDE_INDIVID_NON_CONFIRMEE.getCode()));

		commandeIndividuelle.setTotalAPayer(panier.getPanierMontant());
		// TODO
		// commandeIndividuelle.setReduction(reduction);

		return commandeIndividuelle;
	}

	private PanierProduit getNewPanierProduit(Panier panier, Produit produit) {
		PanierProduitPK panierProduitPK = new PanierProduitPK(panier.getIdPanier(), produit.getIdProduit());
		PanierProduit panierProduit = new PanierProduit(panierProduitPK);
		panierProduit.setPanier(panier);
		panierProduit.setProduit(produit);
		panierProduit.setQteSouhaitee(ShortUtils.ONE);
		return panierProduit;
	}

	private Panier getNewPanier(Produit produit, Membre utilisateur) {
		ParametersChecker.checkParameter("utilisateur cannot be null ", utilisateur);
		Panier panier = new Panier();
		// TODO fix nom de panier
		Organisation organisation = utilisateur.getIdOrga();
		panier.setPanierNom(organisation.getOrgaAlias() + "_" + utilisateur.getMembreNom());
		panier.setPanierMontant(produit.getNotrePrix());
		panier.setPanierNbreProduit(ShortUtils.ONE);
		return panier;
	}


	@Override
	public List<PanierProduit> recalculer(List<PanierProduit> panierProduitList) throws ProductNotAvailableException {

		BigDecimal totalPanier = BigDecimal.ZERO;
		Panier panier = null;
		Short panierNbreProduit = ShortUtils.DEFAULT;
		for (PanierProduit panierProduit : panierProduitList) {
			if (panierProduit.getProduit().getQteEnStock() - panierProduit.getQteSouhaitee() < 0)
			{
				throw new ProductNotAvailableException("Vérifier votre commande : Quantité disponible pour le produit "
						+ panierProduit.getProduit().getProduitDescription() + " est : "
						+ panierProduit.getProduit().getQteEnStock());
			}
			totalPanier = totalPanier.add(panierProduit.getSousTotal());
			panierNbreProduit = ShortUtils.sum2Short(panierNbreProduit, panierProduit.getQteSouhaitee());
			panierProduitDAO.edit(panierProduit);

			if (panier == null) {
				panier = panierProduit.getPanier();
			}
		}
		// TODO
		// commandeIndividuelle.setReduction(reduction);
		// update montant panier
		panier.setPanierMontant(totalPanier);
		panier.setPanierNbreProduit(panierNbreProduit);
		// TODO Total à payer montantTotal - reduction
		panier.getCommandeIndividuelle().setTotalAPayer(totalPanier);
		panier = panierDao.edit(panier);
		// update totale à payer sur la commande individuelle
		
		CommandeIndividuelle cmdIndiv = commandeIndividuelleDAO
				.find(panier.getCommandeIndividuelle().getIdCdeIndiv());
		// TODO substract reduction
		cmdIndiv.setTotalAPayer(totalPanier);
		commandeIndividuelleDAO.edit(cmdIndiv);


		// return updated list of panier produit
		return getProduitsByCmdIndiv(cmdIndiv.getIdCdeIndiv());
	}

	@Override
	public void confirmerCommandeIndiv(CommandeIndividuelle commandeIndividuelle, List<PanierProduit> panierProduitList,
			UserSessionBean userSessionBean)
			throws ProductNotAvailableException {
		// recalculer et verifier la disponibilité de produit en stock
		recalculer(panierProduitList);
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
		// mettre à jour la commande individuelle
		commandeIndividuelle.setDateModification(new Date());
		commandeIndividuelle.setNomModifieur(userSessionBean.getMembreNom());
		commandeIndividuelle.setPrenomModifieur(userSessionBean.getMembrePrenom());
		commandeIndividuelle.setIdStatus(commandeIndividuelleStatusDAO
				.getCommandeIndividuelleStatusByCode(CommandeIndividuelleStatusEnum.CDE_INDIVID_CONFIRMEE.getCode()));
		commandeIndividuelleDAO.edit(commandeIndividuelle);
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



}
