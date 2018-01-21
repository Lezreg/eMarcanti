package com.marcanti.ecommerce.service.actions;

import java.util.List;

import com.marcanti.ecommerce.model.Carousel;

public interface CarouselServiceAction {
 
	public List<Carousel> getCarouselList();
	
	public Carousel getCarousel(Carousel carousel);
	
	public void updateCarousel(Carousel carousel);
	
	public void insertCarousel(Carousel carousel);
	
	public boolean isRangExist(Carousel carousel) ;
	
	public List<Carousel> getCarouselListView();
	
     
}