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
import com.marcanti.ecommerce.dao.SousCategorieDAO;
import com.marcanti.ecommerce.model.SousCategorie;

/**
 *
 * @author lezreg
 */
public class SousCategorieDAOImpl extends AbstractGenericDAO<SousCategorie> implements SousCategorieDAO {

	@PersistenceContext
    private EntityManager em;

    public SousCategorieDAOImpl() {
        super(SousCategorie.class);
    }

    @Override
    public void create(SousCategorie entity) {
        super.create(entity);
    }

	public void edit(Short id, SousCategorie entity) {
        super.edit(entity);
    }

	public void remove(Short id) {
        super.remove(super.find(id));
    }

	public SousCategorie find(Short id) {
        return super.find(id);
    }


    public List<SousCategorie> findAll() {
        return super.findAll();
    }

	public List<SousCategorie> findRange(Integer from, Integer to) {
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
