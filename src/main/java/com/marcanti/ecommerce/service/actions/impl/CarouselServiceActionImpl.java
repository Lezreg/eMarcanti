package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcanti.ecommerce.dao.CarouselDAO;
import com.marcanti.ecommerce.model.Carousel;
import com.marcanti.ecommerce.service.actions.CarouselServiceAction;


public class CarouselServiceActionImpl implements CarouselServiceAction {


	@Autowired
	private CarouselDAO carouselDAO;	
 
	public CarouselDAO getCarouselDAO() {
		return carouselDAO;
	}

	public void setCarouselDAO(CarouselDAO carouselDAO) {
		this.carouselDAO = carouselDAO;
	}

	@Override
	public List<Carousel> getCarouselList() {
		return carouselDAO.getCarouselList();
	}

	@Override
	public Carousel getCarousel(Carousel carousel) {
		return carouselDAO.getCarousel(carousel);
	}

	@Override
	public void updateCarousel(Carousel carousel) {
		carouselDAO.updateCarousel(carousel);
	}

	@Override
	public void insertCarousel(Carousel carousel) {
		carouselDAO.insertCarousel(carousel);
	}

	@Override
	public boolean isRangExist(Carousel carousel) {
		return carouselDAO.isRangExist(carousel);
	}
	
}
