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
@Table(name = "commande_individuelle_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommandeIndividuelleStatus.findAll", query = "SELECT c FROM CommandeIndividuelleStatus c")
    , @NamedQuery(name = "CommandeIndividuelleStatus.findByIdStatus", query = "SELECT c FROM CommandeIndividuelleStatus c WHERE c.idStatus = :idStatus")
    , @NamedQuery(name = "CommandeIndividuelleStatus.findByStatusLibelle", query = "SELECT c FROM CommandeIndividuelleStatus c WHERE c.statusLibelle = :statusLibelle")
    , @NamedQuery(name = "CommandeIndividuelleStatus.findByStatusCode", query = "SELECT c FROM CommandeIndividuelleStatus c WHERE c.statusCode = :statusCode")})
public class CommandeIndividuelleStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Short idStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String statusLibelle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String statusCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStatus")
    private Collection<CommandeIndividuelle> commandeIndividuelleCollection;

    public CommandeIndividuelleStatus() {
    }

    public CommandeIndividuelleStatus(Short idStatus) {
        this.idStatus = idStatus;
    }

    public CommandeIndividuelleStatus(Short idStatus, String statusLibelle, String statusCode) {
        this.idStatus = idStatus;
        this.statusLibelle = statusLibelle;
        this.statusCode = statusCode;
    }

    public Short getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Short idStatus) {
        this.idStatus = idStatus;
    }

    public String getStatusLibelle() {
        return statusLibelle;
    }

    public void setStatusLibelle(String statusLibelle) {
        this.statusLibelle = statusLibelle;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @XmlTransient
    public Collection<CommandeIndividuelle> getCommandeIndividuelleCollection() {
        return commandeIndividuelleCollection;
    }

    public void setCommandeIndividuelleCollection(Collection<CommandeIndividuelle> commandeIndividuelleCollection) {
        this.commandeIndividuelleCollection = commandeIndividuelleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatus != null ? idStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommandeIndividuelleStatus)) {
            return false;
        }
        CommandeIndividuelleStatus other = (CommandeIndividuelleStatus) object;
        if ((this.idStatus == null && other.idStatus != null) || (this.idStatus != null && !this.idStatus.equals(other.idStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parfum.ce.model.CommandeIndividuelleStatus[ idStatus=" + idStatus + " ]";
    }
    
}
