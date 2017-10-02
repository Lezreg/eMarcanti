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

import com.marcanti.ecommerce.model.Panier;

/**
 *
 * @author lezreg
 */

@Repository
public class PanierDAOImpl extends AbstractGenericDAO<Panier> implements PanierDAO {

	@PersistenceContext
    private EntityManager em;

    public PanierDAOImpl() {
        super(Panier.class);
    }

    @Override
    public void create(Panier entity) {
        super.create(entity);
    }

	public void edit(Long id, Panier entity) {
        super.edit(entity);
    }

	public void remove(Long id) {
        super.remove(super.find(id));
    }


	public Panier find(Long id) {
        return super.find(id);
    }

    @Override
    public List<Panier> findAll() {
        return super.findAll();
    }

	public List<Panier> findRange(Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
