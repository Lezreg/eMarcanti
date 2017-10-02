package com.marcanti.ecommerce.service.actions;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcanti.ecommerce.dao.AuthentificationDAO;
import com.marcanti.ecommerce.view.bean.UserSessionBean;


public class AuthentificationServiceActionImpl implements AuthentificationServiceAction {


	@Autowired(required=true)
	private AuthentificationDAO authentificationDAO;
 
	public AuthentificationDAO getAuthentificationDAO() {
		return authentificationDAO;
	}

	public void setAuthentificationDAO(AuthentificationDAO authentificationDAO) {
		this.authentificationDAO = authentificationDAO;
	}
	
	public boolean emailExist(String email){
		return authentificationDAO.emailExist(email);
	}

	@Override
	public boolean isAuthenticated(String email, String passwordSHA512) {
		return authentificationDAO.isAuthenticated(email,passwordSHA512);
	}

	@Override
	public boolean[] isUserAndOrgaActif(String email) {
		return authentificationDAO.isUserAndOrgaActif(email);
	}

	@Override
	public void updateCode(String email, int code) {
		authentificationDAO.updateCode(email,code);
		
	}

	@Override
	public void updateGeneratedPassword(String email, String password, int isDefaultPassword) {
		authentificationDAO.updateGeneratedPassword(email,password,isDefaultPassword);
		
	}

	@Override
	public boolean codeExist(String email, String code) {
		return authentificationDAO.codeExist(email,code);
	}

	@Override
	public boolean getIsDefaultPassword(String email) {
		return authentificationDAO.getIsDefaultPassword(email);
	}

	@Override
	public UserSessionBean getUserSession(String email) {
		return authentificationDAO.getUserSession(email);
	}

	@Override
	public void updateLastConnectionDate(Date toDay, String email) {
		authentificationDAO.updateLastConnectionDate(toDay, email);
		
	}
	
	
	
	
}
