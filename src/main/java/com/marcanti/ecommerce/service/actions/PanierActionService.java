package com.marcanti.ecommerce.service.actions;

import java.util.List;

import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.Panier;
import com.marcanti.ecommerce.model.PanierProduit;
import com.marcanti.ecommerce.model.Produit;

public interface PanierActionService {

	Panier addProduct(Produit produit, Long idMembre, Long idOrg);

	Panier create(Panier panier);

	boolean isExistingCommandeGroupee(Membre utilisateur);

	List<PanierProduit> getProduitsByPAnier(Panier panier);

	List<PanierProduit> getProduitsByCmdIndiv(Long idCmdIndiv);

}
