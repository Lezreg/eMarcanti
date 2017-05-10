/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Filleul.findAll", query = "SELECT f FROM Filleul f")
    , @NamedQuery(name = "Filleul.findByIdFilleul", query = "SELECT f FROM Filleul f WHERE f.idFilleul = :idFilleul")
    , @NamedQuery(name = "Filleul.findByParrainNom", query = "SELECT f FROM Filleul f WHERE f.parrainNom = :parrainNom")})
public class Filleul implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idFilleul;
    @Size(max = 60)
    private String parrainNom;
    @JoinColumn(name = "idMembreParrain", referencedColumnName = "idMembre")
    @ManyToOne(optional = false)
    private Membre idMembreParrain;

    public Filleul() {
    }

    public Filleul(Long idFilleul) {
        this.idFilleul = idFilleul;
    }

    public Long getIdFilleul() {
        return idFilleul;
    }

    public void setIdFilleul(Long idFilleul) {
        this.idFilleul = idFilleul;
    }

    public String getParrainNom() {
        return parrainNom;
    }

    public void setParrainNom(String parrainNom) {
        this.parrainNom = parrainNom;
    }

    public Membre getIdMembreParrain() {
        return idMembreParrain;
    }

    public void setIdMembreParrain(Membre idMembreParrain) {
        this.idMembreParrain = idMembreParrain;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFilleul != null ? idFilleul.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filleul)) {
            return false;
        }
        Filleul other = (Filleul) object;
        if ((this.idFilleul == null && other.idFilleul != null) || (this.idFilleul != null && !this.idFilleul.equals(other.idFilleul))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parfum.ce.model.Filleul[ idFilleul=" + idFilleul + " ]";
    }
    
}
