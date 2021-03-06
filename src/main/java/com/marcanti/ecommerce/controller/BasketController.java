package com.marcanti.ecommerce.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.marcanti.ecommerce.exception.CommandeGroupeeNotFoundException;
import com.marcanti.ecommerce.exception.CommandeGroupeeValidatedExeception;
import com.marcanti.ecommerce.exception.ProductNotAvailableException;
import com.marcanti.ecommerce.exception.ProductOutOfStockException;
import com.marcanti.ecommerce.model.CommandeIndividuelle;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Panier;
import com.marcanti.ecommerce.model.PanierProduit;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.service.actions.CommandeGroupeeServiceAction;
import com.marcanti.ecommerce.service.actions.CommandeIndividuelleServiceAction;
import com.marcanti.ecommerce.service.actions.OrganisationServiceAction;
import com.marcanti.ecommerce.service.actions.PanierActionService;
import com.marcanti.ecommerce.utils.ParametersChecker;
import com.marcanti.ecommerce.utils.ParfumUtils;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

@ManagedBean(name = "basketView")
@SessionScoped
public class BasketController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1960368412588524596L;

	private static final Logger LOGGER = LoggerFactory.getLogger(BasketController.class);

	@Autowired
	@Qualifier("panierActionService")
	private PanierActionService panierService;

	@Autowired
	@Qualifier("commandeGroupeeServiceAction")
	private CommandeGroupeeServiceAction commandeGroupeeServiceAction;

	@Autowired
	private CommandeIndividuelleServiceAction commandeIndividuelleServiceAction;

	@Autowired
	private OrganisationServiceAction organisationServiceAction;

	private Panier panierEnCours;

	private List<CommandeIndividuelle> commandes;

	private String selectedCmd;

	private CommandeIndividuelle commandeIndividuelle;

	private boolean isCurrrentCmds = true;

	private BigDecimal reduction;

	UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();

	private List<PanierProduit> panierProduitList;

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);

		getCommandes();
		getPanierProduitList();

	}

	public void addPoduct(Produit produit, int quantite)
			throws CommandeGroupeeNotFoundException, CommandeGroupeeValidatedExeception, ProductOutOfStockException {
		ParametersChecker.checkParameter("produit is null ", produit);
		LOGGER.debug(produit.toString());

		if (produit.getQteEnStock() < 1) {
			throw new ProductOutOfStockException("Product out of stock");
		}

		panierEnCours = panierService.addProduct(produit, userSessionBean);
		// panierService
		setPanierProduitList(panierService.getProduitsByPanier(panierEnCours));
	}

	public String redirectCurrentCmd() {
		this.isCurrrentCmds = true;
		return "commandes_enCours";
	}

	public String redirectPrecCmd() {
		this.isCurrrentCmds = false;
		return "commandes_pre";
	}

	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
		init();
	}

	/**
	 * sauvegarder en IHM<br>
	 * recalculer le panier après la modification
	 */
	public void reCalculer() {

		UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();

		try {
			// TODO biding reduction in IHM
			this.panierProduitList = panierService.recalculer(panierProduitList, reduction, userSessionBean, false);
			// add messages
			addFacesMessages();
		} catch (ProductNotAvailableException e) {
			LOGGER.error(e.getMessage());
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(),
					"! Mettez à jour le panier");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problème Technique : ",
					" Contactez votre administrateur");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
	}

	private void addFacesMessages() {
		FacesMessage saveMessageInfo = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"La commande a été sauvegardée dans votre espace internet", null);
		FacesContext.getCurrentInstance().addMessage(null, saveMessageInfo);

		if (commandeIndividuelle != null && !commandeIndividuelle.isConfirmed()) {
			FacesMessage saveMessageWarning = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Veuillez confirmer la commande, afin que les produits vous soient réservés !", null);
			FacesContext.getCurrentInstance().addMessage(null, saveMessageWarning);
		}
	}

	/**
	 * Confirmer une commande
	 */
	public void confirmerCommandeIndiv() {
		try {
			UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();
			panierService.confirmerCommandeIndiv(commandeIndividuelle, panierProduitList, reduction, userSessionBean);

			// La commande est confirmée ! Les produits vous ont été réservés.
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"La commande est confirmée ! Les produits vous ont été réservés.", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);

		} catch (ProductNotAvailableException e) {
			LOGGER.error(e.getMessage());
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(),
					"! Mettez à jour le panier");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}

	}

	/**
	 * 
	 * @return la liste des panierProduits pour une commande
	 */
	public List<PanierProduit> getPanierProduitList() {
		if (selectedCmd != null) {
			this.commandeIndividuelle = commandeIndividuelleServiceAction
					.getCommandeIndividuelleById(new Long(selectedCmd));
			panierProduitList = panierService.getProduitsByCmdIndiv(new Long(selectedCmd));
			LOGGER.info("Id organisation " + userSessionBean.getIdOrga());
			Organisation organisation = organisationServiceAction.getOrganisationById(userSessionBean.getIdOrga());

			for (PanierProduit panierProduit : panierProduitList) {
				Short qtePossible = 0;
				if (organisation != null) {
					qtePossible = (short) organisation.getNbreMaxProduitParItem();
				}
				if (panierProduit.getProduit() != null && panierProduit.getProduit().getQteEnStock() < qtePossible) {
					qtePossible = panierProduit.getProduit().getQteEnStock();
				}
				panierProduit.getProduit().setQtePossible(qtePossible);
			}
		}
		return panierProduitList;
	}

	/**
	 * 
	 * @return la liste des commandes en cours ou précedentes par membre
	 */
	public List<CommandeIndividuelle> getCommandes() {
		UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();

		Long derniereCdeGoupee = commandeGroupeeServiceAction.getIdDerniereCdeGoupee(userSessionBean.getIdOrga());
		if (derniereCdeGoupee != null) {
			LOGGER.debug("derniereCdeGoupee : " + derniereCdeGoupee.toString());
			LOGGER.debug("member : " + userSessionBean.getIdMembre().toString());
			// récupérer les commandes en cours du member connecté
			commandes = commandeIndividuelleServiceAction.getCmdEnCoursParMembre(userSessionBean.getIdMembre(),
					derniereCdeGoupee, isCurrrentCmds);
			// renseigner le selected commande ID
			if (selectedCmd == null || selectedCmd.isEmpty()) {
				if (commandes != null && !commandes.isEmpty()) {
					CommandeIndividuelle selectedCommandeIndividuelle = commandes.get(0);
					selectedCmd = selectedCommandeIndividuelle.getIdCdeIndiv().toString();
				}
			}

			LOGGER.info(selectedCmd);
		}

		return commandes;
	}

	public Panier getPanierEnCours() {

		CommandeIndividuelle lastCommandeIndividuelle = panierService.getLastCommandeIndividuelle(userSessionBean);

		if (lastCommandeIndividuelle != null && lastCommandeIndividuelle.getIdPanier() != null) {
			return lastCommandeIndividuelle.getIdPanier();
		}

		return new Panier();

	}

	public void setPanierEnCours(Panier panierEnCours) {
		this.panierEnCours = panierEnCours;
	}

	public void setPanierProduitList(List<PanierProduit> panierProduit) {
		this.panierProduitList = panierProduit;
	}

	public CommandeIndividuelleServiceAction getCommandeIndividuelleServiceAction() {
		return commandeIndividuelleServiceAction;
	}

	public void setCommandeIndividuelleServiceAction(
			CommandeIndividuelleServiceAction commandeIndividuelleServiceAction) {
		this.commandeIndividuelleServiceAction = commandeIndividuelleServiceAction;
	}

	public String getSelectedCmd() {
		return selectedCmd;
	}

	public void setSelectedCmd(String selectedCmd) {
		this.selectedCmd = selectedCmd;
	}

	public CommandeIndividuelle getCommandeIndividuelle() {
		return commandeIndividuelle;
	}

	public void setCommandeIndividuelle(CommandeIndividuelle commandeIndividuelle) {
		this.commandeIndividuelle = commandeIndividuelle;
	}

	public PanierActionService getPanierService() {
		return panierService;
	}

	public void setPanierService(PanierActionService panierService) {
		this.panierService = panierService;
	}

	public void setCommandes(List<CommandeIndividuelle> commandes) {
		this.commandes = commandes;
	}

	public CommandeGroupeeServiceAction getCommandeGroupeeServiceAction() {
		return commandeGroupeeServiceAction;
	}

	public void setCommandeGroupeeServiceAction(CommandeGroupeeServiceAction commandeGroupeeServiceAction) {
		this.commandeGroupeeServiceAction = commandeGroupeeServiceAction;
	}

	public OrganisationServiceAction getOrganisationServiceAction() {
		return organisationServiceAction;
	}

	public void setOrganisationServiceAction(OrganisationServiceAction organisationServiceAction) {
		this.organisationServiceAction = organisationServiceAction;
	}

}
