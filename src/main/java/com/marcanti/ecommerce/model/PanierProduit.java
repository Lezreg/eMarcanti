/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "panier_produit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PanierProduit.findAll", query = "SELECT p FROM PanierProduit p")
    , @NamedQuery(name = "PanierProduit.findByIdPanier", query = "SELECT p FROM PanierProduit p WHERE p.panierProduitPK.idPanier = :idPanier")
    , @NamedQuery(name = "PanierProduit.findByIdProduit", query = "SELECT p FROM PanierProduit p WHERE p.panierProduitPK.idProduit = :idProduit")
    , @NamedQuery(name = "PanierProduit.findByQteSouhaitee", query = "SELECT p FROM PanierProduit p WHERE p.qteSouhaitee = :qteSouhaitee")})
public class PanierProduit implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PanierProduitPK panierProduitPK;
    @Basic(optional = false)
    @NotNull
    private short qteSouhaitee;
    @JoinColumn(name = "idPanier", referencedColumnName = "idPanier", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Panier panier;
    @JoinColumn(name = "idProduit", referencedColumnName = "idProduit", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produit produit;
	/** juste pour l'affichage **/
	@Transient
	private BigDecimal sousTotal;

	public BigDecimal getSousTotal() {
		return getProduit().getNotrePrix().multiply(new BigDecimal(getQteSouhaitee()));
	}

	public void setSousTotal(BigDecimal sousTotal) {
		this.sousTotal = sousTotal;
	}

	public PanierProduit() {
    }

    public PanierProduit(PanierProduitPK panierProduitPK) {
        this.panierProduitPK = panierProduitPK;
    }

    public PanierProduit(PanierProduitPK panierProduitPK, short qteSouhaitee) {
        this.panierProduitPK = panierProduitPK;
        this.qteSouhaitee = qteSouhaitee;
    }

    public PanierProduit(long idPanier, long idProduit) {
        this.panierProduitPK = new PanierProduitPK(idPanier, idProduit);
    }

    public PanierProduitPK getPanierProduitPK() {
        return panierProduitPK;
    }

    public void setPanierProduitPK(PanierProduitPK panierProduitPK) {
        this.panierProduitPK = panierProduitPK;
    }

    public short getQteSouhaitee() {
        return qteSouhaitee;
    }

    public void setQteSouhaitee(short qteSouhaitee) {
        this.qteSouhaitee = qteSouhaitee;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (panierProduitPK != null ? panierProduitPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PanierProduit)) {
            return false;
        }
        PanierProduit other = (PanierProduit) object;
        if ((this.panierProduitPK == null && other.panierProduitPK != null) || (this.panierProduitPK != null && !this.panierProduitPK.equals(other.panierProduitPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parfum.ce.model.PanierProduit[ panierProduitPK=" + panierProduitPK + " ]";
    }
    
}
