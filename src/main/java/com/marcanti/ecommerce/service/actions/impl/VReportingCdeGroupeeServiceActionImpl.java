package com.marcanti.ecommerce.service.actions.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcanti.ecommerce.dao.VReportingCdeGroupeeDAO;
import com.marcanti.ecommerce.model.VReportingCdeGroupee;
import com.marcanti.ecommerce.service.actions.VReportingCdeGroupeeServiceAction;


public class VReportingCdeGroupeeServiceActionImpl implements VReportingCdeGroupeeServiceAction {


	@Autowired
	private VReportingCdeGroupeeDAO reportingCdeGroupeeDAO;
	

	public VReportingCdeGroupeeDAO getReportingCdeGroupeeDAO() {
		return reportingCdeGroupeeDAO;
	}

	public void setReportingCdeGroupeeDAO(VReportingCdeGroupeeDAO reportingCdeGroupeeDAO) {
		this.reportingCdeGroupeeDAO = reportingCdeGroupeeDAO;
	}


	@Override
	public List<VReportingCdeGroupee> getReportingCdeGroupeeList() {
		return reportingCdeGroupeeDAO.getReportingCdeGroupeeList();
	}

	@Override
	public List<VReportingCdeGroupee> searchReportingCdeGroupeeList(Date dateDebut, Date dateFin, String idStatus, String isPaiementEffectue) {
		return reportingCdeGroupeeDAO.searchReportingCdeGroupeeList(dateDebut,dateFin,idStatus,isPaiementEffectue);
	}	
	
	
 




}
