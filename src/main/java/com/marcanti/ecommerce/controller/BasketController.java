package com.marcanti.ecommerce.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
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
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.marcanti.ecommerce.exception.ProductNotAvailableException;
import com.marcanti.ecommerce.model.CommandeIndividuelle;
import com.marcanti.ecommerce.model.Panier;
import com.marcanti.ecommerce.model.PanierProduit;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.service.actions.CommandeIndividuelleServiceAction;
import com.marcanti.ecommerce.service.actions.PanierActionService;
import com.marcanti.ecommerce.service.actions.ProduitServiceAction;
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
	private PanierActionService panierService;

	@Autowired
	private ProduitServiceAction produitServiceAction;

	@Autowired
	private CommandeIndividuelleServiceAction commandeIndividuelleServiceAction;

	private Panier panierEnCours;

	private List<CommandeIndividuelle> cmdEnCours;

	private String selectedCmd;

	private CommandeIndividuelle commandeIndividuelle;

	public Panier getPanierEnCours() {
		return panierEnCours;
	}

	public void setPanierEnCours(Panier panierEnCours) {
		this.panierEnCours = panierEnCours;
	}

	private List<PanierProduit> panierProduitList;

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);

		// FIXME load panier produit
		// if commande groupee not exist then send message ("Impossible
		// d'ajouter des produits "page 26)

		// by default last commandeInd will be selected
		setPanierProduitList(new ArrayList<PanierProduit>());
		setCmdEnCours(new ArrayList<CommandeIndividuelle>());
	}

	public void addPoduct(Produit produit, int quantite) {

		UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();
		// FIXME not support quantity
		LOGGER.info(produit.toString());
		// TODO return panier after update
		panierEnCours = panierService.addProduct(produit, userSessionBean.getIdMembre(), userSessionBean.getIdOrga());
		// panierService
		setPanierProduitList(panierService.getProduitsByPAnier(panierEnCours));
	}

	public String redirect() {
		return "panier";
	}

	public PanierActionService getPanierService() {
		return panierService;
	}

	public void setPanierService(PanierActionService panierService) {
		this.panierService = panierService;
	}

	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
		init();
	}

	public List<PanierProduit> getPanierProduitList() {
		if (selectedCmd != null) {

			this.commandeIndividuelle = commandeIndividuelleServiceAction
					.getCommandeIndividuelleById(new Long(selectedCmd));
			panierProduitList = panierService.getProduitsByCmdIndiv(new Long(selectedCmd));
		}

		return panierProduitList;
	}

	public void setPanierProduitList(List<PanierProduit> panierProduit) {
		this.panierProduitList = panierProduit;
	}

	public List<CommandeIndividuelle> getCmdEnCours() {
		UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();

			cmdEnCours = commandeIndividuelleServiceAction.getCmdEnCoursParMembre(userSessionBean.getIdMembre(), 1L);
			if (selectedCmd == null || selectedCmd.isEmpty()) {
				selectedCmd = cmdEnCours.get(0).getIdCdeIndiv().toString();

		}
		return cmdEnCours;
	}

	public void setCmdEnCours(List<CommandeIndividuelle> cmdEnCours) {
		this.cmdEnCours = cmdEnCours;
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

	/**
	 * recalculer la panier après la modification
	 */
	public void reCalculer() {

		try {
			this.panierProduitList = panierService.recalculer(panierProduitList);

		} catch (ProductNotAvailableException e) {
			LOGGER.error(e.getMessage());
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(),
					"! Mettez à jour le panier");
			FacesContext.getCurrentInstance().addMessage("qte", facesMsg);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Problème Technique : ",
					" Contactez votre administrateur");
			FacesContext.getCurrentInstance().addMessage("qte", facesMsg);
		}
	}

	public void confirmerCommandeIndiv() {
		try {
			UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();
			panierService.confirmerCommandeIndiv(commandeIndividuelle, panierProduitList, userSessionBean);
		} catch (ProductNotAvailableException e) {
			LOGGER.error(e.getMessage());
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(),
					"! Mettez à jour le panier");
			FacesContext.getCurrentInstance().addMessage("qte", facesMsg);
		}



	}

}
