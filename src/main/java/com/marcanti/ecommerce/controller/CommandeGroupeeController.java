package com.marcanti.ecommerce.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
import com.marcanti.ecommerce.view.bean.ReferentielBean;
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

	@ManagedProperty("#{referentielBean}")
	private ReferentielBean referentielBean;

	private CommandeGroupee commandeGroupee;

	private List<CommandeGroupee> cmdGroupees;

	private List<CommandeGroupee> cmdGroupeesPrec;

	private List<CommandeGroupee> cmdGroupeesFiltred;

	private UserSessionBean userSessionBean;

	private Organisation organisation;

	private Long organisationId;

	private List<com.marcanti.ecommerce.model.CommandeGroupeeStatus> commandeGroupeeStatus;

	private List<Organisation> organisations;

	private Locale locale;

	private String StatusCode;

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);

		// commandeGroupee = new CommandeGroupee();
		userSessionBean = ParfumUtils.getUserSessionBean();
		organisation = organisationServiceAction.getOrganisationById(userSessionBean.getIdOrga());
		locale = Locale.FRANCE;
	}

	public String createNew() {
		this.commandeGroupee = newCommandeGroupee();
		commandeGroupee.setDateCreation(new Date());

		return "createCmd";
	}

	public String saveCmdGroupee() {
		LOGGER.info("********************save and update methode ******************************");

		// create new commande
		if (commandeGroupee != null && commandeGroupee.getIdCdeGroupee() == null) {
			commandeGroupee.setCdeGroupeeNom(getCommandeGroupeName());
			commandeGroupee.setNomCreateur(userSessionBean.getMembreNom().toUpperCase());
			commandeGroupee.setPrenomCreateur(userSessionBean.getMembrePrenom());
			commandeGroupee.setDateCreation(new Date());
			commandeGroupee.setIdOrga(organisation);
			commandeGroupee.setIsPaiementEffectue(false);
			commandeGroupee.setIsEnCours(true);
			commandeGroupee.setIdStatus(commandeGroupeeServiceAction
					.getCommandeGroupeeStatusByCode(CommandeGroupeeStatus.CDE_GROUPEE_A_LIVRER.getCode()));
			// update exist command
		} else {
			commandeGroupee.setDateModification(new Date());
			commandeGroupee.setNomModifieur(userSessionBean.getMembreNom().toUpperCase());
			commandeGroupee.setPrenomModifieur(userSessionBean.getMembrePrenom());
			commandeGroupee.getIdStatus();
			LOGGER.info(commandeGroupee.getIdStatus().toString());
			// FIXME date creation, n'est pas date du jour lors de mise à jour
			// problème jsf ou hibernate à resoudre
			commandeGroupee.setDateCreation(new Date());
			// commandeGroupee.setIdStatus(commandeGroupeeServiceAction
			// .);

			commandeGroupee.setIdStatus(commandeGroupeeServiceAction.getCommandeGroupeeStatusByCode(StatusCode));
		}
		commandeGroupeeServiceAction.saveCmdGroupee(commandeGroupee);
		return "cmdGroupees";
	}

	private String getCommandeGroupeName() {
		Date date = new Date();
		String monthName = DateUtils.getMonthName(DateUtils.getMonth(date), Locale.FRANCE);
		return organisation.getOrgaAlias() + "_" + DateUtils.getYear(date) + "_"
				+ monthName.substring(0, 3).toUpperCase() + "_" + DateUtils.getDay(date);
	}

	public String editCommandeGroupee() {
		/**
		 * 
		 */
		return "commandeGroupee";
	}

	public String showCommandeGroupee() {
		/**
		 * 
		 */
		return "showCommandeGroupee";
	}

	public List<CommandeGroupee> getCmdGroupees() {
		// default value (pour selected by Admin)
		Long idOrga = organisationId;
		// else organisation of current user
		if (idOrga == null || idOrga == 0) {
			idOrga = userSessionBean.getIdOrga();
		}
		return commandeGroupeeServiceAction.getCmdGroupeesByOrganisation(idOrga, true);
	}

	public List<CommandeGroupee> getCmdGroupeesPrec() {
		// default value (pour selected by Admin)
		Long idOrga = organisationId;
		// else organisation of current user
		if (idOrga == null || idOrga == 0) {
			idOrga = userSessionBean.getIdOrga();
		}
		return commandeGroupeeServiceAction.getCmdGroupeesByOrganisation(idOrga, false);
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

	public List<com.marcanti.ecommerce.model.CommandeGroupeeStatus> getCommandeGroupeeStatus() {
		return commandeGroupeeServiceAction.getAllCommandeGroupeeStatus();
	}

	public void setCommandeGroupeeStatus(
			List<com.marcanti.ecommerce.model.CommandeGroupeeStatus> commandeGroupeeStatus) {
		this.commandeGroupeeStatus = commandeGroupeeStatus;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public ReferentielBean getReferentielBean() {
		return referentielBean;
	}

	public void setReferentielBean(ReferentielBean referentielBean) {
		this.referentielBean = referentielBean;
	}

	public String getStatusCode() {
		return StatusCode;
	}

	public void setStatusCode(String statusCode) {
		StatusCode = statusCode;
	}

}
