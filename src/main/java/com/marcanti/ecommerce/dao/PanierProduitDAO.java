package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.PanierProduit;

public interface PanierProduitDAO extends GenericDAO<PanierProduit> {

	PanierProduit getPanierProduitByPanierAndProduit(Long idPanier, Long idProduit);

	List<PanierProduit> getPanierProduitByPanier(Long idPanier);

	List<PanierProduit> findByCmdIndiv(Long idCmdIndiv);

}
