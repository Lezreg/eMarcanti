package com.marcanti.ecommerce.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.marcanti.ecommerce.model.Produit;

public class ProduitBean {

	private String produitPhotoURL;
	private String produitPhotoTailleMediumURL;
	private String produitPhotoTailleLargeURL;
	private String marqueNom;
	private String produitDescription;
	private String sousCategorieNom;
	private BigDecimal notrePrix;
	private BigDecimal prixPublic;
	private String produitDetail;
	private String categorieNom;
	private String categorieCode;
	private long idProduit;
	private short idCategorie;
	private short idSousCategorie;
	private short idMarque;
	private String noteDeTete;
	private List<String> noteDeTeteList;
	private String noteDeCoeur;
	private List<String> noteDeCoeurList;
	private String noteDeFond;
	private List<String> noteDeFondList;
	private String titreADecouvrir;
	private String descriptionADecouvrir;
	private String familleOlfactive;
	private boolean isFullAccess;
	private short qteEnStock;
	private String categorieIconeURL;
	private Date dateDebutNouveaute;
	private Date dateFinNouveaute;
	private Date dateDebutADecouvrir;
	private Date dateFinADecouvrir;
	private Date dateDebutPromo;
	private Date dateFinPromo;

	private boolean isBestSellerHomme;
	private boolean isBestSellerFemme;
	private boolean isPetitPrix;
	private boolean isNouveauteSurHomePage;
	private boolean isPromoSurHomePage;
	private boolean isADecouvrirSurHomePage;

	private String categorieCodeCss;
	private String categorieNomCss;
	private String isNouveauteSurHomePageCss;
	private String isPromoSurHomePageCss;
	
	private String nouveauteSurHomePageIndexCss;
	private String promoSurHomePageIndexCss;

	private List<Produit> produitsAssocies;

	public String getProduitPhotoURL() {
		return produitPhotoURL;
	}

	public void setProduitPhotoURL(String produitPhotoURL) {
		this.produitPhotoURL = produitPhotoURL;
	}

	public String getMarqueNom() {
		return marqueNom;
	}

	public void setMarqueNom(String marqueNom) {
		this.marqueNom = marqueNom;
	}

	public String getProduitDescription() {
		return produitDescription;
	}

	public void setProduitDescription(String produitDescription) {
		this.produitDescription = produitDescription;
	}

	public String getSousCategorieNom() {
		return sousCategorieNom;
	}

	public void setSousCategorieNom(String sousCategorieNom) {
		this.sousCategorieNom = sousCategorieNom;
	}

	public BigDecimal getNotrePrix() {
		return notrePrix;
	}

	public void setNotrePrix(BigDecimal notrePrix) {
		this.notrePrix = notrePrix;
	}

	public BigDecimal getPrixPublic() {
		return prixPublic;
	}

	public void setPrixPublic(BigDecimal prixPublic) {
		this.prixPublic = prixPublic;
	}

	public String getProduitDetail() {
		return produitDetail;
	}

	public void setProduitDetail(String produitDetail) {
		this.produitDetail = produitDetail;
	}

	public String getCategorieNom() {
		return categorieNom;
	}

	public void setCategorieNom(String categorieNom) {
		this.categorieNom = categorieNom;
	}

	public String getCategorieCode() {
		return categorieCode;
	}

	public void setCategorieCode(String categorieCode) {
		this.categorieCode = categorieCode;
	}

	public long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	public short getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(short idCategorie) {
		this.idCategorie = idCategorie;
	}

	public short getIdSousCategorie() {
		return idSousCategorie;
	}

	public void setIdSousCategorie(short idSousCategorie) {
		this.idSousCategorie = idSousCategorie;
	}

	public short getIdMarque() {
		return idMarque;
	}

	public void setIdMarque(short idMarque) {
		this.idMarque = idMarque;
	}

	public String getNoteDeTete() {
		return noteDeTete;
	}

	public void setNoteDeTete(String noteDeTete) {
		this.noteDeTete = noteDeTete;
	}
	
