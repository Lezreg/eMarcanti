/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */

@Table(name = "v_reporting_cde_groupee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VReportingCdeGroupee.findAll", query = "SELECT v FROM VReportingCdeGroupee v")
    , @NamedQuery(name = "VReportingCdeGroupee.findByIdCdeGroupee", query = "SELECT v FROM VReportingCdeGroupee v WHERE v.idCdeGroupee = :idCdeGroupee")
    , @NamedQuery(name = "VReportingCdeGroupee.findByCdeGroupeeNom", query = "SELECT v FROM VReportingCdeGroupee v WHERE v.cdeGroupeeNom = :cdeGroupeeNom")
    , @NamedQuery(name = "VReportingCdeGroupee.findByDateCreation", query = "SELECT v FROM VReportingCdeGroupee v WHERE v.dateCreation = :dateCreation")
    , @NamedQuery(name = "VReportingCdeGroupee.findByCdeGoupeeEtat", query = "SELECT v FROM VReportingCdeGroupee v WHERE v.cdeGoupeeEtat = :cdeGoupeeEtat")
    , @NamedQuery(name = "VReportingCdeGroupee.findByCdeGroupeeMontantTotal", query = "SELECT v FROM VReportingCdeGroupee v WHERE v.cdeGroupeeMontantTotal = :cdeGroupeeMontantTotal")
    , @NamedQuery(name = "VReportingCdeGroupee.findByMontantPaye", query = "SELECT v FROM VReportingCdeGroupee v WHERE v.montantPaye = :montantPaye")
    , @NamedQuery(name = "VReportingCdeGroupee.findByNbCdeIndiv", query = "SELECT v FROM VReportingCdeGroupee v WHERE v.nbCdeIndiv = :nbCdeIndiv")
    , @NamedQuery(name = "VReportingCdeGroupee.findByNbCdeIndivPayees", query = "SELECT v FROM VReportingCdeGroupee v WHERE v.nbCdeIndivPayees = :nbCdeIndivPayees")
    , @NamedQuery(name = "VReportingCdeGroupee.findByCdeGroupeeResteAPayer", query = "SELECT v FROM VReportingCdeGroupee v WHERE v.cdeGroupeeResteAPayer = :cdeGroupeeResteAPayer")
    , @NamedQuery(name = "VReportingCdeGroupee.findByIsPaiementEffectue", query = "SELECT v FROM VReportingCdeGroupee v WHERE v.isPaiementEffectue = :isPaiementEffectue")
    , @NamedQuery(name = "VReportingCdeGroupee.findByDateModification", query = "SELECT v FROM VReportingCdeGroupee v WHERE v.dateModification = :dateModification")
    , @NamedQuery(name = "VReportingCdeGroupee.findByDateLivraisonPrevue", query = "SELECT v FROM VReportingCdeGroupee v WHERE v.dateLivraisonPrevue = :dateLivraisonPrevue")
    , @NamedQuery(name = "VReportingCdeGroupee.findByDateLivraisonReelle", query = "SELECT v FROM VReportingCdeGroupee v WHERE v.dateLivraisonReelle = :dateLivraisonReelle")
    , @NamedQuery(name = "VReportingCdeGroupee.findByIdStatus", query = "SELECT v FROM VReportingCdeGroupee v WHERE v.idStatus = :idStatus")})
public class VReportingCdeGroupee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    private long idCdeGroupee;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String cdeGroupeeNom;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String cdeGoupeeEtat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private BigDecimal cdeGroupeeMontantTotal;
    private BigDecimal montantPaye;
    @Basic(optional = false)
    @NotNull
    private long nbCdeIndiv;
    private BigInteger nbCdeIndivPayees;
    private BigDecimal cdeGroupeeResteAPayer;
    @Basic(optional = false)
    @NotNull
    private boolean isPaiementEffectue;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    @Temporal(TemporalType.DATE)
    private Date dateLivraisonPrevue;
    @Temporal(TemporalType.DATE)
    private Date dateLivraisonReelle;
    @Basic(optional = false)
    @NotNull
    private short idStatus;
    

    public VReportingCdeGroupee() {
    }

    public long getIdCdeGroupee() {
        return idCdeGroupee;
    }

    public void setIdCdeGroupee(long idCdeGroupee) {
        this.idCdeGroupee = idCdeGroupee;
    }

    public String getCdeGroupeeNom() {
        return cdeGroupeeNom;
    }

    public void setCdeGroupeeNom(String cdeGroupeeNom) {
        this.cdeGroupeeNom = cdeGroupeeNom;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCdeGoupeeEtat() {
        return cdeGoupeeEtat;
    }

    public void setCdeGoupeeEtat(String cdeGoupeeEtat) {
        this.cdeGoupeeEtat = cdeGoupeeEtat;
    }

    public BigDecimal getCdeGroupeeMontantTotal() {
        return cdeGroupeeMontantTotal;
    }

    public void setCdeGroupeeMontantTotal(BigDecimal cdeGroupeeMontantTotal) {
        this.cdeGroupeeMontantTotal = cdeGroupeeMontantTotal;
    }

    public BigDecimal getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(BigDecimal montantPaye) {
        this.montantPaye = montantPaye;
    }

    public long getNbCdeIndiv() {
        return nbCdeIndiv;
    }

    public void setNbCdeIndiv(long nbCdeIndiv) {
        this.nbCdeIndiv = nbCdeIndiv;
    }

    public BigInteger getNbCdeIndivPayees() {
        return nbCdeIndivPayees;
    }

    public void setNbCdeIndivPayees(BigInteger nbCdeIndivPayees) {
        this.nbCdeIndivPayees = nbCdeIndivPayees;
    }

    public BigDecimal getCdeGroupeeResteAPayer() {
        return cdeGroupeeResteAPayer;
    }

    public void setCdeGroupeeResteAPayer(BigDecimal cdeGroupeeResteAPayer) {
        this.cdeGroupeeResteAPayer = cdeGroupeeResteAPayer;
    }

    public boolean getIsPaiementEffectue() {
        return isPaiementEffectue;
    }

    public void setIsPaiementEffectue(boolean isPaiementEffectue) {
        this.isPaiementEffectue = isPaiementEffectue;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public Date getDateLivraisonPrevue() {
        return dateLivraisonPrevue;
    }

    public void setDateLivraisonPrevue(Date dateLivraisonPrevue) {
        this.dateLivraisonPrevue = dateLivraisonPrevue;
    }

    public Date getDateLivraisonReelle() {
        return dateLivraisonReelle;
    }

    public void setDateLivraisonReelle(Date dateLivraisonReelle) {
        this.dateLivraisonReelle = dateLivraisonReelle;
    }

    public short getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(short idStatus) {
        this.idStatus = idStatus;
    }
    
}
