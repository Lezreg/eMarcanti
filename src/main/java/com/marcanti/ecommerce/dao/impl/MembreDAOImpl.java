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
import com.marcanti.ecommerce.dao.MembreDAO;
import com.marcanti.ecommerce.model.Membre;

/**
 *
 * @author admin
 */
public class MembreDAOImpl extends AbstractGenericDAO<Membre> implements MembreDAO {

	@PersistenceContext
    private EntityManager em;

    public MembreDAOImpl() {
        super(Membre.class);
    }

    @Override
    public void create(Membre entity) {
        super.create(entity);
    }

	public void edit(Long id, Membre entity) {
        super.edit(entity);
    }

	public void remove(Long id) {
        super.remove(super.find(id));
    }


	public Membre find(Long id) {
        return super.find(id);
    }

    @Override
    public List<Membre> findAll() {
        return super.findAll();
    }

	public List<Membre> findRange(Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
