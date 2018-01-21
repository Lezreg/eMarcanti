package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.Carousel;
import com.marcanti.ecommerce.service.actions.CarouselServiceAction;

@ManagedBean(name = "carouselIndexBean")
@SessionScoped
public class CarouselIndexBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(CarouselIndexBean.class);

	private List<Carousel> carouselList;
	

	@ManagedProperty("#{carouselService}")
	private CarouselServiceAction carouselService;
	

	public List<Carousel> getCarouselList() {
		return carouselList;
	}

	public void setCarouselList(List<Carousel> carouselList) {
		this.carouselList = carouselList;
	}

	public CarouselServiceAction getCarouselService() {
		return carouselService;
	}

	public void setCarouselService(CarouselServiceAction carouselService) {
		this.carouselService = carouselService;
	}

	public CarouselIndexBean() {
	}

	@PostConstruct
	public void init() {
		this.carouselList = carouselService.getCarouselListView();
	}
	

}
