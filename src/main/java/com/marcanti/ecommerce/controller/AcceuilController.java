package com.marcanti.ecommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.marcanti.ecommerce.beans.ProduitBean;
import com.marcanti.ecommerce.service.actions.ProduitServiceAction;

@ManagedBean(name = "acceuilView")
@SessionScoped
public class AcceuilController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5444941236886680049L;

	private static final Logger LOGGER = LoggerFactory.getLogger(AcceuilController.class);

	@Autowired
	private ProduitServiceAction produitServiceAction;

	/**
	 * 
	 * 
	 */

	private List<ProduitBean> products;

	private String searchChar;

	public ProduitServiceAction getProduitServiceAction() {
		return produitServiceAction;
	}

	public void setProduitServiceAction(ProduitServiceAction produitServiceAction) {
		this.produitServiceAction = produitServiceAction;
	}

	public List<ProduitBean> getProducts() {
		return produitServiceAction.searchProduct(searchChar);
	}

	public void setProducts(List<ProduitBean> products) {
		this.products = products;
	}

}
