/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.PathParam;

import org.springframework.stereotype.Repository;

import com.marcanti.ecommerce.model.CommandeIndividuelleStatus;

/**
 *
 * @author lezreg
 */
@Repository
public class CommandeIndividuelleStatusDAOImpl extends AbstractGenericDAO<CommandeIndividuelleStatus>
		implements CommandeIndividuelleStatusDAO {

	@PersistenceContext
    private EntityManager em;

    public CommandeIndividuelleStatusDAOImpl() {
        super(CommandeIndividuelleStatus.class);
    }

    @Override
    public void create(CommandeIndividuelleStatus entity) {
        super.create(entity);
    }

    public void edit(@PathParam("id") Short id, CommandeIndividuelleStatus entity) {
        super.edit(entity);
    }

    public void remove(@PathParam("id") Short id) {
        super.remove(super.find(id));
    }

    public CommandeIndividuelleStatus find(@PathParam("id") Short id) {
        return super.find(id);
    }

	@Override
    public List<CommandeIndividuelleStatus> findAll() {
        return super.findAll();
    }

    public List<CommandeIndividuelleStatus> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
	public CommandeIndividuelleStatus getCommandeIndividuelleStatusByCode(String statusCode) {
		return (CommandeIndividuelleStatus) em.createNamedQuery("CommandeIndividuelleStatus.findByStatusCode")
				.setParameter("statusCode", statusCode).getSingleResult();
	}
    
}
