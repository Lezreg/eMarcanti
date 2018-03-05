package com.marcanti.ecommerce.dao;

import java.util.Date;
import java.util.List;

import com.marcanti.ecommerce.model.VReportingCdeGroupee;

public interface VReportingCdeGroupeeDAO {
	
	public List<VReportingCdeGroupee> getReportingCdeGroupeeList();
	
	public List<VReportingCdeGroupee> searchReportingCdeGroupeeList(Date dateDebut, Date dateFin, String idStatus, String isPaiementEffectue);
	

}
