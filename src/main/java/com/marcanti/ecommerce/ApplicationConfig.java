package com.marcanti.ecommerce;

import java.io.Serializable;


/**
 * 
 * @author Regina Patrick
 *
 */
public class ApplicationConfig implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	   * Clef en Contexte Servlet du Bean.
	   */
	  public static final String BEAN_KEY = "applicationConfig";
	 
	  /**
	   * Clef de l'URL de login GAIA.
	   */
	  private static final String URL_LOGIN_KEY = "login_url";
	  
	  /**
	   * Clef de l'URL d'authentification en succes.
	   */
	  private static final String URL_AUTH_SUCCES_KEY = "url_auth_succes";
	  
	  private static final String INES_PROFIL_ADMIN = "ines.profil.ADMIN";
	  private static final String INES_PROFIL_MISEAJOUR = "ines.profil.MAJ";
	  private static final String INES_PROFIL_CHEF = "ines.profil.CHEF";
	  private static final String USERSCHEMA_TYPE="userschema.type";
	  private static final String INSTALL_CHOIX="install.choix";
	  private static final String TYPE_CLUSTER="cluster.type";
	  private static final String INES_LISTE_VAL_LIGNE = "ines.liste.val.ligne";
	  private static final String INES_TAB_NBLIGNE_default = "ines.tab.nbligne.default";
	  
	  private static final String INES_REP_REQUEST_FILE = "ines.rep.request.file";
	  private static final String INES_REP_XSD_FILE = "ines.rep.xsd.file";
	  private static final String INES_REP_CHEF_KEY_FILE = "ines.rep.chef.key";
	  
	  private static final String SPM_AFFICHE_HTTPPORT = "SPM.Affiche.HttpPort";
	  private static final String SPM_AFFICHE_HTTPSPORT = "SPM.Affiche.HttpsPort";
	  private static final String SPM_AFFICHE_SHUTDOWNPORT = "SPM.Affiche.ShutdownPort";
	  private static final String CCE_AFFICHE_HTTPPORT = "CCE.Affiche.HttpPort";
	  private static final String CCE_AFFICHE_HTTPSPORT = "CCE.Affiche.HttpsPort";
	  private static final String CCE_AFFICHE_SHUTDOWNPORT = "CCE.Affiche.ShutdownPort";
	  private static final String BRKMON_AFFICHE_HTTPPORT = "BRKMON.Affiche.HttpPort";
	  private static final String BRKSERV_AFFICHE_HTTPPORT = "BRKSERV.Affiche.HttpPort";
	  private static final String MWS_AFFICHE_HTTPPORT = "MWS.Affiche.HttpPort";
	  private static final String MWS_AFFICHE_HTTPSPORT = "MWS.Affiche.HttpsPort";
	  private static final String MWS_AFFICHE_JMXPORT = "MWS.Affiche.JmxPort";
	  private static final String MWS_AFFICHE_SHUTDOWNPORT = "MWS.Affiche.ShutDownPort";
	  private static final String IS_AFFICHE_HTTPPORT = "IS.Affiche.HttpPort";
	  private static final String IS_AFFICHE_HTTPSPORT = "IS.Affiche.HttpsPort";
	  private static final String IS_AFFICHE_DIAGPORT = "IS.Affiche.DiagPort";
	  private static final String IS_AFFICHE_JMXPORT = "IS.Affiche.JmxPort";
	  private static final String IS_AFFICHE_PRTPORT = "IS.Affiche.PrtPort";
	  private static final String IS_AFFICHE_SHUTDOWNPORT = "IS.Affiche.ShutDownPort";
	  private static final String INSTANCEAPACHE_AFFICHE_HTTPPORT = "IntanceApache.Affiche.HttpPort";
	  private static final String INSTANCETOMCAT_AFFICHE_HTTPPORT = "IntanceTomcat.Affiche.HttpPort";
	  private static final String INSTANCETOMCAT_AFFICHE_JMXPORT = "IntanceTomcat.Affiche.JmxPort";
	  private static final String INSTANCETOMCAT_AFFICHE_AJPPORT = "IntanceTomcat.Affiche.AjpPort";
	  private static final String INSTANCETOMCAT_AFFICHE_SHUTDOWNPORT = "IntanceTomcat.Affiche.ShutdownPort";
	  
	  private static final String INES_CHEF_TYPESERVEUR_ID = "ines.chef.typeserveur.id";
	  private static final String INES_CHEF_TYPECLIENT_ID = "ines.chef.typeclient.id";
	  private static final String INES_CHEF_SSH_CLIENT_TIMEOUT = "ines.chef.ssh.client.timeout";
	  
	  
	  private static final String INES_COMMANDE_PROCESSBUILDER_ARG0 = "ines.commande.processbuilder.arg0";
	  private static final String INES_COMMANDE_PROCESSBUILDER_ARG1 = "ines.commande.processbuilder.arg1";
	  private static final String INES_COMMANDE_OPENSSL_DIRECTORY = "ines.commande.openssl.directory";

	  private static final String StatusComposant_ID_AINSTALLER = "StatusComposant.ID.AINSTALLER";
	  private static final String StatusComposant_ID_INSTALLE = "StatusComposant.ID.INSTALLE";
	  
	  /**
	   * Url de connection GAIA
	   */
	  protected String mUrlLogin;
		  
	  /**
	   * Url de renvoi en cas de succes.
	   */
	  protected String mUrlAuthentificationSuccess;
	  
	  protected String mProfilAdministrateur;
	  protected String mProfilMiseAJour;
	  protected String mProfilChef;
	  protected String mUserschemaType;
	  protected String mInstallChoix;
	  protected String mTypeCluster;
	  
	  protected String mTabNbLigneDefault;
	  protected String mListeValLigne;
	  protected String mRequestRepFile;
	  protected String mXsdRepFile;
	  protected String mChefKeyRepFile;
	  protected String mSpmAfficheHttpPort;
	  protected String mSpmAfficheHttpsPort;
	  protected String mSpmAfficheShutDownPort;
	  protected String mCceAfficheHttpPort;
	  protected String mCceAfficheHttpsPort;
	  protected String mCceAfficheShutDownPort;
	  protected String mBrkMonAfficheHttpPort;
	  protected String mBrkServAfficheHttpPort;
	  protected String mMwsAfficheHttpPort;
	  protected String mMwsAfficheHttpsPort;
	  protected String mMwsAfficheJmxPort;
	  protected String mMwsAfficheShutDownPort;
	  protected String mIsAfficheHttpPort;
	  protected String mIsAfficheHttpsPort;
	  protected String mIsAfficheDiagPort;
	  protected String mIsAfficheJmxPort;
	  protected String mIsAfficheShutDownPort;
	  protected String mIsAffichePrtPort;
	  protected String mInstanceApacheAfficheHttpPort;
	  protected String mInstanceTomcatAfficheHttpPort;
	  protected String mInstanceTomcatAfficheJmxPort;
	  protected String mInstanceTomcatAfficheAjpPort;
	  protected String mInstanceTomcatAfficheShutDownPort;
	  
	  protected String mChefIdTypeServeur;
	  protected String mChefIdTypeClient;
	  protected String mSshChefClientTimeOut;
	  
	  protected String mInesCommandeProcessBuilderArg0;
	  protected String mInesCommandeProcessBuilderArg1;
	  protected String mInesCommandeOpenSSLDirectory;
	  
	  protected String mStatusIDComposantAInstaller;
	  protected String mStatusIDComposantInstalled;
	  
	  

	  
	  	  
	  /**
	   * Url de renvoi pour une authentification en succes.
	   * @return l'url.
	   */
	  public String getUrlAuthSucces( ) {
	      return mUrlAuthentificationSuccess;
	  }
	  
	  /**
	   * Url de connexion.
	   * @return l'URL de connexion.
	   * @see ApplicationInit#redirectUrl(java.lang.String, javax.servlet.ServletContext)
	   */
	  public String getLoginUrl( ) {
	      return mUrlLogin;
	  }
	  
	  /**
	   * recupere le code du profil Administrateur
	   * @return
	   */
	  public String getProfilAdministrateur( ) {
	      return mProfilAdministrateur;
	  }
	 
	  /**
	   * recupere le code du profil Mise a Jour
	   * @return
	   */
	  public String getProfilMiseAJour( ) {
	      return mProfilMiseAJour;
	  }
	  
	  /**
	   * recupere le code du profil Chef
	   * @return
	   */
	  public String getProfilChef( ) {
	      return mProfilChef;
	  }

	public String getUserschemaType() {
		return mUserschemaType;
	}

	public String getInstallChoix() {
		return mInstallChoix;
	}


	public String getTypeCluster() {
		return mTypeCluster;
	}

	public String getListeValLigne() {
		
		return mListeValLigne;		
	}
	 
	public String getTabNbLigneDefault() {
		
		return this.mTabNbLigneDefault;
	}
	
	public String getRequestRepFile() {
		return mRequestRepFile;
	}

	public String getChefKeyRepFile() {
		return mChefKeyRepFile;
	}
	
	public String getXsdRepFile() {
		return mXsdRepFile;
	}
		
	public String getSpmAfficheHttpPort() {
		return mSpmAfficheHttpPort;
	}
	
	public String getSpmAfficheHttpsPort() {
		return mSpmAfficheHttpsPort;
	}
	
	public String getSpmAfficheShutDownPort() {
		return mSpmAfficheShutDownPort;
	}
	
	public String getCceAfficheHttpPort() {
		return mCceAfficheHttpPort;
	}
	
	public String getCceAfficheHttpsPort() {
		return mCceAfficheHttpsPort;
	}
	
	public String getCceAfficheShutDownPort() {
		return mCceAfficheShutDownPort;
	}
	
	public String getBrokermonitorAfficheHttpPort() {
		return mBrkMonAfficheHttpPort;
	}
	
	public String getBrokerserverAfficheHttpPort() {
		return mBrkServAfficheHttpPort;
	}
	
	public String getMwsAfficheHttpsPort() {
		return mMwsAfficheHttpsPort;
	}
	
	public String getMwsAfficheJmxPort() {
		return mMwsAfficheJmxPort;
	}
	
	public String getMwsAfficheShutDownPort() {
		return mMwsAfficheShutDownPort;
	}
	
	public String getMwsAfficheHttpPort() {
		return mMwsAfficheHttpPort;
	}
	
	public String getIsAfficheHttpPort() {
		return mIsAfficheHttpPort;
	}
	
	public String getIsAfficheHttpsPort() {
		return mIsAfficheHttpsPort;
	}
	public String getIsAfficheDiadPort() {
		return mIsAfficheDiagPort;
	}
	public String getIsAfficheJmxPort() {
		return mIsAfficheJmxPort;
	}
	public String getIsAfficheShutDownPort() {
		return mIsAfficheShutDownPort;
	}
	public String getIsAffichePrtPort() {
		return mIsAffichePrtPort;
	}
	
	public String getChefIdTypeServeur() {
		return mChefIdTypeServeur;
	}
	
	public String getChefIdTypeClient() {
		return mChefIdTypeClient;
	}
	
	public String getSshChefCLientTimeOut() {
		return mSshChefClientTimeOut;
	}

	public String getInesCommandeProcessBuilderArg0() {
		return mInesCommandeProcessBuilderArg0;
	}

	public String getInesCommandeProcessBuilderArg1() {
		return mInesCommandeProcessBuilderArg1;
	}

	public String getInesCommandeOpenSSLDirectory() {
		return mInesCommandeOpenSSLDirectory;
	}
	
	public String getInstanceApacheAfficheHttpPort() {
		return mInstanceApacheAfficheHttpPort;
	}
	
	public String getInstanceTomcatAfficheHttpPort() {
		return mInstanceTomcatAfficheHttpPort;
	}
	
	public String getInstanceTomcatAfficheJmxPort() {
		return mInstanceTomcatAfficheJmxPort;
	}
	
	public String getInstanceTomcatAfficheAjpPort() {
		return mInstanceTomcatAfficheAjpPort;
	}
	
	public String getInstanceTomcatAfficheShutDownPort() {
		return mInstanceTomcatAfficheShutDownPort;
	}
	
	public String getStatusIDComposantAInstaller() {
		return mStatusIDComposantAInstaller;
	}
	
	public String getStatusIDComposantInstalled() {
		return mStatusIDComposantInstalled;
	}
	
	

}
