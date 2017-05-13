/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.marcanti.ecommerce.dao.AbstractGenericDAO;
import com.marcanti.ecommerce.dao.PanierProduitDAO;
import com.marcanti.ecommerce.model.PanierProduit;

/**
 *
 * @author lezreg
 */

public class PanierProduitDAOImpl extends AbstractGenericDAO<PanierProduit> implements PanierProduitDAO {

	@PersistenceContext
    private EntityManager em;

    public PanierProduitDAOImpl() {
        super(PanierProduit.class);
    }

    public void create(PanierProduit entity) {
        super.create(entity);
    }

	public void edit(PanierProduit entity) {
        super.edit(entity);
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
    
}
