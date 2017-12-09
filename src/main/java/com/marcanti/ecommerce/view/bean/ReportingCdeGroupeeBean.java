package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.VReportingCdeGroupee;
import com.marcanti.ecommerce.service.actions.VReportingCdeGroupeeServiceAction;

@ManagedBean(name = "reportingCdeGroupeeBean")
@SessionScoped
public class ReportingCdeGroupeeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(ReportingCdeGroupeeBean.class);

	private List<VReportingCdeGroupee> reportingCdeGroupeeList;
	
	private VReportingCdeGroupee reportingCdeGroupee;
	
	private Long idCdeGroupee;	
	
	private Short idStatus;
	
	private Short isPaid;
	
	@ManagedProperty("#{reportingCdeGroupeeService}")
	private VReportingCdeGroupeeServiceAction reportingCdeGroupeeService;
	
	@ManagedProperty("#{referentielBean}")
	private ReferentielBean referentielBean;	
	
	public ReportingCdeGroupeeBean() {
	}

	@PostConstruct
	public void init() {
		//this.reportingCdeGroupeeList = reportingCdeGroupeeService.getReportingCdeGroupeeList();
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
	
	public Short getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Short idStatus) {
		this.idStatus = idStatus;
	}
	
	public Short getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Short isPaid) {
		this.isPaid = isPaid;
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

		return "produit";

	}
	
	
	
	
}
