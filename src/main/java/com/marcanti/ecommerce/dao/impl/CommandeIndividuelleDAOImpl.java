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
import com.marcanti.ecommerce.dao.CommandeIndividuelleDAO;
import com.marcanti.ecommerce.model.CommandeIndividuelle;

/**
 *
 * @author lezreg
 */
@Repository
public class CommandeIndividuelleDAOImpl extends AbstractGenericDAO<CommandeIndividuelle>
		implements CommandeIndividuelleDAO {

	@PersistenceContext
    private EntityManager em;

    public CommandeIndividuelleDAOImpl() {
        super(CommandeIndividuelle.class);
    }

    public void create(CommandeIndividuelle entity) {
        super.create(entity);
    }

	public void edit(Long id, CommandeIndividuelle entity) {
        super.edit(entity);
    }

	public void remove(Long id) {
        super.remove(super.find(id));
    }

	public CommandeIndividuelle find(Long id) {
        return super.find(id);
    }

    public List<CommandeIndividuelle> findAll() {
        return super.findAll();
    }

	public List<CommandeIndividuelle> findRange(Integer from, Integer to) {
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
