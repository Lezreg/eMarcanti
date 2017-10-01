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
    @NamedQuery(name = "Marque.findAll", query = "SELECT m FROM Marque m")
    , @NamedQuery(name = "Marque.findByIdMarque", query = "SELECT m FROM Marque m WHERE m.idMarque = :idMarque")
    , @NamedQuery(name = "Marque.findByMarqueNom", query = "SELECT m FROM Marque m WHERE m.marqueNom = :marqueNom")
    , @NamedQuery(name = "Marque.findByCommentaire", query = "SELECT m FROM Marque m WHERE m.commentaire = :commentaire")
    , @NamedQuery(name = "Marque.findByIsFullAccess", query = "SELECT m FROM Marque m WHERE m.isFullAccess = :isFullAccess")})
public class Marque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Short idMarque;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    private String marqueNom;
    @Size(max = 200)
    private String commentaire;
    @Basic(optional = false)
    @NotNull
    private boolean isFullAccess;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMarque")
    private Collection<Produit> produitCollection;

    public Marque() {
    }

    public Marque(Short idMarque) {
        this.idMarque = idMarque;
    }

    public Marque(Short idMarque, String marqueNom, boolean isFullAccess) {
        this.idMarque = idMarque;
        this.marqueNom = marqueNom;
        this.isFullAccess = isFullAccess;
    }

    public Short getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(Short idMarque) {
        this.idMarque = idMarque;
    }

    public String getMarqueNom() {
        return marqueNom;
    }

    public void setMarqueNom(String marqueNom) {
        this.marqueNom = marqueNom;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public boolean getIsFullAccess() {
        return isFullAccess;
    }

    public void setIsFullAccess(boolean isFullAccess) {
        this.isFullAccess = isFullAccess;
    }
    
    public String getIsFullAccessStr() {
    	if (isFullAccess) return "y";
    	else return "n";
    }   
    
    public String getIsFullAccessStyle() {
    	if (isFullAccess) return "greenclass fa fa-check";
    	else return "redclass fa fa-times";
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
        hash += (idMarque != null ? idMarque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marque)) {
            return false;
        }
        Marque other = (Marque) object;
        if ((this.idMarque == null && other.idMarque != null) || (this.idMarque != null && !this.idMarque.equals(other.idMarque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parfum.ce.model.Marque[ idMarque=" + idMarque + " ]";
    }
    
}
