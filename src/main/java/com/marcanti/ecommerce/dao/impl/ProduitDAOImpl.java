/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.marcanti.ecommerce.dao.AbstractGenericDAO;
import com.marcanti.ecommerce.dao.ProduitDAO;
import com.marcanti.ecommerce.model.Produit;

/**
 *
 * @author lezreg
 */
@Repository
public class ProduitDAOImpl extends AbstractGenericDAO<Produit> implements ProduitDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProduitDAOImpl.class);

	@PersistenceContext
    private EntityManager em;

    public ProduitDAOImpl() {
        super(Produit.class);
    }

    public void create(Produit entity) {
        super.create(entity);
    }

	public void edit(Long id, Produit entity) {
        super.edit(entity);
    }

	public void remove(Long id) {
        super.remove(super.find(id));
    }

	public Produit find(Long id) {
        return super.find(id);
    }

    public List<Produit> findAll() {
        return super.findAll();
    }

	public List<Produit> findRange(Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }

	public List<Produit> findProduitByCategorie(String CatCOde) {
		Query query = em
				.createQuery("SELECT p FROM Produit p WHERE p.qteEnStock>0 and p.idCategorie.categorieCode = :CatCOde")
				.setParameter("CatCOde", CatCOde);
		return query.getResultList();
	}
    public String countREST() {
        return String.valueOf(super.count());
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Produit> getAllProduits() {
		return super.findAll();
	}
}
