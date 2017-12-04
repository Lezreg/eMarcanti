package com.marcanti.ecommerce.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.marcanti.ecommerce.model.Produit;

public class ProduitBean {

	private String produitPhotoURL;
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
	private String noteDeCoeur;
	private String noteDeFond;
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

	public String getNoteDeCoeur() {
		return noteDeCoeur;
	}

	public void setNoteDeCoeur(String noteDeCoeur) {
		this.noteDeCoeur = noteDeCoeur;
	}

	public String getNoteDeFond() {
		return noteDeFond;
	}

	public void setNoteDeFond(String noteDeFond) {
		this.noteDeFond = noteDeFond;
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

}
