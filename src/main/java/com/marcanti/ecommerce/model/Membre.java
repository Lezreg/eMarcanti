/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Membre.findAll", query = "SELECT m FROM Membre m")
    , @NamedQuery(name = "Membre.findByIdMembre", query = "SELECT m FROM Membre m WHERE m.idMembre = :idMembre")
    , @NamedQuery(name = "Membre.findByMembreNom", query = "SELECT m FROM Membre m WHERE m.membreNom = :membreNom")
    , @NamedQuery(name = "Membre.findByMembrePrenom", query = "SELECT m FROM Membre m WHERE m.membrePrenom = :membrePrenom")
    , @NamedQuery(name = "Membre.findByMembreEmail", query = "SELECT m FROM Membre m WHERE m.membreEmail = :membreEmail")
    , @NamedQuery(name = "Membre.findByMembreTel", query = "SELECT m FROM Membre m WHERE m.membreTel = :membreTel")
    , @NamedQuery(name = "Membre.findByDateCreation", query = "SELECT m FROM Membre m WHERE m.dateCreation = :dateCreation")
    , @NamedQuery(name = "Membre.findByDateModification", query = "SELECT m FROM Membre m WHERE m.dateModification = :dateModification")
    , @NamedQuery(name = "Membre.findByHasReduc", query = "SELECT m FROM Membre m WHERE m.hasReduc = :hasReduc")
    , @NamedQuery(name = "Membre.findByIsActif", query = "SELECT m FROM Membre m WHERE m.isActif = :isActif")
    , @NamedQuery(name = "Membre.findByPassword", query = "SELECT m FROM Membre m WHERE m.password = :password")
    , @NamedQuery(name = "Membre.findByIsDefaultPassword", query = "SELECT m FROM Membre m WHERE m.isDefaultPassword = :isDefaultPassword")
    , @NamedQuery(name = "Membre.findByCodeVerificationPassword", query = "SELECT m FROM Membre m WHERE m.codeVerificationPassword = :codeVerificationPassword")
    , @NamedQuery(name = "Membre.findByDateDerniereConnexion", query = "SELECT m FROM Membre m WHERE m.dateDerniereConnexion = :dateDerniereConnexion")})
public class Membre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idMembre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String membreNom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String membrePrenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    private String membreEmail;
    @Size(max = 20)
    private String membreTel;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    @Basic(optional = false)
    @NotNull
    private boolean hasReduc;
    @Basic(optional = false)
    @NotNull
    private boolean isActif;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    private String password;
    @Basic(optional = false)
    @NotNull
    private boolean isDefaultPassword;
    private Integer codeVerificationPassword;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDerniereConnexion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMembre")
    private Collection<CommandeIndividuelle> commandeIndividuelleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMembreParrain")
    private Collection<Filleul> filleulCollection;
    @JoinColumn(name = "idDepartement", referencedColumnName = "idDepartement")
    @ManyToOne
    private Departement idDepartement;
    @JoinColumn(name = "idOrga", referencedColumnName = "idOrga")
    @ManyToOne(optional = false)
    private Organisation idOrga;
    @JoinColumn(name = "idProfil", referencedColumnName = "idProfil")
    @ManyToOne(optional = false)
    private Profil idProfil;
    
    private boolean isAuthenticated = false;

    public Membre() {
    }

    public Membre(Long idMembre) {
        this.idMembre = idMembre;
    }

    public Membre(Long idMembre, String membreNom, String membrePrenom, String membreEmail, Date dateCreation, boolean hasReduc, boolean isActif, String password, boolean isDefaultPassword) {
        this.idMembre = idMembre;
        this.membreNom = membreNom;
        this.membrePrenom = membrePrenom;
        this.membreEmail = membreEmail;
        this.dateCreation = dateCreation;
        this.hasReduc = hasReduc;
        this.isActif = isActif;
        this.password = password;
        this.isDefaultPassword = isDefaultPassword;
    }

    public Long getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Long idMembre) {
        this.idMembre = idMembre;
    }

    public String getMembreNom() {
        return membreNom;
    }

    public void setMembreNom(String membreNom) {
        this.membreNom = membreNom;
    }

    public String getMembrePrenom() {
        return membrePrenom;
    }

    public void setMembrePrenom(String membrePrenom) {
        this.membrePrenom = membrePrenom;
    }

    public String getMembreEmail() {
        return membreEmail;
    }

    public void setMembreEmail(String membreEmail) {
        this.membreEmail = membreEmail;
    }

    public String getMembreTel() {
        return membreTel;
    }

    public void setMembreTel(String membreTel) {
        this.membreTel = membreTel;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public boolean getHasReduc() {
        return hasReduc;
    }

    public void setHasReduc(boolean hasReduc) {
        this.hasReduc = hasReduc;
    }

    public boolean getIsActif() {
        return isActif;
    }

    public void setIsActif(boolean isActif) {
        this.isActif = isActif;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsDefaultPassword() {
        return isDefaultPassword;
    }

    public void setIsDefaultPassword(boolean isDefaultPassword) {
        this.isDefaultPassword = isDefaultPassword;
    }

    public Integer getCodeVerificationPassword() {
        return codeVerificationPassword;
    }

    public void setCodeVerificationPassword(Integer codeVerificationPassword) {
        this.codeVerificationPassword = codeVerificationPassword;
    }

    public Date getDateDerniereConnexion() {
        return dateDerniereConnexion;
    }

    public void setDateDerniereConnexion(Date dateDerniereConnexion) {
        this.dateDerniereConnexion = dateDerniereConnexion;
    }

    @XmlTransient
    public Collection<CommandeIndividuelle> getCommandeIndividuelleCollection() {
        return commandeIndividuelleCollection;
    }

    public void setCommandeIndividuelleCollection(Collection<CommandeIndividuelle> commandeIndividuelleCollection) {
        this.commandeIndividuelleCollection = commandeIndividuelleCollection;
    }

    @XmlTransient
    public Collection<Filleul> getFilleulCollection() {
        return filleulCollection;
    }

    public void setFilleulCollection(Collection<Filleul> filleulCollection) {
        this.filleulCollection = filleulCollection;
    }

    public Departement getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(Departement idDepartement) {
        this.idDepartement = idDepartement;
    }

    public Organisation getIdOrga() {
        return idOrga;
    }

    public void setIdOrga(Organisation idOrga) {
        this.idOrga = idOrga;
    }

    public Profil getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Profil idProfil) {
        this.idProfil = idProfil;
    }
    
    public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	public void setActif(boolean isActif) {
		this.isActif = isActif;
	}

	public void setDefaultPassword(boolean isDefaultPassword) {
		this.isDefaultPassword = isDefaultPassword;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idMembre != null ? idMembre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membre)) {
            return false;
        }
        Membre other = (Membre) object;
        if ((this.idMembre == null && other.idMembre != null) || (this.idMembre != null && !this.idMembre.equals(other.idMembre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parfum.ce.model.Membre[ idMembre=" + idMembre + " ]";
    }
    
}
