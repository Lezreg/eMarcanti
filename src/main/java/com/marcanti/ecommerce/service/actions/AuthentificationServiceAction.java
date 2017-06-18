package com.marcanti.ecommerce.service.actions;

import com.marcanti.ecommerce.model.UserSession;

public interface AuthentificationServiceAction {
 
	public boolean emailExist(String email);
	
	public boolean isAuthenticated(String email, String passwordSHA512);
	
	public boolean[] isUserAndOrgaActif(String email);
	
	public void updateCode(String email, int code);
	
	public void updateGeneratedPassword(String email, String password, int isDefaultPassword);
	
	public boolean codeExist(String email, String code);
	
	public boolean getIsDefaultPassword(String email);
	
	public UserSession getUserSession(String email);
     
}