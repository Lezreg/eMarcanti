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

import com.marcanti.ecommerce.model.Filleul;

/**
 *
 * @author lezreg
 */
@Repository
public class FilleulDAOImpl extends AbstractGenericDAO<Filleul> implements FilleulDAO {

	@PersistenceContext
    private EntityManager em;

    public FilleulDAOImpl() {
        super(Filleul.class);
    }

    public void create(Filleul entity) {
        super.create(entity);
    }

	public void edit(Long id, Filleul entity) {
        super.edit(entity);
    }

	public void remove(Long id) {
        super.remove(super.find(id));
    }

	public Filleul find(Long id) {
        return super.find(id);
    }

    @Override
    public List<Filleul> findAll() {
        return super.findAll();
    }

	public List<Filleul> findRange(Integer from, Integer to) {
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
