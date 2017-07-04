/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.marcanti.ecommerce.exception.IllegalDataValueException;
import com.marcanti.ecommerce.model.PanierProduit;

/**
 *
 * @author lezreg
 */
@Repository
public class PanierProduitDAOImpl extends AbstractGenericDAO<PanierProduit> implements PanierProduitDAO {

	@PersistenceContext
    private EntityManager em;

    public PanierProduitDAOImpl() {
        super(PanierProduit.class);
    }

    public void create(PanierProduit entity) {
        super.create(entity);
    }

	public PanierProduit edit(PanierProduit entity) {

		return super.edit(entity);

    }

	public void remove(long id) {
		super.remove(super.find(id));
    }

	public PanierProduit find(long id) {
		return super.find(id);
    }

    public List<PanierProduit> findAll() {
        return super.findAll();
    }

	public List<PanierProduit> findRange(Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

	@Override
	public PanierProduit getPanierProduitByPanierAndProduit(Long idPanier, Long idProduit) {
		Query query = em
				.createQuery(
						"SELECT p FROM PanierProduit p WHERE p.panierProduitPK.idProduit = :idProduit AND p.panierProduitPK.idPanier = :idPanier")
				.setParameter("idProduit", idProduit).setParameter("idPanier", idPanier);

		if (query.getResultList().size() > 1) {
			throw new IllegalDataValueException();
		}

		if (query.getResultList() == null || query.getResultList().isEmpty()) {
			return null;
		}

		return (PanierProduit) query.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PanierProduit> getPanierProduitByPanier(Long idPanier) {
		Query query = em
				.createQuery(
						"SELECT p FROM PanierProduit p WHERE p.panierProduitPK.idPanier = :idPanier")
				.setParameter("idPanier", idPanier);

		if (query.getResultList() == null || query.getResultList().isEmpty()) {
			return Collections.<PanierProduit>emptyList();
		}
		return query.getResultList();
	}

	@Override
	public List<PanierProduit> findByCmdIndiv(Long idCmdIndiv) {
		Query query = em
				.createQuery(
						"SELECT p FROM PanierProduit p WHERE p.panier.commandeIndividuelle.idCdeIndiv = :idCmdIndiv")
				.setParameter("idCmdIndiv", idCmdIndiv);

		if (query.getResultList() == null) {
			return Collections.<PanierProduit>emptyList();
		}
		return query.getResultList();
	}
    
}
