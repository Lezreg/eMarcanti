/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.marcanti.ecommerce.dao.AbstractGenericDAO;
import com.marcanti.ecommerce.dao.DepartementDAO;
import com.marcanti.ecommerce.model.Departement;
import com.marcanti.ecommerce.model.Organisation;

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
    
	@Override
	public List<Departement> getDepartementList() {
		return em.createNamedQuery("Departement.findAll", Departement.class).getResultList();
	}

	@Override
	public Departement getDepartement(Departement departement) {
		return em.createNamedQuery("Departement.findByIdDepartement", Departement.class).setParameter("idDepartement", departement.getIdDepartement()).getSingleResult();
	}

	@Override
	public void insertDepartement(Departement departement) {
		Query query = em.createNativeQuery("INSERT INTO departement (idOrga, departementNom, isActif, commentaire) VALUES (?,?,?,?)")
				.setParameter(1, departement.getIdOrga().getIdOrga())
				.setParameter(2, departement.getDepartementNom())
				.setParameter(3, departement.getIsActif())
				.setParameter(4, departement.getCommentaire());
		query.executeUpdate();
		//em.persist(departement);
	}

	@Override
	public void updateDepartement(Departement departement) {
		Query query = em.createNativeQuery("UPDATE departement SET idOrga=?, "
				+ "departementNom=?, "
				+ "isActif=?, "
				+ "commentaire=? "
				+ "WHERE idDepartement=?")
				.setParameter(1, departement.getIdOrga().getIdOrga())
				.setParameter(2, departement.getDepartementNom())
				.setParameter(3, departement.getIsActif())
				.setParameter(4, departement.getCommentaire())
				.setParameter(5, departement.getIdDepartement());
		query.executeUpdate();
		//em.persist(departement);
	}

	@Override
	public List<Departement> getDepartementByOrgaList(Organisation idOrga) {
		List<Departement> departementList =  em.createNamedQuery("Departement.findByIdOrga", Departement.class).setParameter("idOrga", idOrga).getResultList();
		return departementList;
	}
    
}
