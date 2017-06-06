/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.marcanti.ecommerce.model.Carousel;

/**
 *
 * @author lezreg
 */
@Repository
public class CarouselDAOImpl extends AbstractGenericDAO<Carousel> implements CarouselDAO {

	private static final Logger logger = LoggerFactory.getLogger(CarouselDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

    public CarouselDAOImpl() {
        super(Carousel.class);
    }

    public void create(Carousel entity) {
        super.create(entity);
    }

	public void edit(Integer id, Carousel entity) {
        super.edit(entity);
    }

	public void remove(Integer id) {
        super.remove(super.find(id));
    }

	public Carousel find(Integer id) {
        return super.find(id);
    }

    public List<Carousel> findAll() {
        return super.findAll();
    }

	public List<Carousel> findRange(Integer from, Integer to) {
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
