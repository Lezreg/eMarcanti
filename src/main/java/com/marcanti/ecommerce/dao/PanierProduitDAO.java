package com.marcanti.ecommerce.dao;

import com.marcanti.ecommerce.model.PanierProduit;

public interface PanierProduitDAO extends GenericDAO<PanierProduit> {

	PanierProduit getPanierProduitByPanierAndProduit(Long idPanier, Long idProduit);

}
