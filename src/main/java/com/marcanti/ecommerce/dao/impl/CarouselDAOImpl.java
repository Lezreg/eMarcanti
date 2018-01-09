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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.marcanti.ecommerce.dao.AbstractGenericDAO;
import com.marcanti.ecommerce.dao.CarouselDAO;
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

	@Override
	public List<Carousel> getCarouselList() {
		return em.createNamedQuery("Carousel.findAll", Carousel.class).getResultList();
	}

	@Override
	public Carousel getCarousel(Carousel carousel) {
		return em.createNamedQuery("Carousel.findByIdCarousel", Carousel.class).setParameter("idCarousel", carousel.getIdCarousel()).getSingleResult();
	}

	@Override
	public void insertCarousel(Carousel carousel) {
		String produitMarque = (String)em.createNamedQuery("select marqueNom from marque where idMarque=? ").setParameter("idMarque", carousel.getIdMarque()).getSingleResult();
		Query query = em.createNativeQuery("INSERT INTO carousel (elementNom, elementImageURL, elementLienURL, elementRang, isVisible, produitMarque, produitPrix, produitNom, produitSousTitre, boutonLibelle) VALUES (?,?,?,?,?,?,?,?,?,?)")
				.setParameter(1, carousel.getElementNom())
				.setParameter(2, carousel.getElementImageURL())
				.setParameter(3, carousel.getElementLienURL())
				.setParameter(4, carousel.getElementRang())
				.setParameter(5, carousel.getIsVisible())
				.setParameter(6, produitMarque)
				.setParameter(7, carousel.getProduitPrix())
				.setParameter(8, carousel.getProduitNom())
				.setParameter(9, carousel.getProduitSousTitre())
				.setParameter(10, carousel.getBoutonLibelle());
		query.executeUpdate();
		//em.persist(carousel);
		
	}

	@Override
	public void updateCarousel(Carousel carousel) {
		Query query = em.createNativeQuery("UPDATE carousel SET "
				+ "elementNom=?, "
				+ "elementImageURL=?, "
				+ "elementLienURL=?, "
				+ "elementRang=?, "
				+ "isVisible=? "
				+ "WHERE idCarousel=?")
				.setParameter(1, carousel.getElementNom())
				.setParameter(2, carousel.getElementImageURL())
				.setParameter(3, carousel.getElementLienURL())
				.setParameter(4, carousel.getElementRang())
				.setParameter(5, carousel.getIsVisible())
				.setParameter(6, carousel.getIdCarousel());
		query.executeUpdate();
		//em.persist(carousel);
	}
	
	
	@Override
	public boolean isRangExist(Carousel carousel) {
		return !em.createNamedQuery("Carousel.findByElementRang", Carousel.class).setParameter("elementRang", carousel.getElementRang()).getResultList().isEmpty();
	}
    
}
