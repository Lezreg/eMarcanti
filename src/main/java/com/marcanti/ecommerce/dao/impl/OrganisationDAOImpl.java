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
import com.marcanti.ecommerce.dao.OrganisationDAO;
import com.marcanti.ecommerce.model.Organisation;

/**
 *
 * @author lezreg
 */
public class OrganisationDAOImpl extends AbstractGenericDAO<Organisation> implements OrganisationDAO {

	@PersistenceContext
    private EntityManager em;

    public OrganisationDAOImpl() {
        super(Organisation.class);
    }

    @Override
    public void create(Organisation entity) {
        super.create(entity);
    }

	public void edit(Long id, Organisation entity) {
        super.edit(entity);
    }

	public void remove(Long id) {
        super.remove(super.find(id));
    }

	public Organisation find(Long id) {
        return super.find(id);
    }

    @Override
    public List<Organisation> getOrganisationList() {
        return super.findAll();
    }

	public List<Organisation> findRange(Integer from, Integer to) {
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
