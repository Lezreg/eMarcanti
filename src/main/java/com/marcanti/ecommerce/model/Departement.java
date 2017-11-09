/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name="departement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departement.findAll", query = "SELECT d FROM Departement d")
    , @NamedQuery(name = "Departement.findByIdDepartement", query = "SELECT d FROM Departement d WHERE d.idDepartement = :idDepartement")
    , @NamedQuery(name = "Departement.findByDepartementNom", query = "SELECT d FROM Departement d WHERE d.departementNom = :departementNom")
    , @NamedQuery(name = "Departement.findByIsActif", query = "SELECT d FROM Departement d WHERE d.isActif = :isActif")
    , @NamedQuery(name = "Departement.findByIdOrga", query = "SELECT d FROM Departement d WHERE d.idOrga = :idOrga")
    , @NamedQuery(name = "Departement.findByCommentaire", query = "SELECT d FROM Departement d WHERE d.commentaire = :commentaire")})
public class Departement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long idDepartement;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String departementNom;
    
    private Boolean isActif;
    
    @Size(max = 120)
    private String commentaire;
    
    @JoinColumn(name = "idOrga", referencedColumnName = "idOrga")
    @ManyToOne(optional = false)
    private Organisation idOrga;
    
    @OneToMany(mappedBy = "idDepartement")
    private Collection<Membre> membreCollection;

    public Departement() {
    }

    public Departement(Long idDepartement) {
        this.idDepartement = idDepartement;
    }

    public Departement(Long idDepartement, String departementNom) {
        this.idDepartement = idDepartement;
        this.departementNom = departementNom;
    }

    public Long getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(Long idDepartement) {
        this.idDepartement = idDepartement;
    }

    public String getDepartementNom() {
        return departementNom;
    }

    public void setDepartementNom(String departementNom) {
        this.departementNom = departementNom;
    }

    public Boolean getIsActif() {
        return isActif;
    }

    public void setIsActif(Boolean isActif) {
        this.isActif = isActif;
    }
    
    public String getIsActifStr() {
    	if (isActif) return "y";
    	else return "n";
    }    
    
    public String getIsActifStyle() {
    	if (isActif) return "greenclass fa fa-check";
    	else return "redclass fa fa-times";
    }    

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Organisation getIdOrga() {
        return idOrga;
    }

    public void setIdOrga(Organisation idOrga) {
        this.idOrga = idOrga;
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
        hash += (idDepartement != null ? idDepartement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departement)) {
            return false;
        }
        Departement other = (Departement) object;
        if ((this.idDepartement == null && other.idDepartement != null) || (this.idDepartement != null && !this.idDepartement.equals(other.idDepartement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parfum.ce.model.Departement[ idDepartement=" + idDepartement + " ]";
    }
    
}
