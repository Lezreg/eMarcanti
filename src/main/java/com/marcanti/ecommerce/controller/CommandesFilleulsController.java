package com.marcanti.ecommerce.controller;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.marcanti.ecommerce.constants.CommandeIndividuelleStatusEnum;
import com.marcanti.ecommerce.model.VCdeGroupeeDetail;
import com.marcanti.ecommerce.service.actions.CommandeGroupeeServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

@ManagedBean(name = "CmdsFilleulsView")
@ViewScoped
public class CommandesFilleulsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandesFilleulsController.class);

	private List<VCdeGroupeeDetail> cmdeGroupees;

	private List<VCdeGroupeeDetail> cmdeGroupeesPrec;

	private List<VCdeGroupeeDetail> filteredCdeGroupees;

	private VCdeGroupeeDetail selectedCdeGroupee;

	@Autowired
	private CommandeGroupeeServiceAction commandeGroupeeServiceAction;

	UserSessionBean userSessionBean = ParfumUtils.getUserSessionBean();

	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

	public boolean filterByPrice(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
	}

	public String showCmdDetails() {
		LOGGER.info("selectedCdeGroupee:" + selectedCdeGroupee);
		// TODO NAVIGATE RULES
		return null;
	}

	public List<VCdeGroupeeDetail> getFilteredCdeGroupees() {
		return filteredCdeGroupees;
	}

	public void setFilteredCdeGroupees(List<VCdeGroupeeDetail> filteredCdeGroupees) {
		this.filteredCdeGroupees = filteredCdeGroupees;
	}

	public VCdeGroupeeDetail getSelectedCdeGroupee() {
		return selectedCdeGroupee;
	}

	public void setSelectedCdeGroupee(VCdeGroupeeDetail selectedCdeGroupee) {
		this.selectedCdeGroupee = selectedCdeGroupee;
	}

	public CommandeGroupeeServiceAction getCommandeGroupeeServiceAction() {
		return commandeGroupeeServiceAction;
	}

	public void setCommandeGroupeeServiceAction(CommandeGroupeeServiceAction commandeGroupeeServiceAction) {
		this.commandeGroupeeServiceAction = commandeGroupeeServiceAction;
	}

	public List<VCdeGroupeeDetail> getCmdeGroupees() {
		return commandeGroupeeServiceAction.getCommandeGroupeesFilleulsByMembre(userSessionBean.getIdMembre());
	}

	public void setCmdeGroupees(List<VCdeGroupeeDetail> cmdeGroupees) {
		this.cmdeGroupees = cmdeGroupees;
	}

	public List<VCdeGroupeeDetail> getCmdeGroupeesPrec() {
		return commandeGroupeeServiceAction.getCmdGroupeesFilleulsByStatus(userSessionBean.getIdMembre(),
				CommandeIndividuelleStatusEnum.CDE_INDIVID_LIVREE.getCode());
	}

	public void setCmdeGroupeesPrec(List<VCdeGroupeeDetail> cmdeGroupeesPrec) {
		this.cmdeGroupeesPrec = cmdeGroupeesPrec;
	}

}