	public List<String> getNoteDeTeteList() {
		this.noteDeTeteList = new ArrayList<String>();
		if(this.noteDeTete!=null){
			StringTokenizer st = new StringTokenizer(noteDeTete, ";");
			while (st.hasMoreElements()) {
				String note = (String) st.nextElement();
				noteDeTeteList.add(note);
			}
		}
		return this.noteDeTeteList;
	}

	public void setNoteDeTeteList(List<String> noteDeTeteList) {
		this.noteDeTeteList = noteDeTeteList;
	}

	public String getNoteDeCoeur() {
		return noteDeCoeur;
	}

	public void setNoteDeCoeur(String noteDeCoeur) {
		this.noteDeCoeur = noteDeCoeur;
	}
	
	public List<String> getNoteDeCoeurList() {
		this.noteDeCoeurList = new ArrayList<String>();
		if(this.noteDeCoeur!=null){
			StringTokenizer st = new StringTokenizer(noteDeCoeur, ";");
			while (st.hasMoreElements()) {
				String note = (String) st.nextElement();
				noteDeCoeurList.add(note);
			}
		}
		return this.noteDeCoeurList;
	}

	public void setNoteDeCoeurList(List<String> noteDeCoeurList) {
		this.noteDeCoeurList = noteDeCoeurList;
	}

	public String getNoteDeFond() {
		return noteDeFond;
	}

	public void setNoteDeFond(String noteDeFond) {
		this.noteDeFond = noteDeFond;
	}

	public List<String> getNoteDeFondList() {
		this.noteDeFondList = new ArrayList<String>();
		if(this.noteDeFond!=null){
			StringTokenizer st = new StringTokenizer(noteDeFond, ";");
			while (st.hasMoreElements()) {
				String note = (String) st.nextElement();
				noteDeFondList.add(note);
			}
		}
		return this.noteDeFondList;
	}

	public void setNoteDeFondList(List<String> noteDeFondList) {
		this.noteDeFondList = noteDeFondList;
	}

	public String getTitreADecouvrir() {
		return titreADecouvrir;
	}

	public void setTitreADecouvrir(String titreADecouvrir) {
		this.titreADecouvrir = titreADecouvrir;
	}

	public String getDescriptionADecouvrir() {
		return descriptionADecouvrir;
	}

	public void setDescriptionADecouvrir(String descriptionADecouvrir) {
		this.descriptionADecouvrir = descriptionADecouvrir;
	}

	public String getFamilleOlfactive() {
		return familleOlfactive;
	}

	public void setFamilleOlfactive(String familleOlfactive) {
		this.familleOlfactive = familleOlfactive;
	}

	public boolean getIsFullAccess() {
		return isFullAccess;
	}

	public void setIsFullAccess(boolean isFullAccess) {
		this.isFullAccess = isFullAccess;
	}

	public short getQteEnStock() {
		return qteEnStock;
	}

	public void setQteEnStock(short qteEnStock) {
		this.qteEnStock = qteEnStock;
	}

	public String getCategorieIconeURL() {
		return categorieIconeURL;
	}

	public void setCategorieIconeURL(String categorieIconeURL) {
		this.categorieIconeURL = categorieIconeURL;
	}

	public Date getDateDebutNouveaute() {
		return dateDebutNouveaute;
	}

	public void setDateDebutNouveaute(Date dateDebutNouveaute) {
		this.dateDebutNouveaute = dateDebutNouveaute;
	}

	public Date getDateFinNouveaute() {
		return dateFinNouveaute;
	}

	public void setDateFinNouveaute(Date dateFinNouveaute) {
		this.dateFinNouveaute = dateFinNouveaute;
	}

	public Date getDateDebutADecouvrir() {
		return dateDebutADecouvrir;
	}

	public void setDateDebutADecouvrir(Date dateDebutADecouvrir) {
		this.dateDebutADecouvrir = dateDebutADecouvrir;
	}

	public Date getDateFinADecouvrir() {
		return dateFinADecouvrir;
	}

	public void setDateFinADecouvrir(Date dateFinADecouvrir) {
		this.dateFinADecouvrir = dateFinADecouvrir;
	}

	public Date getDateDebutPromo() {
		return dateDebutPromo;
	}

	public void setDateDebutPromo(Date dateDebutPromo) {
		this.dateDebutPromo = dateDebutPromo;
	}

