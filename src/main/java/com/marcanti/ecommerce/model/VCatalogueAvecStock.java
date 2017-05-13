/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */

@Table(name = "v_catalogue_avec_stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VCatalogueAvecStock.findAll", query = "SELECT v FROM VCatalogueAvecStock v")
    , @NamedQuery(name = "VCatalogueAvecStock.findByProduitPhotoURL", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.produitPhotoURL = :produitPhotoURL")
    , @NamedQuery(name = "VCatalogueAvecStock.findByMarqueNom", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.marqueNom = :marqueNom")
    , @NamedQuery(name = "VCatalogueAvecStock.findByProduitDescription", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.produitDescription = :produitDescription")
    , @NamedQuery(name = "VCatalogueAvecStock.findBySousCategorieNom", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.sousCategorieNom = :sousCategorieNom")
    , @NamedQuery(name = "VCatalogueAvecStock.findByNotrePrix", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.notrePrix = :notrePrix")
    , @NamedQuery(name = "VCatalogueAvecStock.findByPrixPublic", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.prixPublic = :prixPublic")
    , @NamedQuery(name = "VCatalogueAvecStock.findByProduitDetail", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.produitDetail = :produitDetail")
    , @NamedQuery(name = "VCatalogueAvecStock.findByCategorieNom", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.categorieNom = :categorieNom")
    , @NamedQuery(name = "VCatalogueAvecStock.findByCategorieCode", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.categorieCode = :categorieCode")
    , @NamedQuery(name = "VCatalogueAvecStock.findByIdProduit", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.idProduit = :idProduit")
    , @NamedQuery(name = "VCatalogueAvecStock.findByIdCategorie", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.idCategorie = :idCategorie")
    , @NamedQuery(name = "VCatalogueAvecStock.findByIdSousCategorie", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.idSousCategorie = :idSousCategorie")
    , @NamedQuery(name = "VCatalogueAvecStock.findByIdMarque", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.idMarque = :idMarque")
    , @NamedQuery(name = "VCatalogueAvecStock.findByNoteDeTete", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.noteDeTete = :noteDeTete")
    , @NamedQuery(name = "VCatalogueAvecStock.findByNoteDeCoeur", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.noteDeCoeur = :noteDeCoeur")
    , @NamedQuery(name = "VCatalogueAvecStock.findByNoteDeFond", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.noteDeFond = :noteDeFond")
    , @NamedQuery(name = "VCatalogueAvecStock.findByTitreADecouvrir", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.titreADecouvrir = :titreADecouvrir")
    , @NamedQuery(name = "VCatalogueAvecStock.findByDescriptionADecouvrir", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.descriptionADecouvrir = :descriptionADecouvrir")
    , @NamedQuery(name = "VCatalogueAvecStock.findByFamilleOlfactive", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.familleOlfactive = :familleOlfactive")
    , @NamedQuery(name = "VCatalogueAvecStock.findByIsFullAccess", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.isFullAccess = :isFullAccess")
    , @NamedQuery(name = "VCatalogueAvecStock.findByQteEnStock", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.qteEnStock = :qteEnStock")
    , @NamedQuery(name = "VCatalogueAvecStock.findByCategorieIconeURL", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.categorieIconeURL = :categorieIconeURL")
    , @NamedQuery(name = "VCatalogueAvecStock.findByDateDebutNouveaute", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.dateDebutNouveaute = :dateDebutNouveaute")
    , @NamedQuery(name = "VCatalogueAvecStock.findByDateFinNouveaute", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.dateFinNouveaute = :dateFinNouveaute")
    , @NamedQuery(name = "VCatalogueAvecStock.findByDateDebutADecouvrir", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.dateDebutADecouvrir = :dateDebutADecouvrir")
    , @NamedQuery(name = "VCatalogueAvecStock.findByDateFinADecouvrir", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.dateFinADecouvrir = :dateFinADecouvrir")
    , @NamedQuery(name = "VCatalogueAvecStock.findByDateDebutPromo", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.dateDebutPromo = :dateDebutPromo")
    , @NamedQuery(name = "VCatalogueAvecStock.findByDateFinPromo", query = "SELECT v FROM VCatalogueAvecStock v WHERE v.dateFinPromo = :dateFinPromo")})
public class VCatalogueAvecStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 512)
    private String produitPhotoURL;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    private String marqueNom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    private String produitDescription;
    @Size(max = 120)
    private String sousCategorieNom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    private BigDecimal notrePrix;
    @Basic(optional = false)
    @NotNull
    private BigDecimal prixPublic;
    @Size(max = 600)
    private String produitDetail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    private String categorieNom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String categorieCode;
    @Basic(optional = false)
    @NotNull
    private long idProduit;
    @Basic(optional = false)
    @NotNull
    private short idCategorie;
    @Basic(optional = false)
    @NotNull
    private short idSousCategorie;
    @Basic(optional = false)
    @NotNull
    private short idMarque;
    @Size(max = 80)
    private String noteDeTete;
    @Size(max = 80)
    private String noteDeCoeur;
    @Size(max = 80)
    private String noteDeFond;
    @Size(max = 80)
    private String titreADecouvrir;
    @Size(max = 600)
    private String descriptionADecouvrir;
    @Size(max = 45)
    private String familleOlfactive;
    @Basic(optional = false)
    @NotNull
    private boolean isFullAccess;
    @Basic(optional = false)
    @NotNull
    private short qteEnStock;
    @Size(max = 512)
    private String categorieIconeURL;
    @Temporal(TemporalType.DATE)
    private Date dateDebutNouveaute;
    @Temporal(TemporalType.DATE)
    private Date dateFinNouveaute;
    @Temporal(TemporalType.DATE)
    private Date dateDebutADecouvrir;
    @Temporal(TemporalType.DATE)
    private Date dateFinADecouvrir;
    @Temporal(TemporalType.DATE)
    private Date dateDebutPromo;
    @Temporal(TemporalType.DATE)
    private Date dateFinPromo;

    public VCatalogueAvecStock() {
    }

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
    
}
