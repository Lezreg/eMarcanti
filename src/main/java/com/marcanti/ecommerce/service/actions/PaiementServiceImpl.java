package com.marcanti.ecommerce.service.actions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.constants.CommandeIndividuelleStatus;
import com.marcanti.ecommerce.constants.StatutTransactionFournissPaiement;
import com.marcanti.ecommerce.dao.CommandeIndividuelleDAO;
import com.marcanti.ecommerce.dao.CommandeIndividuelleStatusDAO;
import com.marcanti.ecommerce.dao.PanierDAO;
import com.marcanti.ecommerce.dao.PanierProduitDAO;
import com.marcanti.ecommerce.dao.TransactionPaiementDAO;
import com.marcanti.ecommerce.model.CommandeIndividuelle;
import com.marcanti.ecommerce.model.TransactionPaiement;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

@Service("paiementService")
@Transactional
public class PaiementServiceImpl implements PaiementService {

	@Autowired
	private PanierDAO panierDao;
	@Autowired
	private PanierProduitDAO panierProduitDAO;
	@Autowired
	private CommandeIndividuelleDAO commandeIndividuelleDAO;
	@Autowired
	private TransactionPaiementDAO transactionPaiementDAO;
	@Autowired
	private CommandeIndividuelleStatusDAO commandeIndividuelleStatusDAO;

	@Override
	public void payerCommande(CommandeIndividuelle commandeIndividuelle, TransactionPaiement transactionPaiement,
			UserSessionBean userSessionBean) {
		// TODO call service web
		// FIXME @mock
		boolean isResponseOk = true;

		if (isResponseOk) {
			createTransactionPaiement(commandeIndividuelle, transactionPaiement, userSessionBean);
			// mettre à jour commande indiv
			updateCommandeIndividuelle(commandeIndividuelle, transactionPaiement, userSessionBean);
		}

	}

	private void createTransactionPaiement(CommandeIndividuelle commandeIndividuelle,
			TransactionPaiement transactionPaiement, UserSessionBean userSessionBean) {
		transactionPaiement.setDatePaiement(new Date());
		transactionPaiement.setIdTransactionFournissPaiement("TODO MOCK web service");
		transactionPaiement.setMontantPaiement(commandeIndividuelle.getTotalAPayer());
		// FIXME add cdeGroupeeNom
		transactionPaiement.setIdTransactionPaiement(generateTransactionId(userSessionBean));
		transactionPaiement
				.setStatutTransactionFournissPaiement(
						StatutTransactionFournissPaiement.TRANSACTION_CONFIRMEE.getCode());

		transactionPaiementDAO.create(transactionPaiement);
	}

	private void updateCommandeIndividuelle(CommandeIndividuelle commandeIndividuelle,
			TransactionPaiement transactionPaiement, UserSessionBean userSessionBean) {
		commandeIndividuelle.setNomModifieur(userSessionBean.getMembreNom());
		commandeIndividuelle.setPrenomModifieur(userSessionBean.getMembreNom());
		
		commandeIndividuelle.setIdStatus(commandeIndividuelleStatusDAO
				.getCommandeIndividuelleStatusByCode(CommandeIndividuelleStatus.CDE_INDIVID_PAYEE.getCode()));
		commandeIndividuelle.setIdTransactionPaiement(transactionPaiement);
		commandeIndividuelle.setDateModification(new Date());

		commandeIndividuelleDAO.edit(commandeIndividuelle);
	}

	private String generateTransactionId(UserSessionBean userSessionBean) {
		// FIXME add cdeGroupeeNom
		return "TODO_cdeGroupeeNom_" + userSessionBean.getMembreNom() + "_"
				+ userSessionBean.getMembrePrenom() + "_" + sysDate();
	}

	private String sysDate() {
		LocalDateTime ldt = LocalDateTime.now();
		String yyyymmddd = ldt.format(DateTimeFormatter.BASIC_ISO_DATE);
		return yyyymmddd + "_" + ldt.getHour() + ":" + ldt.getMinute() + ":" + ldt.getSecond();
	}

	public PanierDAO getPanierDao() {
		return panierDao;
	}

	public void setPanierDao(PanierDAO panierDao) {
		this.panierDao = panierDao;
	}

	public PanierProduitDAO getPanierProduitDAO() {
		return panierProduitDAO;
	}

	public void setPanierProduitDAO(PanierProduitDAO panierProduitDAO) {
		this.panierProduitDAO = panierProduitDAO;
	}

	public CommandeIndividuelleDAO getCommandeIndividuelleDAO() {
		return commandeIndividuelleDAO;
	}

	public void setCommandeIndividuelleDAO(CommandeIndividuelleDAO commandeIndividuelleDAO) {
		this.commandeIndividuelleDAO = commandeIndividuelleDAO;
	}

	public TransactionPaiementDAO getTransactionPaiementDAO() {
		return transactionPaiementDAO;
	}

	public void setTransactionPaiementDAO(TransactionPaiementDAO transactionPaiementDAO) {
		this.transactionPaiementDAO = transactionPaiementDAO;
	}



}
