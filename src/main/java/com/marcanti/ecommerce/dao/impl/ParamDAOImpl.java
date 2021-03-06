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
import com.marcanti.ecommerce.dao.ParamDAO;
import com.marcanti.ecommerce.model.Param;

/**
 *
 * @author lezreg
 */
public class ParamDAOImpl extends AbstractGenericDAO<Param> implements ParamDAO {

	@PersistenceContext
    private EntityManager em;

    public ParamDAOImpl() {
        super(Param.class);
	}

    @Override
    public void create(Param entity) {
        super.create(entity);
    }

	public void edit(String id, Param entity) {
        super.edit(entity);
    }

	public void remove(String id) {
        super.remove(super.find(id));
    }

	public Param find(String id) {
        return super.find(id);
    }

    @Override
    public List<Param> findAll() {
        return super.findAll();
    }

	public List<Param> findRange(Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

	@Override
	public String getDefaultPassword() {
		return (String)em.createNativeQuery("SELECT param.value FROM param where param.key='default.password'").getSingleResult();
	}
    
}
