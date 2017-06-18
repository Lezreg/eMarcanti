package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.UserSession;
import com.marcanti.ecommerce.service.actions.AuthentificationServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;



@ManagedBean(name="authentificationBean")
@ViewScoped
public class AuthentificationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(AuthentificationBean.class);
	
	private String username;
	private String password;
	private String passwordConfirmation;
	private String code;

	@ManagedProperty("#{authentificationService}")
	private AuthentificationServiceAction service;
	
	public AuthentificationBean() 
	{
		username="";
		password="";
		passwordConfirmation="";
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
	
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	public AuthentificationServiceAction getService() {
		return service;
	}

	public void setService(AuthentificationServiceAction service) {
		this.service = service;
	}

	public String checkAuthentication()
	{
		FacesMessage facesMessage = new FacesMessage();
		String msg = null;
		boolean resu = false;
		boolean isOK = false;
		boolean[] resuTab = new boolean[2];
		String ecran="index";
		
		try {
			resu = service.emailExist(getUsername());
		} catch (Exception e) {
			logger.error("ERROR check email exist : ",e);
		}
		if(resu){
			try {
				String passwordSHA512 = DigestUtils.sha512Hex(getPassword());
				resu = service.isAuthenticated(getUsername(),passwordSHA512);
			} catch (Exception e) {
				logger.error("ERROR authentication : ",e);
			}
			if(resu){
				try {
					resuTab = service.isUserAndOrgaActif(getUsername());
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
	    		boolean isDefaultPassword = service.getIsDefaultPassword(getUsername());
				if(isDefaultPassword){
					UserSession userSession = service.getUserSession(getUsername());
					ParfumUtils.setUserSessionBean(userSession);
					ecran = "index";
				}else{
					HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
					request.setAttribute("email", getUsername());
					ecran = "new_password";
				}
			} catch (Exception e) {
				logger.error("ERROR getIsDefaultPassword : ",e);
			}
	    return ecran;
	    }else{
	    	return "login";
	    }
	}
	
	public String checkEmailAndSendCode()
	{
		FacesMessage facesMessage = new FacesMessage();
		String msg = null;
		boolean resu = false;
		boolean isOK = false;
		
		try {
			resu = service.emailExist(getUsername());
		} catch (Exception e) {
			logger.error("ERROR check email exist : ",e);
		}
		if(resu){
			isOK=true;
			//génération d'un code sur 4 chiffres
			int code = ParfumUtils.getCode();
			
			//mise à jour du champs 'codeVerificationPassword' dans la table membe
			try {
				service.updateCode(getUsername(),code);
			} catch (Exception e) {
				logger.error("ERROR update code : ",e);
			}
			
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			request.setAttribute("email", getUsername());
			
			// envoi du mail avec le code généré plus haut
			// try {
			// logger.info("send mail with generated code : " + code);
			// // Mail.send(getUsername(),
			// //
			// ParfumUtils.getBundleApplication().getString("message.membre.topic"),MessageFormat.format(ParfumUtils.getBundleApplication().getString("message.code.verification"),String.valueOf(code)));
			// } catch (MessagingException e) {
			// logger.error("ERROR send mail with generated code : ",e);
			// }
			
		}else{
			msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_invalidEmail");
			logger.info(getUsername() + " : " + msg);
			facesMessage.setDetail(msg); 
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		
	    if(isOK){
	    	return "code";
	    }else{
	    	return "change_password";
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
			resu = service.codeExist(email,getCode());
		} catch (Exception e) {
			logger.error("ERROR check code exist : ",e);
		}
		if(resu){
			isOK=true;
			//génération d'un password sur 8 caractères
			String password = ParfumUtils.getPassword();
			
			//mise à jour du champs 'codeVerificationPassword' et du password dans la table membe
			try {
				String passwordSHA512 = DigestUtils.sha512Hex(password);
				service	.updateGeneratedPassword(email,passwordSHA512,0);
			} catch (Exception e) {
				logger.error("ERROR update Generated password : ",e);
			} 
			// envoi du mail avec le password sur 8 caractères
			// try {
			// logger.info("send mail with generated password : " + password);
			// Mail.send(email,
			// ParfumUtils.getBundleApplication().getString("message.membre.topic"),MessageFormat.format(ParfumUtils.getBundleApplication().getString("message.membre.password.regenere"),email,password));
			// } catch (MessagingException e) {
			// logger.error("ERROR send mail with generated password : ",e);
			// }
			
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
	    	return "message";
	    }else{
	    	return "code";
	    }
	}
	
	public String savePassword()
	{
		FacesMessage facesMessage = new FacesMessage();
		String msg = null;
		boolean resu = false;
		
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String email = (String)request.getAttribute("email");
		
		resu = getPassword().equals(getPasswordConfirmation());
		
		if(!resu){
	    	msg = ParfumUtils.getBundleApplication().getString("message.confirmation.error");
	    	facesMessage.setDetail(msg); 
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		    return "new_password";
		}
		
		resu = ParfumUtils.checkPasswordFormat(getPassword());
		
		if(!resu){
	    	msg = ParfumUtils.getBundleApplication().getString("message.password.format");
	    	facesMessage.setDetail(msg); 
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		    return "new_password";
		}		
		
		try {
			String passwordSHA512 = DigestUtils.sha512Hex(getPassword());
			service.updateGeneratedPassword(email, passwordSHA512, 1);
		} catch (Exception e) {
			logger.error("ERROR update Generated password : ",e);
		}
		
    	return "index";
	}	
	
	public String deconnection() throws Exception
	{

		//HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	    //session.invalidate();
	    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		logger.info(getUsername() + " : deconnexion !!! ");
		
	    return "login";
	}
	

}