	public Date getDateFinPromo() {
		return dateFinPromo;
	}

	public void setDateFinPromo(Date dateFinPromo) {
		this.dateFinPromo = dateFinPromo;
	}

	public List<Produit> getProduitsAssocies() {
		return produitsAssocies;
	}

	public void setProduitsAssocies(List<Produit> produitsAssocies) {
		this.produitsAssocies = produitsAssocies;
	}

	public boolean isBestSellerHomme() {
		return isBestSellerHomme;
	}

	public void setBestSellerHomme(boolean isBestSellerHomme) {
		this.isBestSellerHomme = isBestSellerHomme;
	}

	public boolean isBestSellerFemme() {
		return isBestSellerFemme;
	}

	public void setBestSellerFemme(boolean isBestSellerFemme) {
		this.isBestSellerFemme = isBestSellerFemme;
	}

	public boolean isPetitPrix() {
		return isPetitPrix;
	}

	public void setPetitPrix(boolean isPetitPrix) {
		this.isPetitPrix = isPetitPrix;
	}

	public boolean isNouveauteSurHomePage() {
		return isNouveauteSurHomePage;
	}

	public void setNouveauteSurHomePage(boolean isNouveauteSurHomePage) {
		this.isNouveauteSurHomePage = isNouveauteSurHomePage;
	}

	public boolean isPromoSurHomePage() {
		return isPromoSurHomePage;
	}

	public void setPromoSurHomePage(boolean isPromoSurHomePage) {
		this.isPromoSurHomePage = isPromoSurHomePage;
	}

	public boolean isADecouvrirSurHomePage() {
		return isADecouvrirSurHomePage;
	}

	public void setADecouvrirSurHomePage(boolean isADecouvrirSurHomePage) {
		this.isADecouvrirSurHomePage = isADecouvrirSurHomePage;
	}

	public String getCategorieCodeCss() {
		return categorieCodeCss;
	}

	public void setCategorieCodeCss(String categorieCodeCss) {
		this.categorieCodeCss = categorieCodeCss;
	}

	public String getCategorieNomCss() {
		return categorieNomCss;
	}

	public void setCategorieNomCss(String categorieNomCss) {
		this.categorieNomCss = categorieNomCss;
	}

	public String getIsNouveauteSurHomePageCss() {
		return isNouveauteSurHomePageCss;
	}

	public void setIsNouveauteSurHomePageCss(String isNouveauteSurHomePageCss) {
		this.isNouveauteSurHomePageCss = isNouveauteSurHomePageCss;
	}

	public String getIsPromoSurHomePageCss() {
		return isPromoSurHomePageCss;
	}

	public void setIsPromoSurHomePageCss(String isPromoSurHomePageCss) {
		this.isPromoSurHomePageCss = isPromoSurHomePageCss;
	}

	public void setFullAccess(boolean isFullAccess) {
		this.isFullAccess = isFullAccess;
	}

	public String getProduitPhotoTailleMediumURL() {
		return produitPhotoTailleMediumURL;
	}

	public void setProduitPhotoTailleMediumURL(String produitPhotoTailleMediumURL) {
		this.produitPhotoTailleMediumURL = produitPhotoTailleMediumURL;
	}
	
	public String getProduitPhotoTailleLargeURL() {
		return produitPhotoTailleLargeURL;
	}

	public void setProduitPhotoTailleLargeURL(String produitPhotoTailleLargeURL) {
		this.produitPhotoTailleLargeURL = produitPhotoTailleLargeURL;
	}

	public String getNouveauteSurHomePageIndexCss() {
		return nouveauteSurHomePageIndexCss;
	}

	public void setNouveauteSurHomePageIndexCss(String nouveauteSurHomePageIndexCss) {
		this.nouveauteSurHomePageIndexCss = nouveauteSurHomePageIndexCss;
	}

	public String getPromoSurHomePageIndexCss() {
		return promoSurHomePageIndexCss;
	}

	public void setPromoSurHomePageIndexCss(String promoSurHomePageIndexCss) {
		this.promoSurHomePageIndexCss = promoSurHomePageIndexCss;
	}
	
	

}
