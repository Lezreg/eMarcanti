package com.marcanti.ecommerce.service.actions;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.exception.CommandeGroupeeNotFoundException;
import com.marcanti.ecommerce.exception.CommandeGroupeeValidatedExeception;
import com.marcanti.ecommerce.exception.ProductNotAvailableException;
import com.marcanti.ecommerce.model.CommandeIndividuelle;
import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.Panier;
import com.marcanti.ecommerce.model.PanierProduit;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

@Transactional
public interface PanierActionService {

	Panier addProduct(Produit produit, UserSessionBean userSessionBean)
			throws CommandeGroupeeNotFoundException, CommandeGroupeeValidatedExeception;

	void createPanier(Panier panier);

	boolean isExistingCommandeGroupee(Membre utilisateur);

	List<PanierProduit> getProduitsByPanier(Panier panier);

	List<PanierProduit> getProduitsByCmdIndiv(Long idCmdIndiv);

	List<PanierProduit> recalculer(List<PanierProduit> panierProduitList, BigDecimal reduction,
			UserSessionBean userSessionBean, boolean isConfirmation) throws ProductNotAvailableException;

	void confirmerCommandeIndiv(CommandeIndividuelle commandeIndividuelle, List<PanierProduit> panierProduitList,
			BigDecimal reduction, UserSessionBean userSessionBean) throws ProductNotAvailableException;

	CommandeIndividuelle getLastCommandeIndividuelle(UserSessionBean userSessionBean);

	void updatePanierProduits(List<PanierProduit> panierProduitList);

}
