package com.marcanti.ecommerce.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
import com.marcanti.ecommerce.model.CommandeIndividuelle;
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

	private List<CommandeIndividuelle> selectedCommmandeIndividuelleList;

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

	private boolean canModifyOrganisation;

	private BigDecimal somme;

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);

		userSessionBean = ParfumUtils.getUserSessionBean();
		locale = Locale.FRANCE;
	}

	public String createNew() {
		// checkCommandeGroupee
		List<CommandeGroupee> cmdGroupeesList = commandeGroupeeServiceAction
				.getCmdGroupeesPaiementByOrganisation(getOrganisationId(), Boolean.FALSE);

		if (cmdGroupeesList != null && !cmdGroupeesList.isEmpty()) {
			addFacesMessages(cmdGroupeesList.get(0));
			return "";
		}
		this.commandeGroupee = new CommandeGroupee();
		commandeGroupee.setDateCreation(new Date());
		commandeGroupee.setIdOrga(organisationServiceAction.getOrganisationById(getOrganisationId()));
		commandeGroupee.setCdeGroupeeNom(getCommandeGroupeName());
		return "createCmd";
	}

	private void addFacesMessages(CommandeGroupee currentCommandeGroupee) {

		FacesMessage newCmdMessageWarning = new FacesMessage(FacesMessage.SEVERITY_WARN,
				"La commande précédente : " + currentCommandeGroupee.getCdeGroupeeNom()
						+ " n’a pas été complètement payée !\r\n"
						+ "Vérifier la (ou les) personnes concernée (s) dans le détail. ",
				null);
		FacesContext.getCurrentInstance().addMessage(null, newCmdMessageWarning);
	}

	public String saveCmdGroupee() {
		LOGGER.info("********************save and update methode ******************************");
		// create new commande
		if (commandeGroupee != null && commandeGroupee.getIdCdeGroupee() == null) {
			commandeGroupee.setCdeGroupeeNom(getCommandeGroupeName());
			commandeGroupee.setNomCreateur(userSessionBean.getMembreNom().toUpperCase());
			commandeGroupee.setPrenomCreateur(userSessionBean.getMembrePrenom());
			commandeGroupee.setDateCreation(new Date());
			commandeGroupee.setIdOrga(getOrganisation());
			commandeGroupee.setIsPaiementEffectue(false);
			commandeGroupee.setIsEnCours(true);
			commandeGroupee.setIdStatus(commandeGroupeeServiceAction
					.getCommandeGroupeeStatusByCode(CommandeGroupeeStatus.CDE_GROUPEE_NON_CONFIRMEE.getCode()));
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
			commandeGroupee.setIdStatus(commandeGroupeeServiceAction.getCommandeGroupeeStatusByCode(StatusCode));
		}
		commandeGroupeeServiceAction.saveCmdGroupee(commandeGroupee);

		return "cmdGroupees";
	}

	public String annulerCmdGroupee() {
		if (commandeGroupee.getIdCdeGroupee() != null) {
			commandeGroupeeServiceAction.annulerCmdGroupee(commandeGroupee);
		}
		return "cmdGroupees";
	}

	private String getCommandeGroupeName() {
		Date date = new Date();
		String monthName = DateUtils.getMonthName(DateUtils.getMonth(date), Locale.FRANCE);
		return getOrganisation().getOrgaAlias() + "_" + DateUtils.getStringDay(date) + "_"
				+ monthName.substring(0, 3).toUpperCase() + "_" + DateUtils.getYear(date);
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
		return commandeGroupeeServiceAction.getCmdGroupeesByOrganisation(getOrganisationId(), true);
	}

	public List<CommandeGroupee> getCmdGroupeesPrec() {
		// else organisation of current user
		return commandeGroupeeServiceAction.getCmdGroupeesByOrganisation(getOrganisationId(), false);
	}

	private CommandeGroupee newCommandeGroupee() {
		CommandeGroupee commandeGroupee = new CommandeGroupee();
		Organisation organisation = organisationServiceAction.getOrganisationById(getOrganisationId());
		commandeGroupee.setIdOrga(organisation);
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
		return organisationServiceAction.getOrganisationById(getOrganisationId());
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
		if (organisationId == null || organisationId == 0) {
			organisationId = userSessionBean.getIdOrga();
		}
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

	public boolean isCanModifyOrganisation() {
		if ((userSessionBean.getIdProfil() < 4)) {
			canModifyOrganisation = true;
		}
		return canModifyOrganisation;
	}

	public void setCanModifyOrganisation(boolean canModifyOrganisation) {
		this.canModifyOrganisation = canModifyOrganisation;
	}

	public List<CommandeIndividuelle> getSelectedCommmandeIndividuelleList() {
		List<CommandeIndividuelle> CommandeIndividuelleList = commandeGroupeeServiceAction
				.getCommandeIndividuelleListByGroupee(commandeGroupee);
		somme = new BigDecimal(0);
		for (CommandeIndividuelle commandeIndividuelle : CommandeIndividuelleList) {
			somme = somme.add(commandeIndividuelle.getTotalAPayer());
		}

		commandeGroupee.setSommeCommandeGroupee(somme);
		return CommandeIndividuelleList;
	}

	public void setSelectedCommmandeIndividuelleList(List<CommandeIndividuelle> selectedCommmandeIndividuelleList) {
		this.selectedCommmandeIndividuelleList = selectedCommmandeIndividuelleList;
	}

}
