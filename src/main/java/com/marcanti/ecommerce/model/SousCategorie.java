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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "sous_categorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SousCategorie.findAll", query = "SELECT s FROM SousCategorie s")
    , @NamedQuery(name = "SousCategorie.findByIdSousCategorie", query = "SELECT s FROM SousCategorie s WHERE s.idSousCategorie = :idSousCategorie")
    , @NamedQuery(name = "SousCategorie.findByIdCategorie", query = "SELECT s FROM SousCategorie s WHERE s.idCategorieProduit = :idCategorieProduit")
    , @NamedQuery(name = "SousCategorie.findBySousCategorieNom", query = "SELECT s FROM SousCategorie s WHERE s.sousCategorieNom = :sousCategorieNom")})
public class SousCategorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    private Short idSousCategorie;
    
    @Size(max = 120)
    private String sousCategorieNom;
    
    @JoinColumn(name = "idCategorieProduit", referencedColumnName = "idCategorie")
    @ManyToOne(optional = false)
    private Categorie idCategorieProduit;
    
    @Transient
    private String categorieNom;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSousCategorie")
    private Collection<Produit> produitCollection;

    public SousCategorie() {
    }

    public SousCategorie(Short idSousCategorie) {
        this.idSousCategorie = idSousCategorie;
    }

    public Short getIdSousCategorie() {
        return idSousCategorie;
    }

    public void setIdSousCategorie(Short idSousCategorie) {
        this.idSousCategorie = idSousCategorie;
    }

    public String getSousCategorieNom() {
        return sousCategorieNom;
    }

    public void setSousCategorieNom(String sousCategorieNom) {
        this.sousCategorieNom = sousCategorieNom;
    }

    public Categorie getIdCategorieProduit() {
        return idCategorieProduit;
    }

    public void setIdCategorieProduit(Categorie idCategorieProduit) {
        this.idCategorieProduit = idCategorieProduit;
    }

    @XmlTransient
    public Collection<Produit> getProduitCollection() {
        return produitCollection;
    }

    public void setProduitCollection(Collection<Produit> produitCollection) {
        this.produitCollection = produitCollection;
    }
    

    public String getCategorieNom() {
		return categorieNom;
	}

	public void setCategorieNom(String categorieNom) {
		this.categorieNom = categorieNom;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idSousCategorie != null ? idSousCategorie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SousCategorie)) {
            return false;
        }
        SousCategorie other = (SousCategorie) object;
        if ((this.idSousCategorie == null && other.idSousCategorie != null) || (this.idSousCategorie != null && !this.idSousCategorie.equals(other.idSousCategorie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parfum.ce.model.SousCategorie[ idSousCategorie=" + idSousCategorie + " ]";
    }
    
}
