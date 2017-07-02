package com.marcanti.ecommerce.service.actions;

import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.model.Panier;
import com.marcanti.ecommerce.model.Produit;

public interface PanierActionService {

	Panier addProduct(Produit produit, Membre utilisateur);

	Panier create(Panier panier);

	boolean isExistingCommandeGroupee(Membre utilisateur);

}
