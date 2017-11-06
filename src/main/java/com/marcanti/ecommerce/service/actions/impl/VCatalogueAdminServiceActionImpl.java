package com.marcanti.ecommerce.service.actions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcanti.ecommerce.dao.VCatalogueAdminDAO;
import com.marcanti.ecommerce.model.VCatalogueAdmin;
import com.marcanti.ecommerce.service.actions.VCatalogueAdminServiceAction;


public class VCatalogueAdminServiceActionImpl implements VCatalogueAdminServiceAction {


	@Autowired
	private VCatalogueAdminDAO catalogueDAO;	
 



	public VCatalogueAdminDAO getCatalogueDAO() {
		return catalogueDAO;
	}

	public void setCatalogueDAO(VCatalogueAdminDAO catalogueDAO) {
		this.catalogueDAO = catalogueDAO;
	}


	@Override
	public List<VCatalogueAdmin> getCatalogueList() {
		return catalogueDAO.getCatalogueList();
	}


}
