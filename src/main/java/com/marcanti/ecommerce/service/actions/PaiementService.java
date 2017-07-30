package com.marcanti.ecommerce.service.actions;

import com.marcanti.ecommerce.model.CommandeIndividuelle;
import com.marcanti.ecommerce.model.TransactionPaiement;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

public interface PaiementService {

	void payerCommande(CommandeIndividuelle commandeIndividuelle, TransactionPaiement transactionPaiement,
			UserSessionBean userSessionBean);

}
