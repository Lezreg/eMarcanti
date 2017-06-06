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

import com.marcanti.ecommerce.model.CommandeGroupee;

/**
 *
 * @author lezreg
 */
@Repository
public class CommandeGroupeeDAOImpl extends AbstractGenericDAO<CommandeGroupee> implements CommandeGroupeeDAO {

	@PersistenceContext
    private EntityManager em;

    public CommandeGroupeeDAOImpl() {
        super(CommandeGroupee.class);
    }

    public void create(CommandeGroupee entity) {
        super.create(entity);
    }

	public void edit(Long id, CommandeGroupee entity) {
        super.edit(entity);
    }

	public void remove(Long id) {
        super.remove(super.find(id));
    }

	public CommandeGroupee find(Long id) {
        return super.find(id);
    }

    public List<CommandeGroupee> findAll() {
        return super.findAll();
    }

	public List<CommandeGroupee> findRange(Integer from, Integer to) {
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
