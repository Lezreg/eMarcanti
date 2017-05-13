/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.marcanti.ecommerce.dao.AbstractGenericDAO;
import com.marcanti.ecommerce.dao.DepartementDAO;
import com.marcanti.ecommerce.model.Departement;

/**
 *
 * @author lezreg
 */
@Repository
public class DepartementDAOImpl extends AbstractGenericDAO<Departement> implements DepartementDAO {

	@PersistenceContext
    private EntityManager em;

    public DepartementDAOImpl() {
        super(Departement.class);
    }

    @Override
    public void create(Departement entity) {
        super.create(entity);
    }

	public void edit(Long id, Departement entity) {
        super.edit(entity);
    }

	public void remove(Long id) {
        super.remove(super.find(id));
    }

	public Departement find(Long id) {
        return super.find(id);
    }

    public List<Departement> findAll() {
        return super.findAll();
    }

	public List<Departement> findRange(Integer from, Integer to) {
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
