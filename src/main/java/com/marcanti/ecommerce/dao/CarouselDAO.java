package com.marcanti.ecommerce.dao;

import java.util.List;

import com.marcanti.ecommerce.model.Carousel;

public interface CarouselDAO {
	
	public List<Carousel> getCarouselList();
	
	public Carousel getCarousel(Carousel carousel);
	
	public void insertCarousel(Carousel carousel);
	
	public void updateCarousel(Carousel carousel);
	
	public boolean isRangExist(Carousel carousel);
	
	public List<Carousel> getCarouselListView();

}
