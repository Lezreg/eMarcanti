package com.marcanti.ecommerce.view.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.Categorie;
import com.marcanti.ecommerce.model.CommandeGroupeeStatus;
import com.marcanti.ecommerce.model.Organisation;
import com.marcanti.ecommerce.model.Profil;
import com.marcanti.ecommerce.service.actions.ReferentielServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "referentielBean", eager=true)
@ApplicationScoped
public class ReferentielBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(ReferentielBean.class);
	
	public static final String BEAN_SESSION_NAME = "USER_SESSION";
	
	public static final String CATALOGUE = "catalogue";
	public static final String CAROUSEL = "carousel";
	
	public static final short PROFIL_MEMBRE  = 1;
	public static final short PROFIL_FILLEUL = 2;
	public static final short PROFIL_MANAGER = 3;
	public static final short PROFIL_ADMIN   = 4;
	
	public static final String PROFIL_MEMBRE_NAME  = "Membre";
	public static final String PROFIL_FILLEUL_NAME = "Filleul";
	public static final String PROFIL_MANAGER_NAME = "Manager";
	public static final String PROFIL_ADMIN_NAME   = "Admin";	


	private List<Organisation> organisationList;
	
	private List<Profil> profilList;
	
	private List<CommandeGroupeeStatus> commandeGroupeeStatusList;
	
	private static List<Profil> memberRoleList;
	
	private static List<Profil> managerRoleList;
	
	private static List<Profil> adminRoleList;
	
	private String defaultPassword;
	
	private static String smtpHost;
	
	private static String smtpPort;
	
	private static String smtpFrom;
	
	private String uploadFolderPath;
	
	private String uploadFolderUrl;
	
	//private String uploadCatalogFolderPath;
	
	//private String uploadCarouselFolderPath;
	
	private String uploadFileExt;
	
	public String uploadFileSize;
	
	private String uploadVideoExt;
	
	public String uploadVideoSize;	
	
	private static String mailFolderPath;
	
	public Map<String,Object> radioButtonOuiNon;
	{
		radioButtonOuiNon = new LinkedHashMap<String,Object>();
		radioButtonOuiNon.put(ParfumUtils.getBundleLibelle().getString("libelle_oui"), "true"); //label, value
		radioButtonOuiNon.put(ParfumUtils.getBundleLibelle().getString("libelle_non"), "false");
	}
	
	public Map<String,Object> radioButtonOuiNonInd;
	{
		radioButtonOuiNonInd = new LinkedHashMap<String,Object>();
		radioButtonOuiNonInd.put(ParfumUtils.getBundleLibelle().getString("libelle_oui"), "1"); //label, value
		radioButtonOuiNonInd.put(ParfumUtils.getBundleLibelle().getString("libelle_non"), "0");
		radioButtonOuiNonInd.put(ParfumUtils.getBundleLibelle().getString("libelle_indif"), "2");
	}	
	
	private List<Categorie> categorieList;

	@ManagedProperty("#{referentielService}")
	private ReferentielServiceAction referentielService;
	
	public List<Profil> getProfilList() {
		return profilList;
	}

	public void setProfilList(List<Profil> profilList) {
		this.profilList = profilList;
	}
	
	
	public List<CommandeGroupeeStatus> getCommandeGroupeeStatusList() {
		return commandeGroupeeStatusList;
	}

	public void setCommandeGroupeeStatusList(List<CommandeGroupeeStatus> commandeGroupeeStatusList) {
		this.commandeGroupeeStatusList = commandeGroupeeStatusList;
	}

	public static List<Profil> getMemberRoleList() {
		return memberRoleList;
	}

	public void setMemberRoleList(List<Profil> memberRoleList) {
		memberRoleList = memberRoleList;
	}

	public static List<Profil> getManagerRoleList() {
		return managerRoleList;
	}

	public void setManagerRoleList(List<Profil> managerRoleList) {
		this.managerRoleList = managerRoleList;
	}

	public static List<Profil> getAdminRoleList() {
		return adminRoleList;
	}

	public void setAdminRoleList(List<Profil> adminRoleList) {
		this.adminRoleList = adminRoleList;
	}

	public List<Organisation> getOrganisationList() {
		return organisationList;
	}

	public void setOrganisationList(List<Organisation> organisationList) {
		this.organisationList = organisationList;
	}

	public ReferentielServiceAction getReferentielService() {
		return referentielService;
	}

	public void setReferentielService(ReferentielServiceAction referentielService) {
		this.referentielService = referentielService;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}
	
	public List<Categorie> getCategorieList() {
		return categorieList;
	}

	public void setCategorieList(List<Categorie> categorieList) {
		this.categorieList = categorieList;
	}
	
	public Map<String, Object> getRadioButtonOuiNon() {
		return radioButtonOuiNon;
	}

	public void setRadioButtonOuiNon(Map<String, Object> radioButtonOuiNon) {
		this.radioButtonOuiNon = radioButtonOuiNon;
	}
	
	public Map<String, Object> getRadioButtonOuiNonInd() {
		return radioButtonOuiNonInd;
	}

	public void setRadioButtonOuiNonInd(Map<String, Object> radioButtonOuiNonInd) {
		this.radioButtonOuiNonInd = radioButtonOuiNonInd;
	}

	public static String getSmtpHost() {
		return smtpHost;
	}

	public static void setSmtpHost(String smtpHost) {
		ReferentielBean.smtpHost = smtpHost;
	}

	public static String getSmtpPort() {
		return smtpPort;
	}

	public static void setSmtpPort(String smtpPort) {
		ReferentielBean.smtpPort = smtpPort;
	}

	public static String getSmtpFrom() {
		return smtpFrom;
	}

	public static void setSmtpFrom(String smtpFrom) {
		ReferentielBean.smtpFrom = smtpFrom;
	}

	public String getUploadFolderPath() {
		return uploadFolderPath;
	}

	public void setUploadFolderPath(String uploadFolderPath) {
		this.uploadFolderPath = uploadFolderPath;
	}
	
	public String getUploadFolderUrl() {
		return uploadFolderUrl;
	}

	public void setUploadFolderUrl(String uploadFolderUrl) {
		this.uploadFolderUrl = uploadFolderUrl;
	}

	public String getUploadCatalogFolderPath() {
		return uploadFolderPath+File.separator+CATALOGUE;
	}

	/*public void setUploadCatalogFolderPath(String uploadCatalogFolderPath) {
		this.uploadCatalogFolderPath = uploadCatalogFolderPath;
	}*/

	public String getUploadCarouselFolderPath() {
		return uploadFolderPath+File.separator+CAROUSEL;
	}

	/*public void setUploadCarouselFolderPath(String uploadCarouselFolderPath) {
		this.uploadCarouselFolderPath = uploadCarouselFolderPath;
	}*/
	
	
	public String getUploadCatalogFolderUrl() {
		return uploadFolderUrl+File.separator+CATALOGUE;
	}

	public String getUploadCarouselFolderUrl() {
		return uploadFolderUrl+File.separator+CAROUSEL;
	}

	public String getUploadFileExt() {
		return uploadFileExt;
	}

	public void setUploadFileExt(String uploadFileExt) {
		this.uploadFileExt = uploadFileExt;
	}

	public String getUploadFileSize() {
		return uploadFileSize;
	}

	public void setUploadFileSize(String uploadFileSize) {
		this.uploadFileSize = uploadFileSize;
	}
	
	public String getUploadVideoExt() {
		return uploadVideoExt;
	}

	public void setUploadVideoExt(String uploadVideoExt) {
		this.uploadVideoExt = uploadVideoExt;
	}

	public String getUploadVideoSize() {
		return uploadVideoSize;
	}

	public void setUploadVideoSize(String uploadVideoSize) {
		this.uploadVideoSize = uploadVideoSize;
	}

	public static String getMailFolderPath() {
		return mailFolderPath;
	}

	public static void setMailFolderPath(String mailFolderPath) {
		ReferentielBean.mailFolderPath = mailFolderPath;
	}

	public void getPropertiesConfig() {
		
		String confFilePath=null;
		
		try {
			Properties prop = new Properties();
			InputStream inputConfFile = null;
			confFilePath = System.getProperty("parfum.conf.file");
			
			inputConfFile = new FileInputStream(confFilePath);

			// load a properties file
			prop.load(inputConfFile);
			
			logger.info("confFilePath : " + confFilePath);
			

			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				logger.info("Key : " + key + ", Value : " + value);
			}

			smtpHost=prop.getProperty("mail.smtp.host");
			smtpPort=prop.getProperty("mail.smtp.port");
			smtpFrom=prop.getProperty("mail.from");
			uploadFolderPath=prop.getProperty("upload.folder.path");
			uploadFileExt=prop.getProperty("upload.file.ext");
			uploadFileSize=prop.getProperty("upload.file.size");
			uploadVideoExt=prop.getProperty("upload.video.ext");
			uploadVideoSize=prop.getProperty("upload.video.size");			
			mailFolderPath=prop.getProperty("mail.folder.path");
			uploadFolderUrl="upload";
			
		} catch (Exception e) {
			logger.error("ERROR loading conf file : " + confFilePath,e);
		}
	}

	public ReferentielBean() {
	}

	@PostConstruct
	public void init() {
		this.organisationList = referentielService.getOrganisationList();
		this.profilList = referentielService.getProfilList();
		this.defaultPassword = referentielService.getDefaultPassword();
		this.categorieList = referentielService.getCategorieList();
		this.commandeGroupeeStatusList = referentielService.getCommandeGroupeeStatusList();
		getPropertiesConfig();
		this.memberRoleList = new ArrayList<Profil>();
		this.memberRoleList.add(new Profil(PROFIL_FILLEUL,PROFIL_FILLEUL_NAME,""));
		this.managerRoleList = new ArrayList<Profil>();
		this.managerRoleList.add(new Profil(PROFIL_MEMBRE,PROFIL_MEMBRE_NAME,""));
		this.managerRoleList.add(new Profil(PROFIL_FILLEUL,PROFIL_FILLEUL_NAME,""));
		this.adminRoleList = new ArrayList<Profil>();
		this.adminRoleList.add(new Profil(PROFIL_MANAGER,PROFIL_MANAGER_NAME,""));
		this.adminRoleList.add(new Profil(PROFIL_MEMBRE,PROFIL_MEMBRE_NAME,""));
		this.adminRoleList.add(new Profil(PROFIL_FILLEUL,PROFIL_FILLEUL_NAME,""));
	}
	
}
