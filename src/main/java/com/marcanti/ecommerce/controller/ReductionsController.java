package com.marcanti.ecommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.VReduction;
import com.marcanti.ecommerce.service.actions.CommandeGroupeeServiceAction;
import com.marcanti.ecommerce.service.actions.OrganisationServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

@ManagedBean(name = "reductionView")
@SessionScoped
public class ReductionsController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2321140781284045514L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReductionsController.class);

	@Autowired
	private CommandeGroupeeServiceAction commandeGroupeeServiceAction;
	@Autowired
	private OrganisationServiceAction organisationServiceAction;

	private List<VReduction> reductions;

	private VReduction reductionSelected;

	private List<VReduction> reductionsFiltred;

	private UserSessionBean userSessionBean;

	private Organisation organisation;

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
		userSessionBean = ParfumUtils.getUserSessionBean();
		organisation = organisationServiceAction.getOrganisationById(userSessionBean.getIdOrga());
	}

	public void valider() {
		LOGGER.info("----------------------------------valider");
	}

	public CommandeGroupeeServiceAction getCommandeGroupeeServiceAction() {
		return commandeGroupeeServiceAction;
	}

	public OrganisationServiceAction getOrganisationServiceAction() {
		return organisationServiceAction;
	}

	public void setOrganisationServiceAction(OrganisationServiceAction organisationServiceAction) {
		this.organisationServiceAction = organisationServiceAction;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public List<VReduction> getReductionsFiltred() {
		return reductionsFiltred;
	}

	public void setReductionsFiltred(List<VReduction> reductionsFiltred) {
		this.reductionsFiltred = reductionsFiltred;
	}

	public List<VReduction> getReductions() {

		return commandeGroupeeServiceAction.getAllReductions();
	}

	public void setReductions(List<VReduction> reductions) {
		this.reductions = reductions;
	}

	public VReduction getReductionSelected() {
		return reductionSelected;
	}

	public void setReductionSelected(VReduction reductionSelected) {
		this.reductionSelected = reductionSelected;
	}

}
