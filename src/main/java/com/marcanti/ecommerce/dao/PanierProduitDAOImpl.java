/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

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
    
}
