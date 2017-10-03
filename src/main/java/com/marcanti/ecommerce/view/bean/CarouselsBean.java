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
import com.marcanti.ecommerce.service.actions.CarouselServiceAction;
import com.marcanti.ecommerce.utils.ParfumUtils;

@ManagedBean(name = "carouselsBean")
@SessionScoped
public class CarouselsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(CarouselsBean.class);

	private List<Carousel> carouselList;

	private List<Carousel> filteredCarouselList;

	private Carousel carousel;
	
	//public String messageFileSize  = "";

	private UploadedFile uploadedFile;
	
	private String oldRangItem;

	//@ManagedProperty("#{param.idCarousel}")
	private Integer idCarousel;

	@ManagedProperty("#{carouselService}")
	private CarouselServiceAction carouselService;

	@ManagedProperty("#{referentielBean}")
	private ReferentielBean referentielBean;

	public CarouselsBean() {
	}

	@PostConstruct
	public void init() {
		this.carouselList = carouselService.getCarouselList();
		this.carousel = new Carousel(0);
	}
	
	public String getElementRang() {
		if(String.valueOf(this.carousel.getElementRang()).equals("0")){
			return "";
		}else
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

	public List<Carousel> getFilteredCarouselList() {
		return filteredCarouselList;
	}

	public void setFilteredCarouselList(List<Carousel> filteredCarouselList) {
		this.filteredCarouselList = filteredCarouselList;
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

	public CarouselServiceAction getCarouselService() {
		return carouselService;
	}

	public void setCarouselService(CarouselServiceAction carouselService) {
		this.carouselService = carouselService;
	}

	public String editCarousel() {

		Carousel carousel = new Carousel(getIdCarousel());
		carousel = carouselService.getCarousel(carousel);
		setCarousel(carousel);
		setOldRangItem(String.valueOf(carousel.getElementRang()));
		return "carousel";
	}

	public String addCarouselView() {
		logger.info("addCarouselView");
		this.carousel = new Carousel(0);
		this.uploadedFile = null;
		return "carousel";

	}

	public String insertOrUpdateCarousel() {

		FacesMessage facesMessage = new FacesMessage();
		String msg;
		String ecran = "carousel";

		logger.info("insertOrUpdateCarousel");
		
		if(!getOldRangItem().equals(getElementRang()) && carouselService.isRangExist(carousel)){
			msg = MessageFormat.format(ParfumUtils.getBundleApplication().getString("libelle_Erreur_rang"),String.valueOf(getElementRang()));
			facesMessage.setSummary(msg);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			return ecran;
		}
		
		if (this.carousel.getIdCarousel() == null || this.carousel.getIdCarousel().shortValue() == 0) {
			
			if(this.uploadedFile==null){
				msg = ParfumUtils.getBundleApplication().getString("libelle_Erreur_upload");
				facesMessage.setSummary(msg);
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				return ecran;
			}

			carouselService.insertCarousel(carousel);
			this.carouselList = carouselService.getCarouselList();
			msg = ParfumUtils.getBundleApplication().getString("message.ajouter.carousel");
			ecran = "carousels";

		} else {

			carouselService.updateCarousel(this.carousel);
			msg = ParfumUtils.getBundleApplication().getString("message.modif.carousel");
		}
		facesMessage.setSummary(msg);
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		
		CarouselsBean carouselsBean =  (CarouselsBean)ParfumUtils.getSessionObject("carouselsBean");
		carouselsBean.setCarouselList(carouselService.getCarouselList());
		
		return ecran;
	}

	public void uploadHandler(FileUploadEvent ev) {

		FileOutputStream fileOuputStream=null;
		this.uploadedFile = ev.getFile();
		String elementImageURL = ParfumUtils.getUniqueName(referentielBean.getUploadFolderPath(), uploadedFile.getFileName());
		File fileDest = new File(referentielBean.getUploadFolderPath()+File.separator+elementImageURL);
		this.carousel.setElementImageURL(elementImageURL);
		byte[] content = uploadedFile.getContents();
		try {
			fileOuputStream = new FileOutputStream(fileDest);
			fileOuputStream.write(content);
			fileOuputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fileOuputStream!=null) {
				try {
					fileOuputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
