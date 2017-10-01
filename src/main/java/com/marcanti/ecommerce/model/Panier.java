/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
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
    @NamedQuery(name = "Panier.findAll", query = "SELECT p FROM Panier p")
    , @NamedQuery(name = "Panier.findByIdPanier", query = "SELECT p FROM Panier p WHERE p.idPanier = :idPanier")
    , @NamedQuery(name = "Panier.findByPanierNom", query = "SELECT p FROM Panier p WHERE p.panierNom = :panierNom")
    , @NamedQuery(name = "Panier.findByPanierMontant", query = "SELECT p FROM Panier p WHERE p.panierMontant = :panierMontant")
    , @NamedQuery(name = "Panier.findByPanierNbreProduit", query = "SELECT p FROM Panier p WHERE p.panierNbreProduit = :panierNbreProduit")})
public class Panier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long idPanier;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    private String panierNom;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    private BigDecimal panierMontant;
    
    @Basic(optional = false)
    @NotNull
    private short panierNbreProduit;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPanier")
    private CommandeIndividuelle commandeIndividuelle;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "panier")
    private Collection<PanierProduit> panierProduitCollection;

    public Panier() {
    }

    public Panier(Long idPanier) {
        this.idPanier = idPanier;
    }

    public Panier(Long idPanier, String panierNom, BigDecimal panierMontant, short panierNbreProduit) {
        this.idPanier = idPanier;
        this.panierNom = panierNom;
        this.panierMontant = panierMontant;
        this.panierNbreProduit = panierNbreProduit;
    }

    public Long getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(Long idPanier) {
        this.idPanier = idPanier;
    }

    public String getPanierNom() {
        return panierNom;
    }

    public void setPanierNom(String panierNom) {
        this.panierNom = panierNom;
    }

    public BigDecimal getPanierMontant() {
        return panierMontant;
    }

    public void setPanierMontant(BigDecimal panierMontant) {
        this.panierMontant = panierMontant;
    }

    public short getPanierNbreProduit() {
        return panierNbreProduit;
    }

    public void setPanierNbreProduit(short panierNbreProduit) {
        this.panierNbreProduit = panierNbreProduit;
    }

    public CommandeIndividuelle getCommandeIndividuelle() {
        return commandeIndividuelle;
    }

    public void setCommandeIndividuelle(CommandeIndividuelle commandeIndividuelle) {
        this.commandeIndividuelle = commandeIndividuelle;
    }

    @XmlTransient
    public Collection<PanierProduit> getPanierProduitCollection() {
        return panierProduitCollection;
    }

    public void setPanierProduitCollection(Collection<PanierProduit> panierProduitCollection) {
        this.panierProduitCollection = panierProduitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPanier != null ? idPanier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Panier)) {
            return false;
        }
        Panier other = (Panier) object;
        if ((this.idPanier == null && other.idPanier != null) || (this.idPanier != null && !this.idPanier.equals(other.idPanier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parfum.ce.model.Panier[ idPanier=" + idPanier + " ]";
    }
    
}
