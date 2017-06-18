package com.marcanti.ecommerce.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.dao.CommandeGroupeeDAO;
import com.marcanti.ecommerce.dao.CommandeIndividuelleDAO;
import com.marcanti.ecommerce.dao.MembreDAO;
import com.marcanti.ecommerce.dao.PanierDAO;
import com.marcanti.ecommerce.dao.PanierProduitDAO;
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


@Service("panierActionService")
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

	@Override
	public Panier create(Panier panier) {
		return panierDao.edit(panier);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void addProduct(Produit produit, Membre utilisateur) {
		// ParametersChecker.checkParameter("utilisateur is null ",
		// utilisateur);
		ParametersChecker.checkParameter("produit is null ", produit);

		// bouchon commandeGroupee
		// @mock
		// FIXME
		CommandeGroupee cmdgroupee = commandeGroupeeDAO.find(1L);// with id
																	// membre :
																	// 2 ;)
		utilisateur = membreDAO.find(2L);

		/**
		 * 
		 */

		// verifier si panier existe deja (commande indiv)
		CommandeIndividuelle commandeIndividuel = commandeIndividuelleDAO
				.getCommandeIndividuellByMembreAndCmdGroupe(utilisateur, cmdgroupee);

		if (commandeIndividuel != null) {
			PanierProduit panierProduit = panierProduitDAO.getPanierProduitByPanierAndProduit(
					commandeIndividuel.getIdPanier().getIdPanier(),
					produit.getIdProduit());
			if (panierProduit != null) {
				panierProduit.setQteSouhaitee(ShortUtils.incrementShort(panierProduit.getQteSouhaitee()));
				panierProduitDAO.edit(panierProduit);
				// update panier
				Panier panierEnCours = panierProduit.getPanier();
				updatePanier(produit, panierEnCours);
				// update commande individuelle
				updateCommandeIndividuel(utilisateur, commandeIndividuel);

			} else {
				// si le produit n'est pas encore ajouté , alors on cree un
				// objet panierproduit
				Panier panierEnCours = commandeIndividuel.getIdPanier();
				panierProduit = getNewPanierProduit(commandeIndividuel.getIdPanier(), produit);
				panierProduitDAO.create(panierProduit);
				// update panier (quantite et Montant)
				updatePanier(produit, panierEnCours);
			}


		}else{
			// else: create panier
			// create panier
			Panier panier = getNewPanier(produit, utilisateur);
			panier = create(panier);
			// create panierproduit
			PanierProduit panierProduit = getNewPanierProduit(panier, produit);
			panierProduitDAO.create(panierProduit);

			// create commande indiv
			CommandeIndividuelle commandeIndividuelle = getNewCommandeIndividuelle(utilisateur, panier);
			commandeIndividuelleDAO.create(commandeIndividuelle);
		}
		
	

	}

	private void updateCommandeIndividuel(Membre utilisateur, CommandeIndividuelle commandeIndividuel) {
		commandeIndividuel.setDateModification(new Date());
		// nom et prenom modifieur
		commandeIndividuel.setNomModifieur(utilisateur.getMembreNom());
		commandeIndividuel.setPrenomModifieur(utilisateur.getMembrePrenom());
		// update
		commandeIndividuelleDAO.edit(commandeIndividuel);
	}

	private void updatePanier(Produit produit, Panier panierEnCours) {
		panierEnCours.setPanierMontant(
				BigDecimalUtils.sum(panierEnCours.getPanierMontant(), produit.getNotrePrix()));
		panierEnCours.setPanierNbreProduit(ShortUtils.incrementShort(panierEnCours.getPanierNbreProduit()));
		panierDao.edit(panierEnCours);
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

	private CommandeIndividuelle getNewCommandeIndividuelle(Membre utilisateur, Panier panier) {
		CommandeIndividuelle commandeIndividuelle = new CommandeIndividuelle();
		commandeIndividuelle.setDateCreation(new Date());
		commandeIndividuelle.setIsPaiementEffectue(false);
		commandeIndividuelle.setIdMembre(utilisateur);
		commandeIndividuelle.setIdPanier(panier);
		commandeIndividuelle.setCdeIndivNom(
				utilisateur.getIdOrga().getOrgaNom() + "_" + utilisateur.getMembreNom() + "_" + new Date().toString());
		// FIXME add commande id status
		// commandeIndividuelle.setIdStatus(idStatus);

		commandeIndividuelle.setTotalAPayer(panier.getPanierMontant());
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

	@Override
	public boolean isExistingCommandeGroupee(Membre utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

}
