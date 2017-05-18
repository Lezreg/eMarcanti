/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
    , @NamedQuery(name = "Produit.findByIdProduit", query = "SELECT p FROM Produit p WHERE p.idProduit = :idProduit")
    , @NamedQuery(name = "Produit.findByProduitDescription", query = "SELECT p FROM Produit p WHERE p.produitDescription = :produitDescription")
    , @NamedQuery(name = "Produit.findByProduitDetail", query = "SELECT p FROM Produit p WHERE p.produitDetail = :produitDetail")
    , @NamedQuery(name = "Produit.findByNotrePrix", query = "SELECT p FROM Produit p WHERE p.notrePrix = :notrePrix")
    , @NamedQuery(name = "Produit.findByPrixPublic", query = "SELECT p FROM Produit p WHERE p.prixPublic = :prixPublic")
    , @NamedQuery(name = "Produit.findByQteEnStock", query = "SELECT p FROM Produit p WHERE p.qteEnStock = :qteEnStock")
    , @NamedQuery(name = "Produit.findByProduitPhotoURL", query = "SELECT p FROM Produit p WHERE p.produitPhotoURL = :produitPhotoURL")
    , @NamedQuery(name = "Produit.findByDateDebutPromo", query = "SELECT p FROM Produit p WHERE p.dateDebutPromo = :dateDebutPromo")
    , @NamedQuery(name = "Produit.findByDateFinPromo", query = "SELECT p FROM Produit p WHERE p.dateFinPromo = :dateFinPromo")
    , @NamedQuery(name = "Produit.findByDateDebutNouveaute", query = "SELECT p FROM Produit p WHERE p.dateDebutNouveaute = :dateDebutNouveaute")
    , @NamedQuery(name = "Produit.findByDateFinNouveaute", query = "SELECT p FROM Produit p WHERE p.dateFinNouveaute = :dateFinNouveaute")
    , @NamedQuery(name = "Produit.findByDateDebutADecouvrir", query = "SELECT p FROM Produit p WHERE p.dateDebutADecouvrir = :dateDebutADecouvrir")
    , @NamedQuery(name = "Produit.findByDateFinADecouvrir", query = "SELECT p FROM Produit p WHERE p.dateFinADecouvrir = :dateFinADecouvrir")
    , @NamedQuery(name = "Produit.findByTitreADecouvrir", query = "SELECT p FROM Produit p WHERE p.titreADecouvrir = :titreADecouvrir")
    , @NamedQuery(name = "Produit.findByNoteDeTete", query = "SELECT p FROM Produit p WHERE p.noteDeTete = :noteDeTete")
    , @NamedQuery(name = "Produit.findByNoteDeCoeur", query = "SELECT p FROM Produit p WHERE p.noteDeCoeur = :noteDeCoeur")
    , @NamedQuery(name = "Produit.findByNoteDeFond", query = "SELECT p FROM Produit p WHERE p.noteDeFond = :noteDeFond")
    , @NamedQuery(name = "Produit.findByFamilleOlfactive", query = "SELECT p FROM Produit p WHERE p.familleOlfactive = :familleOlfactive")
    , @NamedQuery(name = "Produit.findByDescriptionADecouvrir", query = "SELECT p FROM Produit p WHERE p.descriptionADecouvrir = :descriptionADecouvrir")
    , @NamedQuery(name = "Produit.findByMotCleADecouvrir", query = "SELECT p FROM Produit p WHERE p.motCleADecouvrir = :motCleADecouvrir")
    , @NamedQuery(name = "Produit.findByCodeEAN", query = "SELECT p FROM Produit p WHERE p.codeEAN = :codeEAN")})
