package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.service.ServiceAuthentification;
import com.marcanti.ecommerce.utils.Mail;
import com.marcanti.ecommerce.utils.ParfumUtils;



@ManagedBean(name="authentificationBean")
@ViewScoped
public class AuthentificationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(AuthentificationBean.class);
	
	private String username;
	private String password;
	private String password2;
	private String code;

	
	public AuthentificationBean() 
	{
		username="";
		password="";
		password2="";
		code="";
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String checkAuthentication()
	{
		FacesMessage facesMessage = new FacesMessage();
		String msg = null;
		boolean resu = false;
		boolean isOK = false;
		boolean[] resuTab = new boolean[2];
		String ecran="/pages/private/index.xhtml";
		
		try {
			resu = ServiceAuthentification.emailExist(getUsername());
		} catch (Exception e) {
			logger.error("ERROR check email exist : ",e);
		}
		if(resu){
			try {
				resu = ServiceAuthentification.isAuthenticated(getUsername(),getPassword());
			} catch (Exception e) {
				logger.error("ERROR authentication : ",e);
			}
			if(resu){
				try {
					resuTab = ServiceAuthentification.isUserAndOrgaActif(getUsername());
				} catch (Exception e) {
					logger.error("ERROR user and orga actif : ",e);
				}
				boolean isUserActif = resuTab[0];
				boolean isOrgaActif = resuTab[1];
				if(isUserActif){
					if(isOrgaActif){
						isOK=true;
					}else{
						msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_orgaDisabled");
					}
				}else{
					msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_userDisabled");
				}
			}else{
				msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_invalidPassword");
			}
		}else{
			msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_invalidLogin");
		}
		
		if(msg!=null){
			logger.info(getUsername() + " : " + msg);
			facesMessage.setDetail(msg); 
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}else{
			logger.info(getUsername() + " : connexion ");
		}

	    if(isOK){
	    	try {
				boolean isDefaultPassword = ServiceAuthentification.getIsDefaultPassword(getUsername());
				if(isDefaultPassword){
					HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
					Membre membre = new Membre();
					request.getSession().setAttribute("membre", membre);
					ecran = "/pages/private/index.xhtml";
				}else{
					HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
					request.setAttribute("email", getUsername());
					ecran = "/pages/private/new_password.xhtml";
				}
			} catch (Exception e) {
				logger.error("ERROR getIsDefaultPassword : ",e);
			}
	    return ecran;
	    }else{
	    	return "/pages/login.xhtml";
	    }
	}
	
	public String checkEmailAndSendCode()
	{
		FacesMessage facesMessage = new FacesMessage();
		String msg = null;
		boolean resu = false;
		boolean isOK = false;
		
		try {
			resu = ServiceAuthentification.emailExist(getUsername());
		} catch (Exception e) {
			logger.error("ERROR check email exist : ",e);
		}
		if(resu){
			isOK=true;
			//génération d'un code sur 4 chiffres
			int code = ParfumUtils.getCode();
			
			//mise à jour du champs 'codeVerificationPassword' dans la table membe
			try {
				ServiceAuthentification.updateCode(getUsername(),code);
			} catch (Exception e) {
				logger.error("ERROR update code : ",e);
			}
			
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			request.setAttribute("email", getUsername());
			
			// envoi du mail avec le code généré plus haut
			try {
				logger.info("send mail with generated code : " + code);
				Mail.send(getUsername(), ParfumUtils.getBundleApplication().getString("message.membre.topic"),MessageFormat.format(ParfumUtils.getBundleApplication().getString("message.code.verification"),String.valueOf(code)));
			} catch (MessagingException e) {
				logger.error("ERROR send mail with generated code : ",e);
			}
			
		}else{
			msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_invalidEmail");
			logger.info(getUsername() + " : " + msg);
			facesMessage.setDetail(msg); 
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		
	    if(isOK){
	    	return "/pages/public/code.xhtml";
	    }else{
	    	return "/pages/public/change_password.xhtml";
	    }
	}
	
	
	public String checkCodeAndSendPassword()
	{
		FacesMessage facesMessage = new FacesMessage();
		String msg = null;
		boolean resu = false;
		boolean isOK = false;
		
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String email = (String)request.getAttribute("email");
		
		try {
			resu = ServiceAuthentification.codeExist(email,getCode());
		} catch (Exception e) {
			logger.error("ERROR check code exist : ",e);
		}
		if(resu){
			isOK=true;
			//génération d'un password sur 8 caractères
			String password = ParfumUtils.getPassword();
			
			//mise à jour du champs 'codeVerificationPassword' et du password dans la table membe
			try {
				ServiceAuthentification.updateGeneratedPassword(email,password,0);
			} catch (Exception e) {
				logger.error("ERROR update Generated password : ",e);
			} 
			// envoi du mail avec le password sur 8 caractères
			try {
				logger.info("send mail with generated password : " + password);
				Mail.send(email, ParfumUtils.getBundleApplication().getString("message.membre.topic"),MessageFormat.format(ParfumUtils.getBundleApplication().getString("message.membre.password.regenere"),email,password));
			} catch (MessagingException e) {
				logger.error("ERROR send mail with generated password : ",e);
			}
			
		}else{
			msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_invalidCode");
			logger.info(email + " : " + msg);
			facesMessage.setDetail(msg); 
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
	    
	    if(isOK){
	    	msg = ParfumUtils.getBundleApplication().getString("message.send.password");
	    	facesMessage.setDetail(msg); 
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
		    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	    	return "/include/message.xhtml";
	    }else{
	    	return "/pages/public/code.xhtml";
	    }
	}
	
	public String savePassword()
	{
		FacesMessage facesMessage = new FacesMessage();
		String msg = null;
		boolean resu = false;
		
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String email = (String)request.getAttribute("email");
		
		resu = getPassword().equals(getPassword2());
		
		if(!resu){
	    	msg = ParfumUtils.getBundleApplication().getString("message.confirmation.error");
	    	facesMessage.setDetail(msg); 
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		    return "/pages/private/new_password.xhtml";
		}
		
		resu = ParfumUtils.checkPasswordFormat(getPassword());
		
		if(!resu){
	    	msg = ParfumUtils.getBundleApplication().getString("message.password.format");
	    	facesMessage.setDetail(msg); 
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		    return "/pages/private/new_password.xhtml";
		}		
		
		try {
			ServiceAuthentification.updateGeneratedPassword(email, getPassword(), 1);
		} catch (Exception e) {
			logger.error("ERROR update Generated password : ",e);
		}
		
    	return "/pages/private/index.xhtml";
	}	
	
	public String deconnection() throws Exception
	{

		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().invalidate();
		logger.info(getUsername() + " : deconnexion ");
		
	    return "/pages/login";
	}
	

}
