package com.marcanti.ecommerce.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.marcanti.ecommerce.constants.CommandeGroupeeStatus;
import com.marcanti.ecommerce.model.CommandeGroupee;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.service.actions.CommandeGroupeeServiceAction;
import com.marcanti.ecommerce.service.actions.OrganisationServiceAction;
import com.marcanti.ecommerce.utils.DateUtils;
import com.marcanti.ecommerce.utils.ParfumUtils;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

@ManagedBean(name = "cmdGroupeeView")
@SessionScoped
public class CommandeGroupeeController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2321140781284045514L;

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandeGroupeeController.class);

	@Autowired
	private CommandeGroupeeServiceAction commandeGroupeeServiceAction;
	@Autowired
	private OrganisationServiceAction organisationServiceAction;

	private CommandeGroupee commandeGroupee;

	private List<CommandeGroupee> cmdGroupees;

	private List<CommandeGroupee> cmdGroupeesPrec;

	private List<CommandeGroupee> cmdGroupeesFiltred;

	private UserSessionBean userSessionBean;

	private Organisation organisation;

	private Long organisationId;

	private List<Organisation> organisations;

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);

		commandeGroupee = new CommandeGroupee();
		userSessionBean = ParfumUtils.getUserSessionBean();
		organisation = organisationServiceAction.getOrganisationById(userSessionBean.getIdOrga());
	}

	public String createNew() {
		this.commandeGroupee = newCommandeGroupee();
		return "createCmd";
	}

	public String saveCmdGroupee() {
		LOGGER.info("********************save and update methode ******************************");
		Date date = new Date();
		// create new commande
		if (commandeGroupee != null && commandeGroupee.getIdCdeGroupee() == null) {
			String monthName = DateUtils.getMonthName(DateUtils.getMonth(date), Locale.FRANCE);
			commandeGroupee.setCdeGroupeeNom(organisation.getOrgaAlias() + "_" + DateUtils.getYear(date) + "_"
					+ monthName.substring(0, 3).toUpperCase() + "_" + DateUtils.getDay(date));
			commandeGroupee.setNomCreateur(userSessionBean.getMembreNom().toUpperCase());
			commandeGroupee.setPrenomCreateur(userSessionBean.getMembrePrenom());
			commandeGroupee.setDateCreation(date);
			commandeGroupee.setIdOrga(organisation);
			commandeGroupee.setIsPaiementEffectue(false);
			commandeGroupee.setIsEnCours(true);
			com.marcanti.ecommerce.model.CommandeGroupeeStatus commandeGroupeeStatus = commandeGroupeeServiceAction
					.getCommandeGroupeeStatusByCode(CommandeGroupeeStatus.CDE_GROUPEE_A_LIVRER.getCode());
			commandeGroupee.setIdStatus(commandeGroupeeStatus);
			// update exist command
		} else {
			commandeGroupee.setDateModification(date);
			commandeGroupee.setNomModifieur(userSessionBean.getMembreNom().toUpperCase());
			commandeGroupee.setPrenomModifieur(userSessionBean.getMembrePrenom());
		}
		commandeGroupeeServiceAction.saveCmdGroupee(commandeGroupee);
		return "cmdGroupees";
	}

	private CommandeGroupee newCommandeGroupee() {
		CommandeGroupee commandeGroupee = new CommandeGroupee();
		return commandeGroupee;
	}

	public CommandeGroupeeServiceAction getCommandeGroupeeServiceAction() {
		return commandeGroupeeServiceAction;
	}

	public void setCommandeGroupeeServiceAction(CommandeGroupeeServiceAction commandeGroupeeServiceAction) {
		this.commandeGroupeeServiceAction = commandeGroupeeServiceAction;
	}

	public CommandeGroupee getCommandeGroupee() {
		return commandeGroupee;
	}

	public void setCommandeGroupee(CommandeGroupee commandeGroupee) {
		this.commandeGroupee = commandeGroupee;
	}

	public List<CommandeGroupee> getCmdGroupees() {
		return commandeGroupeeServiceAction.getCmdGroupeesByOrganisation(userSessionBean.getIdOrga(), false);
	}

	public List<CommandeGroupee> getCmdGroupeesPrec() {
		return commandeGroupeeServiceAction.getCmdGroupeesByOrganisation(userSessionBean.getIdOrga(), true);
	}

	public void setCmdGroupees(List<CommandeGroupee> cmdGroupees) {
		this.cmdGroupees = cmdGroupees;
	}

	public List<CommandeGroupee> getCmdGroupeesFiltred() {
		return cmdGroupeesFiltred;
	}

	public void setCmdGroupeesFiltred(List<CommandeGroupee> cmdGroupeesFiltred) {
		this.cmdGroupeesFiltred = cmdGroupeesFiltred;
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

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public List<Organisation> getOrganisations() {
		return organisationServiceAction.getOrganisationList();
	}

	public void setOrganisations(List<Organisation> organisations) {
		this.organisations = organisations;
	}

	public Long getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Long organisationId) {
		this.organisationId = organisationId;
	}
}
