/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */

@Table(name = "v_rapport_cde_indiv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VRapportCdeIndiv.findAll", query = "SELECT v FROM VRapportCdeIndiv v")
    , @NamedQuery(name = "VRapportCdeIndiv.findByIdCdeGroupee", query = "SELECT v FROM VRapportCdeIndiv v WHERE v.idCdeGroupee = :idCdeGroupee")
    , @NamedQuery(name = "VRapportCdeIndiv.findByIdCdeIndiv", query = "SELECT v FROM VRapportCdeIndiv v WHERE v.idCdeIndiv = :idCdeIndiv")
    , @NamedQuery(name = "VRapportCdeIndiv.findByMembreNom", query = "SELECT v FROM VRapportCdeIndiv v WHERE v.membreNom = :membreNom")
    , @NamedQuery(name = "VRapportCdeIndiv.findByCdeIndivNom", query = "SELECT v FROM VRapportCdeIndiv v WHERE v.cdeIndivNom = :cdeIndivNom")
    , @NamedQuery(name = "VRapportCdeIndiv.findByCdeIndivMontant", query = "SELECT v FROM VRapportCdeIndiv v WHERE v.cdeIndivMontant = :cdeIndivMontant")
    , @NamedQuery(name = "VRapportCdeIndiv.findByCdeIndivEtat", query = "SELECT v FROM VRapportCdeIndiv v WHERE v.cdeIndivEtat = :cdeIndivEtat")
    , @NamedQuery(name = "VRapportCdeIndiv.findByReduction", query = "SELECT v FROM VRapportCdeIndiv v WHERE v.reduction = :reduction")
    , @NamedQuery(name = "VRapportCdeIndiv.findByTotalAPayer", query = "SELECT v FROM VRapportCdeIndiv v WHERE v.totalAPayer = :totalAPayer")
    , @NamedQuery(name = "VRapportCdeIndiv.findByIsPaiementEffectue", query = "SELECT v FROM VRapportCdeIndiv v WHERE v.isPaiementEffectue = :isPaiementEffectue")
    , @NamedQuery(name = "VRapportCdeIndiv.findByDateCreation", query = "SELECT v FROM VRapportCdeIndiv v WHERE v.dateCreation = :dateCreation")
    , @NamedQuery(name = "VRapportCdeIndiv.findByCdeIndivStatusCode", query = "SELECT v FROM VRapportCdeIndiv v WHERE v.cdeIndivStatusCode = :cdeIndivStatusCode")})
public class VRapportCdeIndiv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    private long idCdeGroupee;
    @Basic(optional = false)
    @NotNull
    private long idCdeIndiv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String membreNom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    private String cdeIndivNom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private BigDecimal cdeIndivMontant;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String cdeIndivEtat;
    private BigDecimal reduction;
    @Basic(optional = false)
    @NotNull
    private BigDecimal totalAPayer;
    @Basic(optional = false)
    @NotNull
    private boolean isPaiementEffectue;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String cdeIndivStatusCode;

    public VRapportCdeIndiv() {
    }

    public long getIdCdeGroupee() {
        return idCdeGroupee;
    }

    public void setIdCdeGroupee(long idCdeGroupee) {
        this.idCdeGroupee = idCdeGroupee;
    }

    public long getIdCdeIndiv() {
        return idCdeIndiv;
    }

    public void setIdCdeIndiv(long idCdeIndiv) {
        this.idCdeIndiv = idCdeIndiv;
    }

    public String getMembreNom() {
        return membreNom;
    }

    public void setMembreNom(String membreNom) {
        this.membreNom = membreNom;
    }

    public String getCdeIndivNom() {
        return cdeIndivNom;
    }

    public void setCdeIndivNom(String cdeIndivNom) {
        this.cdeIndivNom = cdeIndivNom;
    }

    public BigDecimal getCdeIndivMontant() {
        return cdeIndivMontant;
    }

    public void setCdeIndivMontant(BigDecimal cdeIndivMontant) {
        this.cdeIndivMontant = cdeIndivMontant;
    }

    public String getCdeIndivEtat() {
        return cdeIndivEtat;
    }

    public void setCdeIndivEtat(String cdeIndivEtat) {
        this.cdeIndivEtat = cdeIndivEtat;
    }

    public BigDecimal getReduction() {
        return reduction;
    }

    public void setReduction(BigDecimal reduction) {
        this.reduction = reduction;
    }

    public BigDecimal getTotalAPayer() {
        return totalAPayer;
    }

    public void setTotalAPayer(BigDecimal totalAPayer) {
        this.totalAPayer = totalAPayer;
    }

    public boolean getIsPaiementEffectue() {
        return isPaiementEffectue;
    }

    public void setIsPaiementEffectue(boolean isPaiementEffectue) {
        this.isPaiementEffectue = isPaiementEffectue;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCdeIndivStatusCode() {
        return cdeIndivStatusCode;
    }

    public void setCdeIndivStatusCode(String cdeIndivStatusCode) {
        this.cdeIndivStatusCode = cdeIndivStatusCode;
    }
    
}
