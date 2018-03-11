/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao.impl;

import java.util.ArrayList;
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
import com.marcanti.ecommerce.model.Marque;

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
		/*List<Carousel> listCarousel = new ArrayList<Carousel>();
		Carousel carousel;
		Query query = em.createNativeQuery("select c.idCarousel, "
				+ "c.idMarque, "
				+ "c.elementNom, "
				+ "c.elementImageURL, "
				+ "c.elementLienURL, "
				+ "c.elementRang, "
				+ "c.isVisible, "
				+ "m.marqueNom, "
				+ "c.produitPrix, "
				+ "c.produitNom, "
				+ "c.produitSousTitre, "
				+ "c.boutonLibelle from carousel c, marque m where c.idMarque=m.idMarque");
		List<Object[]> resuList = query.getResultList();
		for (Object[] entity : resuList) {
			carousel = new Carousel();
			carousel.setIdCarousel((Integer)(entity[0]));
			carousel.setIdMarque(new Marque(((Short)(entity[1])).shortValue()));
			carousel.setElementNom((String)(entity[2]));
			carousel.setElementImageURL((String)entity[3]);
			carousel.setElementLienURL((String)entity[4]);
			carousel.setElementRang(((Short)(entity[5])).shortValue());
			carousel.setIsVisible(((Boolean)(entity[6])).booleanValue());
			carousel.setProduitMarque((String)entity[7]);
			carousel.setProduitPrix((String)entity[8]);
			carousel.setProduitNom((String)entity[9]);
			carousel.setProduitSousTitre((String)entity[10]);
			carousel.setBoutonLibelle((String)entity[11]);
			listCarousel.add(carousel);
		}
		return listCarousel;*/
	}
	
	@Override
	public List<Carousel> getCarouselListView() {
		//return em.createNamedQuery("Carousel.findByIsVisibleOrderBy", Carousel.class).setParameter("isVisible", true).getResultList();
		List<Carousel> listCarousel = new ArrayList<Carousel>();
		Carousel carousel;
		Query query = em.createNativeQuery("select c.idCarousel, "
				+ "c.idMarque, "
				+ "c.elementNom, "
				+ "c.elementImageURL, "
				+ "c.elementLienURL, "
				+ "c.elementRang, "
				+ "c.isVisible, "
				+ "m.marqueNom, "
				+ "c.produitPrix, "
				+ "c.produitNom, "
				+ "c.produitSousTitre, "
				+ "c.boutonLibelle from carousel c, marque m where c.idMarque=m.idMarque and c.isVisible=1 ORDER BY elementRang asc");
		List<Object[]> resuList = query.getResultList();
		for (Object[] entity : resuList) {
			carousel = new Carousel();
			carousel.setIdCarousel((Integer)(entity[0]));
			carousel.setIdMarque(new Marque(((Short)(entity[1])).shortValue()));
			carousel.setElementNom((String)(entity[2]));
			carousel.setElementImageURL((String)entity[3]);
			carousel.setElementLienURL((String)entity[4]);
			carousel.setElementRang(((Short)(entity[5])).shortValue());
			carousel.setIsVisible(((Boolean)(entity[6])).booleanValue());
			carousel.setProduitMarque((String)entity[7]);
			carousel.setProduitPrix((String)entity[8]);
			carousel.setProduitNom((String)entity[9]);
			carousel.setProduitSousTitre((String)entity[10]);
			carousel.setBoutonLibelle((String)entity[11]);
			listCarousel.add(carousel);
		}
		return listCarousel;
	}	

	@Override
	public Carousel getCarousel(Carousel carousel) {
		return em.createNamedQuery("Carousel.findByIdCarousel", Carousel.class).setParameter("idCarousel", carousel.getIdCarousel()).getSingleResult();
	}

	@Override
	public void insertCarousel(Carousel carousel) {
		//String produitMarque = (String)em.createNativeQuery("select marqueNom from marque where idMarque=? ").setParameter(1, carousel.getIdMarque()).getSingleResult();
		Query query = em.createNativeQuery("INSERT INTO carousel (idMarque, elementNom, elementImageURL, elementLienURL, elementRang, isVisible, produitPrix, produitNom, produitSousTitre, boutonLibelle) VALUES (?,?,?,?,?,?,?,?,?,?)")
				.setParameter(1, carousel.getIdMarque().getIdMarque())
				.setParameter(2, carousel.getElementNom())
				.setParameter(3, carousel.getElementImageURL())
				.setParameter(4, carousel.getElementLienURL())
				.setParameter(5, carousel.getElementRang())
				.setParameter(6, carousel.getIsVisible())
				.setParameter(7, carousel.getProduitPrix())
				.setParameter(8, carousel.getProduitNom())
				.setParameter(9, carousel.getProduitSousTitre())
				.setParameter(10, carousel.getBoutonLibelle());
		query.executeUpdate();
		//em.persist(carousel);
		
	}

	@Override
	public void updateCarousel(Carousel carousel) {
		//String produitMarque = (String)em.createNativeQuery("select marqueNom from marque where idMarque=? ").setParameter(1, carousel.getIdMarque()).getSingleResult();
		Query query = em.createNativeQuery("UPDATE carousel SET "
				+ "idMarque=?, "
				+ "elementNom=?, "
				+ "elementImageURL=?, "
				+ "elementLienURL=?, "
				+ "elementRang=?, "
				+ "isVisible=?, "
				+ "produitPrix=?, "
				+ "produitNom=?, "
				+ "produitSousTitre=?, "
				+ "boutonLibelle=? "
				+ "WHERE idCarousel=?")
				.setParameter(1, carousel.getIdMarque().getIdMarque())
				.setParameter(2, carousel.getElementNom())
				.setParameter(3, carousel.getElementImageURL())
				.setParameter(4, carousel.getElementLienURL())
				.setParameter(5, carousel.getElementRang())
				.setParameter(6, carousel.getIsVisible())
				.setParameter(7, carousel.getProduitPrix())
				.setParameter(8, carousel.getProduitNom())
				.setParameter(9, carousel.getProduitSousTitre())
				.setParameter(10, carousel.getBoutonLibelle())
				.setParameter(11, carousel.getIdCarousel());
		query.executeUpdate();
		//em.persist(carousel);
	}
	
	
	@Override
	public boolean isRangExist(Carousel carousel) {
		return !em.createNamedQuery("Carousel.findByElementRang", Carousel.class).setParameter("elementRang", carousel.getElementRang()).getResultList().isEmpty();
	}
    
}
