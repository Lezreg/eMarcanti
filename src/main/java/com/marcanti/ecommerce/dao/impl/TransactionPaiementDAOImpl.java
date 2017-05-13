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
import com.marcanti.ecommerce.dao.TransactionPaiementDAO;
import com.marcanti.ecommerce.model.TransactionPaiement;

/**
 *
 * @author lezreg
 */

public class TransactionPaiementDAOImpl extends AbstractGenericDAO<TransactionPaiement>
		implements TransactionPaiementDAO {

	@PersistenceContext
    private EntityManager em;

    public TransactionPaiementDAOImpl() {
        super(TransactionPaiement.class);
    }

    public void create(TransactionPaiement entity) {
        super.create(entity);
    }

	public void edit(String id, TransactionPaiement entity) {
        super.edit(entity);
    }

	public void remove(String id) {
        super.remove(super.find(id));
    }

	public TransactionPaiement find(String id) {
        return super.find(id);
    }

    public List<TransactionPaiement> findAll() {
        return super.findAll();
    }

	public List<TransactionPaiement> findRange(Integer from, Integer to) {
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
