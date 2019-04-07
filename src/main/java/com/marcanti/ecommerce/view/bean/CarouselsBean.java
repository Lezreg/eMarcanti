package com.marcanti.ecommerce.view.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.model.Carousel;
import com.marcanti.ecommerce.model.Marque;
import com.marcanti.ecommerce.service.actions.CarouselServiceAction;
import com.marcanti.ecommerce.service.actions.MarqueServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "carouselsBean")
@SessionScoped
public class CarouselsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(CarouselsBean.class);

	private List<Carousel> carouselList;

	private List<Marque> marqueList;

	private Carousel carousel;

	private UploadedFile uploadedFile;

	private String oldRangItem;

	private Integer idCarousel;

	private String titre = "";

	@ManagedProperty("#{carouselService}")
	private CarouselServiceAction carouselService;

	@ManagedProperty("#{referentielBean}")
	private ReferentielBean referentielBean;

	@ManagedProperty("#{marqueService}")
	private MarqueServiceAction marqueService;

	public CarouselsBean() {
	}

	@PostConstruct
	public void init() {
		this.carouselList = carouselService.getCarouselList();
		this.carousel = new Carousel(0);
		// Short idMarque = new Short((short)0);
		// this.carousel.setIdMarque(new Marque(idMarque));
		this.marqueList = marqueService.getMarqueList();
	}

	public String getElementRang() {
		if (String.valueOf(this.carousel.getElementRang()).equals("0")) {
			return "";
		} else
			return String.valueOf(this.carousel.getElementRang());
	}

	public void setElementRang(String elementRang) {
		this.carousel.setElementRang(Short.valueOf(elementRang));
	}

	public String getOldRangItem() {
		return oldRangItem;
	}

	public void setOldRangItem(String oldRangItem) {
		this.oldRangItem = oldRangItem;
	}

	public ReferentielBean getReferentielBean() {
		return referentielBean;
	}

	public void setReferentielBean(ReferentielBean referentielBean) {
		this.referentielBean = referentielBean;
	}

	public List<Carousel> getCarouselList() {
		return carouselList;
	}

	public void setCarouselList(List<Carousel> carouselList) {
		this.carouselList = carouselList;
	}

	public Carousel getCarousel() {
		return carousel;
	}

	public void setCarousel(Carousel carousel) {
		this.carousel = carousel;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public Integer getIdCarousel() {
		return idCarousel;
	}

	public void setIdCarousel(Integer idCarousel) {
		this.idCarousel = idCarousel;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public CarouselServiceAction getCarouselService() {
		return carouselService;
	}

	public List<Marque> getMarqueList() {
		return marqueList;
	}

	public void setMarqueList(List<Marque> marqueList) {
		this.marqueList = marqueList;
	}

	public MarqueServiceAction getMarqueService() {
		return marqueService;
	}

	public void setMarqueService(MarqueServiceAction marqueService) {
		this.marqueService = marqueService;
	}

	public void setCarouselService(CarouselServiceAction carouselService) {
		this.carouselService = carouselService;
	}

	public String editCarousel() {

		Carousel carousel = new Carousel(getIdCarousel());
		carousel = carouselService.getCarousel(carousel);
		setCarousel(carousel);
		setOldRangItem(String.valueOf(carousel.getElementRang()));
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_modifier_item");
		return "carousel";
	}

	public String addCarouselView() {
		logger.info("addCarouselView");
		this.carousel = new Carousel(0);
		this.carousel.setIdMarque(new Marque((short) 0));
		setOldRangItem("0");
		this.uploadedFile = null;
		this.titre = ParfumUtils.getBundleApplication().getString("libelle_ajouter_item");
		return "carousel";

	}

	public String insertOrUpdateCarousel() {

		FacesMessage facesMessage = new FacesMessage();
		String msg;
		String ecran = "carousel";

		logger.info("insertOrUpdateCarousel");

		/*
		 * if(getCarousel().getElementNom()==null ||
		 * getCarousel().getElementNom().equals("")){ msg =
		 * ParfumUtils.getBundleApplication().getString("nom_item_obligatoire");
		 * facesMessage = new FacesMessage(); facesMessage.setSummary(msg);
		 * facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		 * FacesContext.getCurrentInstance().addMessage("messages_view", facesMessage);
		 * isError = true; } if(uploadedFile==null){ msg =
		 * ParfumUtils.getBundleApplication().getString("image_item_obligatoire");
		 * facesMessage = new FacesMessage(); facesMessage.setSummary(msg);
		 * facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		 * FacesContext.getCurrentInstance().addMessage("messages_view", facesMessage);
		 * isError = true; } if((new Short(getCarousel().getElementRang()))==null){ msg
		 * = ParfumUtils.getBundleApplication().getString("nom_rang_obligatoire");
		 * facesMessage = new FacesMessage(); facesMessage.setSummary(msg);
		 * facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		 * FacesContext.getCurrentInstance().addMessage("messages_view", facesMessage);
		 * isError = true; } if(getCarousel().getProduitPrix()==null){ msg =
		 * ParfumUtils.getBundleApplication().getString("prix_prod_obligatoire");
		 * facesMessage = new FacesMessage(); facesMessage.setSummary(msg);
		 * facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		 * FacesContext.getCurrentInstance().addMessage("messages_view", facesMessage);
		 * isError = true; } if(getCarousel().getProduitNom()==null ||
		 * getCarousel().getProduitNom().equals("")){ msg =
		 * ParfumUtils.getBundleApplication().getString("nom_prod_obligatoire");
		 * facesMessage = new FacesMessage(); facesMessage.setSummary(msg);
		 * facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		 * FacesContext.getCurrentInstance().addMessage("messages_view", facesMessage);
		 * isError = true; } if(getCarousel().getProduitSousTitre()==null ||
		 * getCarousel().getProduitSousTitre().equals("")){ msg =
		 * ParfumUtils.getBundleApplication().getString("ss_titre_prod_obligatoire");
		 * facesMessage = new FacesMessage(); facesMessage.setSummary(msg);
		 * facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		 * FacesContext.getCurrentInstance().addMessage("messages_view", facesMessage);
		 * isError = true; } if(getCarousel().getBoutonLibelle()==null ||
		 * getCarousel().getBoutonLibelle().equals("")){ msg =
		 * ParfumUtils.getBundleApplication().getString("btn_libelle_prod_obligatoire");
		 * facesMessage = new FacesMessage(); facesMessage.setSummary(msg);
		 * facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		 * FacesContext.getCurrentInstance().addMessage("messages_view", facesMessage);
		 * isError = true; }
		 * 
		 * if(isError){ return ecran; }
		 */

		if (!getOldRangItem().equals(getElementRang()) && carouselService.isRangExist(carousel)) {
			msg = MessageFormat.format(ParfumUtils.getBundleApplication().getString("libelle_Erreur_rang"),
					String.valueOf(getElementRang()));
			facesMessage.setSummary(msg);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			return ecran;
		}

		if (this.carousel.getIdCarousel() == null || this.carousel.getIdCarousel().shortValue() == 0) {

			if (this.uploadedFile == null) {
				msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_upload");
				facesMessage.setSummary(msg);
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				return ecran;
			}

			carouselService.insertCarousel(carousel);
			msg = ParfumUtils.getBundleApplication().getString("message.ajouter.carousel");
			ecran = "carousels";

		} else {

			carouselService.updateCarousel(this.carousel);
			msg = ParfumUtils.getBundleApplication().getString("message.modif.carousel");
		}
		facesMessage.setSummary(msg);
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);

		// on rafraichit la liste du carousel de la page admin
		this.carouselList = carouselService.getCarouselList();

		// on rafraichit la liste du carousel de la page index
		CarouselIndexBean carouselIndexBean = (CarouselIndexBean) ParfumUtils.getSessionObject("carouselIndexBean");
		carouselIndexBean.setCarouselList(carouselService.getCarouselListView());

		return ecran;
	}

	public void uploadHandler(FileUploadEvent ev) {

		FileOutputStream fileOuputStream = null;
		this.uploadedFile = ev.getFile();
		String elementImageURL = ParfumUtils.getUniqueName(referentielBean.getUploadCarouselFolderPath(),
				uploadedFile.getFileName());
		File fileDest = new File(referentielBean.getUploadCarouselFolderPath() + File.separator + elementImageURL);
		this.carousel.setElementImageURL(elementImageURL);
		byte[] content = uploadedFile.getContents();
		try {
			fileOuputStream = new FileOutputStream(fileDest);
			fileOuputStream.write(content);
			fileOuputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileOuputStream != null) {
				try {
					fileOuputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
