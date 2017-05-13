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
import com.marcanti.ecommerce.dao.CommandeGroupeeStatusDAO;
import com.marcanti.ecommerce.model.CommandeGroupeeStatus;

/**
 *
 * @author lezreg
 */
@Repository
public class CommandeGroupeeStatusDAOImpl extends AbstractGenericDAO<CommandeGroupeeStatus>
		implements CommandeGroupeeStatusDAO {

	@PersistenceContext
    private EntityManager em;

    public CommandeGroupeeStatusDAOImpl() {
        super(CommandeGroupeeStatus.class);
    }

    public void create(CommandeGroupeeStatus entity) {
        super.create(entity);
    }

	public void edit(Short id, CommandeGroupeeStatus entity) {
        super.edit(entity);
    }

	public void remove(Short id) {
        super.remove(super.find(id));
    }

	public CommandeGroupeeStatus find(Short id) {
        return super.find(id);
    }

    public List<CommandeGroupeeStatus> findAll() {
        return super.findAll();
    }

	public List<CommandeGroupeeStatus> findRange(Integer from, Integer to) {
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
