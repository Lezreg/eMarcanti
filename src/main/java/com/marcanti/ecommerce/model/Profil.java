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
    @NamedQuery(name = "Profil.findAll", query = "SELECT p FROM Profil p")
    , @NamedQuery(name = "Profil.findByIdProfil", query = "SELECT p FROM Profil p WHERE p.idProfil = :idProfil")
    , @NamedQuery(name = "Profil.findByProfilLibelle", query = "SELECT p FROM Profil p WHERE p.profilLibelle = :profilLibelle")
    , @NamedQuery(name = "Profil.findByProfilCode", query = "SELECT p FROM Profil p WHERE p.profilCode = :profilCode")})
public class Profil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Short idProfil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String profilLibelle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String profilCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProfil")
    private Collection<Membre> membreCollection;

    public Profil() {
    }

    public Profil(Short idProfil) {
        this.idProfil = idProfil;
    }

    public Profil(Short idProfil, String profilLibelle, String profilCode) {
        this.idProfil = idProfil;
        this.profilLibelle = profilLibelle;
        this.profilCode = profilCode;
    }

    public Short getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Short idProfil) {
        this.idProfil = idProfil;
    }

    public String getProfilLibelle() {
        return profilLibelle;
    }

    public void setProfilLibelle(String profilLibelle) {
        this.profilLibelle = profilLibelle;
    }

    public String getProfilCode() {
        return profilCode;
    }

    public void setProfilCode(String profilCode) {
        this.profilCode = profilCode;
    }

    @XmlTransient
    public Collection<Membre> getMembreCollection() {
        return membreCollection;
    }

    public void setMembreCollection(Collection<Membre> membreCollection) {
        this.membreCollection = membreCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfil != null ? idProfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profil)) {
            return false;
        }
        Profil other = (Profil) object;
        if ((this.idProfil == null && other.idProfil != null) || (this.idProfil != null && !this.idProfil.equals(other.idProfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parfum.ce.model.Profil[ idProfil=" + idProfil + " ]";
    }
    
}
