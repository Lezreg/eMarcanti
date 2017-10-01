/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c")
    , @NamedQuery(name = "Categorie.findByIdCategorie", query = "SELECT c FROM Categorie c WHERE c.idCategorie = :idCategorie")
    , @NamedQuery(name = "Categorie.findByCategorieNom", query = "SELECT c FROM Categorie c WHERE c.categorieNom = :categorieNom")
    , @NamedQuery(name = "Categorie.findByCategorieCode", query = "SELECT c FROM Categorie c WHERE c.categorieCode = :categorieCode")
    , @NamedQuery(name = "Categorie.findByCategorieIconeURL", query = "SELECT c FROM Categorie c WHERE c.categorieIconeURL = :categorieIconeURL")})
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    private Short idCategorie;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    private String categorieNom;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String categorieCode;
    
    @Size(max = 512)
    private String categorieIconeURL;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategorieProduit")
    private Collection<SousCategorie> sousCategorieCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategorie")
    private Collection<Produit> produitCollection;

    public Categorie() {
    }

    public Categorie(Short idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Categorie(Short idCategorie, String categorieNom, String categorieCode) {
        this.idCategorie = idCategorie;
        this.categorieNom = categorieNom;
        this.categorieCode = categorieCode;
    }

    public Short getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Short idCategorie) {
        this.idCategorie = idCategorie;
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

    public String getCategorieIconeURL() {
        return categorieIconeURL;
    }

    public void setCategorieIconeURL(String categorieIconeURL) {
        this.categorieIconeURL = categorieIconeURL;
    }

    @XmlTransient
    public Collection<SousCategorie> getSousCategorieCollection() {
        return sousCategorieCollection;
    }

    public void setSousCategorieCollection(Collection<SousCategorie> sousCategorieCollection) {
        this.sousCategorieCollection = sousCategorieCollection;
    }

    @XmlTransient
    public Collection<Produit> getProduitCollection() {
        return produitCollection;
    }

    public void setProduitCollection(Collection<Produit> produitCollection) {
        this.produitCollection = produitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategorie != null ? idCategorie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.idCategorie == null && other.idCategorie != null) || (this.idCategorie != null && !this.idCategorie.equals(other.idCategorie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parfum.ce.model.Categorie[ idCategorie=" + idCategorie + " ]";
    }
    
}
