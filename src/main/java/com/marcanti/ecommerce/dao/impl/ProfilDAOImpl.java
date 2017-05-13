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
import com.marcanti.ecommerce.dao.ProfilDAO;
import com.marcanti.ecommerce.model.Profil;

/**
 *
 * @author lezreg
 */
public class ProfilDAOImpl extends AbstractGenericDAO<Profil> implements ProfilDAO {

	@PersistenceContext
    private EntityManager em;

    public ProfilDAOImpl() {
        super(Profil.class);
    }

    @Override
    public void create(Profil entity) {
        super.create(entity);
    }

	public void edit(Short id, Profil entity) {
        super.edit(entity);
    }

	public void remove(Short id) {
        super.remove(super.find(id));
    }

	public Profil find(Short id) {
        return super.find(id);
    }

    public List<Profil> findAll() {
        return super.findAll();
    }

	public List<Profil> findRange(Integer from, Integer to) {
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
