package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.Marque;
import com.marcanti.ecommerce.service.actions.MarqueServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "marquesBean")
@SessionScoped
public class MarquesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(MarquesBean.class);

	private List<Marque> marqueList;

	private Marque marque;
	
	private String titre="";
	
	//@ManagedProperty("#{param.idMarque}")
	private Short idMarque;
	
	@ManagedProperty("#{marqueService}")
	private MarqueServiceAction marqueService;
	
	@ManagedProperty("#{referentielBean}")
	private ReferentielBean referentielBean;

	public MarquesBean() {
	}

	@PostConstruct
	public void init() {
		this.marqueList = marqueService.getMarqueList();
		this.marque = new Marque((short)0);
	}

	
	public ReferentielBean getReferentielBean() {
		return referentielBean;
	}

	public void setReferentielBean(ReferentielBean referentielBean) {
		this.referentielBean = referentielBean;
	}

	
	public List<Marque> getMarqueList() {
		return marqueList;
	}

	public void setMarqueList(List<Marque> marqueList) {
		this.marqueList = marqueList;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Short getIdMarque() {
		return idMarque;
	}

	public void setIdMarque(Short idMarque) {
		this.idMarque = idMarque;
	}

	public MarqueServiceAction getMarqueService() {
		return marqueService;
	}

	public void setMarqueService(MarqueServiceAction marqueService) {
		this.marqueService = marqueService;
	}

	public Map<String, Object> getRadioButtonOuiNon() {
		return referentielBean.getRadioButtonOuiNon();
	}

	public void setRadioButtonOuiNon(Map<String, Object> radioButtonOuiNon) {
		referentielBean.setRadioButtonOuiNon(radioButtonOuiNon);
	}

	public String editMarque() {

		Marque marque = new Marque(getIdMarque().shortValue());
		marque= marqueService.getMarque(marque);
		setMarque(marque);
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_marque");
		return "marque";

	}
	
	public String addMarqueView() {
		logger.info("addMarqueView");
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_ajouter_marque");
		return "marque";

	}	
	
	
	public String insertOrUpdateMarque() {
		
		FacesMessage facesMessage = new FacesMessage();
		String msg;
		String ecran ="marque";
		
		logger.info("insertOrUpdateMarque");

		if(this.marque.getIdMarque()==null || this.marque.getIdMarque().shortValue()==0){
				
			marqueService.insertMarque(marque);
			msg = ParfumUtils.getBundleApplication().getString("message.ajouter.marque");
			this.titre = ParfumUtils.getBundleApplication().getString("libelle_ajouter_marque");
		    ecran="marques";
		    
		}else{
			
			marqueService.updateMarque(this.marque);;
			msg = ParfumUtils.getBundleApplication().getString("message.modif.marque");
			this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_marque");
		}
		//on rafraichit la liste des marques
		this.marqueList=marqueService.getMarqueList();
		facesMessage.setSummary(msg); 
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
	    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		return ecran;
	}
	
	
	

}