@ManagedBean(name = "produit")
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long idProduit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    private String produitDescription;
    @Size(max = 600)
    private String produitDetail;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    private BigDecimal notrePrix;
    @Basic(optional = false)
    @NotNull
    private BigDecimal prixPublic;
    @Basic(optional = false)
    @NotNull
    private short qteEnStock;
    @Size(max = 512)
    private String produitPhotoURL;
    @Temporal(TemporalType.DATE)
    private Date dateDebutPromo;
    @Temporal(TemporalType.DATE)
    private Date dateFinPromo;
    @Temporal(TemporalType.DATE)
    private Date dateDebutNouveaute;
    @Temporal(TemporalType.DATE)
    private Date dateFinNouveaute;
    @Temporal(TemporalType.DATE)
    private Date dateDebutADecouvrir;
    @Temporal(TemporalType.DATE)
    private Date dateFinADecouvrir;
    @Size(max = 80)
    private String titreADecouvrir;
    @Size(max = 80)
    private String noteDeTete;
    @Size(max = 80)
    private String noteDeCoeur;
    @Size(max = 80)
    private String noteDeFond;
    @Size(max = 45)
    private String familleOlfactive;
    @Size(max = 600)
    private String descriptionADecouvrir;
    @Size(max = 45)
    private String motCleADecouvrir;
    @Size(max = 20)
    private String codeEAN;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produit")
    private Collection<PanierProduit> panierProduitCollection;
    @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
    @ManyToOne(optional = false)
    private Categorie idCategorie;
    @JoinColumn(name = "idMarque", referencedColumnName = "idMarque")
    @ManyToOne(optional = false)
    private Marque idMarque;
    @JoinColumn(name = "idSousCategorie", referencedColumnName = "idSousCategorie")
    @ManyToOne(optional = false)
    private SousCategorie idSousCategorie;

    public Produit() {
    }

    public Produit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public Produit(Long idProduit, String produitDescription, BigDecimal notrePrix, BigDecimal prixPublic, short qteEnStock) {
        this.idProduit = idProduit;
        this.produitDescription = produitDescription;
        this.notrePrix = notrePrix;
        this.prixPublic = prixPublic;
        this.qteEnStock = qteEnStock;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getProduitDescription() {
        return produitDescription;
    }

    public void setProduitDescription(String produitDescription) {
        this.produitDescription = produitDescription;
    }

    public String getProduitDetail() {
        return produitDetail;
    }

    public void setProduitDetail(String produitDetail) {
        this.produitDetail = produitDetail;
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

    public short getQteEnStock() {
        return qteEnStock;
    }

    public void setQteEnStock(short qteEnStock) {
        this.qteEnStock = qteEnStock;
    }

    public String getProduitPhotoURL() {
		if (produitPhotoURL == null) {
			return "";
		}
        return produitPhotoURL;
    }

    public void setProduitPhotoURL(String produitPhotoURL) {
        this.produitPhotoURL = produitPhotoURL;
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

    public String getTitreADecouvrir() {
        return titreADecouvrir;
    }

    public void setTitreADecouvrir(String titreADecouvrir) {
        this.titreADecouvrir = titreADecouvrir;
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

    public String getFamilleOlfactive() {
        return familleOlfactive;
    }

    public void setFamilleOlfactive(String familleOlfactive) {
        this.familleOlfactive = familleOlfactive;
    }

    public String getDescriptionADecouvrir() {
        return descriptionADecouvrir;
    }

    public void setDescriptionADecouvrir(String descriptionADecouvrir) {
        this.descriptionADecouvrir = descriptionADecouvrir;
    }

    public String getMotCleADecouvrir() {
        return motCleADecouvrir;
    }

    public void setMotCleADecouvrir(String motCleADecouvrir) {
        this.motCleADecouvrir = motCleADecouvrir;
    }

    public String getCodeEAN() {
        return codeEAN;
    }

    public void setCodeEAN(String codeEAN) {
        this.codeEAN = codeEAN;
    }

    @XmlTransient
    public Collection<PanierProduit> getPanierProduitCollection() {
        return panierProduitCollection;
    }

    public void setPanierProduitCollection(Collection<PanierProduit> panierProduitCollection) {
        this.panierProduitCollection = panierProduitCollection;
    }

    public Categorie getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Categorie idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Marque getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(Marque idMarque) {
        this.idMarque = idMarque;
    }

    public SousCategorie getIdSousCategorie() {
        return idSousCategorie;
    }

    public void setIdSousCategorie(SousCategorie idSousCategorie) {
        this.idSousCategorie = idSousCategorie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduit != null ? idProduit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.idProduit == null && other.idProduit != null) || (this.idProduit != null && !this.idProduit.equals(other.idProduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parfum.ce.model.Produit[ idProduit=" + idProduit + " ]";
    }
    
}
