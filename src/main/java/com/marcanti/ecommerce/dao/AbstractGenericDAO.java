/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Transactional
public abstract class AbstractGenericDAO<T> implements GenericDAO<T> {

    private Class<T> entityClass;

    public AbstractGenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

	/* (non-Javadoc)
	 * @see com.marcanti.ecommerce.dao.Test#create(T)
	 */
	@Override
	@Transactional
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    /* (non-Javadoc)
	 * @see com.marcanti.ecommerce.dao.Test#edit(T)
	 */
    @Override
	public T edit(T entity) {
		return getEntityManager().merge(entity);
    }

    /* (non-Javadoc)
	 * @see com.marcanti.ecommerce.dao.Test#remove(T)
	 */
    @Override
	public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /* (non-Javadoc)
	 * @see com.marcanti.ecommerce.dao.Test#find(java.lang.Object)
	 */
    @Override
	public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /* (non-Javadoc)
	 * @see com.marcanti.ecommerce.dao.Test#findAll()
	 */
    @Override
	public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /* (non-Javadoc)
	 * @see com.marcanti.ecommerce.dao.Test#findRange(int[])
	 */
    @Override
	public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /* (non-Javadoc)
	 * @see com.marcanti.ecommerce.dao.Test#count()
	 */
    @Override
	public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
