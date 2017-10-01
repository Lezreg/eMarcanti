package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.VCatalogueAdmin;
import com.marcanti.ecommerce.service.actions.VCatalogueAdminServiceAction;

@ManagedBean(name = "catalogueBean")
@RequestScoped
public class VCatalogueAdminBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(VCatalogueAdminBean.class);

	private List<VCatalogueAdmin> catalogueList;

	private List<VCatalogueAdmin> filteredCatalogueList;
	
	private VCatalogueAdmin catalogue;
	
	
	@ManagedProperty("#{catalogueService}")
	private VCatalogueAdminServiceAction catalogueService;
	
	public VCatalogueAdminBean() {
	}

	@PostConstruct
	public void init() {
		this.catalogueList = catalogueService.getCatalogueList();
		System.out.println("size : " + catalogueList.size());
	}

	public List<VCatalogueAdmin> getCatalogueList() {
		return catalogueList;
	}

	public void setCatalogueList(List<VCatalogueAdmin> catalogueList) {
		this.catalogueList = catalogueList;
	}

	public List<VCatalogueAdmin> getFilteredCatalogueList() {
		return filteredCatalogueList;
	}

	public void setFilteredCatalogueList(List<VCatalogueAdmin> filteredCatalogueList) {
		this.filteredCatalogueList = filteredCatalogueList;
	}

	public VCatalogueAdmin getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(VCatalogueAdmin catalogue) {
		this.catalogue = catalogue;
	}

	public VCatalogueAdminServiceAction getCatalogueService() {
		return catalogueService;
	}

	public void setCatalogueService(VCatalogueAdminServiceAction catalogueService) {
		this.catalogueService = catalogueService;
	}

	

}
