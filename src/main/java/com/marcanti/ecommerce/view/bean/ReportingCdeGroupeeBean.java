package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.VReportingCdeGroupee;
import com.marcanti.ecommerce.service.actions.VReportingCdeGroupeeServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "reportingCdeGroupeeBean")
@SessionScoped
public class ReportingCdeGroupeeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(ReportingCdeGroupeeBean.class);

	private List<VReportingCdeGroupee> reportingCdeGroupeeList;
	
	private VReportingCdeGroupee reportingCdeGroupee;
	
	private Long idCdeGroupee;	
	
	private String idStatus;
	
	private String isPaid;
	
	private Date dateDebut;
	
	private Date dateFin;
	
	@ManagedProperty("#{reportingCdeGroupeeService}")
	private VReportingCdeGroupeeServiceAction reportingCdeGroupeeService;
	
	@ManagedProperty("#{referentielBean}")
	private ReferentielBean referentielBean;	
	
	public ReportingCdeGroupeeBean() {
	}

	@PostConstruct
	public void init() {
		//this.reportingCdeGroupeeList = reportingCdeGroupeeService.getReportingCdeGroupeeList();
		setIdStatus("4");
	}

	public List<VReportingCdeGroupee> getReportingCdeGroupeeList() {
		return reportingCdeGroupeeList;
	}

	public void setReportingCdeGroupeeList(List<VReportingCdeGroupee> reportingCdeGroupeeList) {
		this.reportingCdeGroupeeList = reportingCdeGroupeeList;
	}

	public VReportingCdeGroupee getReportingCdeGroupee() {
		return reportingCdeGroupee;
	}

	public void setReportingCdeGroupee(VReportingCdeGroupee reportingCdeGroupee) {
		this.reportingCdeGroupee = reportingCdeGroupee;
	}

	public Long getIdCdeGroupee() {
		return idCdeGroupee;
	}

	public void setIdCdeGroupee(Long idCdeGroupee) {
		this.idCdeGroupee = idCdeGroupee;
	}
	
	public String getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}
	
	public String getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(String isPaid) {
		this.isPaid = isPaid;
	}
	
	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public VReportingCdeGroupeeServiceAction getReportingCdeGroupeeService() {
		return reportingCdeGroupeeService;
	}

	public void setReportingCdeGroupeeService(VReportingCdeGroupeeServiceAction reportingCdeGroupeeService) {
		this.reportingCdeGroupeeService = reportingCdeGroupeeService;
	}

	public ReferentielBean getReferentielBean() {
		return referentielBean;
	}

	public void setReferentielBean(ReferentielBean referentielBean) {
		this.referentielBean = referentielBean;
	}

	public String editCdeGroupee() {
		
		Date toDay = new Date();
		Date dateDebutCritere;
		Date dateFinCritere;
		FacesMessage facesMessage = new FacesMessage();
		String msg;
		
		if(getDateDebut()!=null && getDateDebut().after(toDay)){
			msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_date_deb_ant");
			facesMessage.setSummary(msg); 
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		
		if(getDateFin()!=null && getDateFin().after(toDay)){
			msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_date_dfin_ant");
			facesMessage.setSummary(msg); 
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		
		if(getDateDebut()!=null && getDateFin()!=null){
			if(getDateFin().before(getDateDebut())){
				msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_date_deb_fin");
				facesMessage.setSummary(msg); 
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			    return "reporting1";
			}
		}
		
		Calendar c = new GregorianCalendar();
		c.setTime(toDay);
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		
		if(getDateDebut()==null || getDateDebut().equals("")){
			dateDebutCritere = c.getTime();
		}else{
			dateDebutCritere=getDateDebut();
		}
		if(getDateFin()==null || getDateFin().equals("")){
			dateFinCritere = toDay;
		}else{
			dateFinCritere=getDateFin();
		}		
		
		this.reportingCdeGroupeeList = reportingCdeGroupeeService.searchReportingCdeGroupeeList(dateDebutCritere,dateFinCritere,getIdStatus(),getIsPaid());
		return "reporting1";

	}
	
	public static void main(String[] args) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
		Date toDay = new Date();
		System.out.println(dateFormat.format(toDay)); //2016/11/16 12:08:43
		
		Calendar c = new GregorianCalendar();
		c.setTime(toDay);
		System.out.println(dateFormat.format(c.getTime()));   // NOW
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(dateFormat1.format(c.getTime()));   // One month ago
		
	}
	
	
}
