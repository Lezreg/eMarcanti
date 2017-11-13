package com.marcanti.ecommerce.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.marcanti.ecommerce.model.CommandeIndividuelle;
import com.marcanti.ecommerce.model.TransactionPaiement;
import com.marcanti.ecommerce.service.actions.CommandeIndividuelleServiceAction;
import com.marcanti.ecommerce.service.actions.PaiementService;
import com.marcanti.ecommerce.service.actions.PanierActionService;
import com.marcanti.ecommerce.utils.ParfumUtils;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

@ManagedBean(name = "carteView")
@SessionScoped
public class CarteController implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarteController.class);

	public TransactionPaiement getTransactionPaiement() {
		return transactionPaiement;
	}

	public void setTransactionPaiement(TransactionPaiement transactionPaiement) {
		this.transactionPaiement = transactionPaiement;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7550178599204043431L;

	@Autowired
	private PanierActionService panierService;

	@Autowired
	private CommandeIndividuelleServiceAction commandeIndividuelleServiceAction;

	@Autowired
	private PaiementService paiementService;

	@ManagedProperty("#{basketView}")
	private BasketController basketController;

	private CommandeIndividuelle cmdIndivEnCours;

	private TransactionPaiement transactionPaiement;

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);

		transactionPaiement = new TransactionPaiement();
		// transactionPaiement.setMontantPaiement(getCmdIndivEnCours().getTotalAPayer());
	}

	/**
	 * Payer la commande Indiv <br>
	 * c'est une transaction bancaire pour payer une commande
	 */
	public void payerCommande() {

		UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();

		paiementService.payerCommande(getCmdIndivEnCours(), transactionPaiement, userSessionBean);
	}

	public String payer() {
		LOGGER.info("-------------payer--------------");
		return "payer";
	}

	public BasketController getBasketController() {
		return basketController;
	}

	public void setBasketController(BasketController basketController) {
		this.basketController = basketController;
	}

	public CommandeIndividuelle getCmdIndivEnCours() {
		return commandeIndividuelleServiceAction
				.getCommandeIndividuelleById(new Long(basketController.getSelectedCmd()));
	}

	public void setCmdIndivEnCours(CommandeIndividuelle cmdIndivEnCours) {
		this.cmdIndivEnCours = cmdIndivEnCours;
	}

}
