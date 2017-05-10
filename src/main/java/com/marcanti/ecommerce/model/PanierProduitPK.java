/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author admin
 */
@Embeddable
public class PanierProduitPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    private long idPanier;
    @Basic(optional = false)
    @NotNull
    private long idProduit;

    public PanierProduitPK() {
    }

    public PanierProduitPK(long idPanier, long idProduit) {
        this.idPanier = idPanier;
        this.idProduit = idProduit;
    }

    public long getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(long idPanier) {
        this.idPanier = idPanier;
    }

    public long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(long idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPanier;
        hash += (int) idProduit;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PanierProduitPK)) {
            return false;
        }
        PanierProduitPK other = (PanierProduitPK) object;
        if (this.idPanier != other.idPanier) {
            return false;
        }
        if (this.idProduit != other.idProduit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parfum.ce.model.PanierProduitPK[ idPanier=" + idPanier + ", idProduit=" + idProduit + " ]";
    }
    
}
