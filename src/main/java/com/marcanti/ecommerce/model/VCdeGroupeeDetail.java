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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */

@Table(name = "v_cde_groupee_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VCdeGroupeeDetail.findAll", query = "SELECT v FROM VCdeGroupeeDetail v")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByDepartementNom", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.departementNom = :departementNom")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByMembreTel", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.membreTel = :membreTel")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByMembreNom", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.membreNom = :membreNom")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByMembrePrenom", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.membrePrenom = :membrePrenom")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByParrainNom", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.parrainNom = :parrainNom")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByCdeIndivMontant", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.cdeIndivMontant = :cdeIndivMontant")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByMontantAPayer", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.montantAPayer = :montantAPayer")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByCdeIndivEtat", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.cdeIndivEtat = :cdeIndivEtat")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByCdeIndivStatusCode", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.cdeIndivStatusCode = :cdeIndivStatusCode")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByIsPaiementEffectue", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.isPaiementEffectue = :isPaiementEffectue")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByCdeIndivDateCreation", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.cdeIndivDateCreation = :cdeIndivDateCreation")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByReduction", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.reduction = :reduction")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByIdMembre", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.idMembre = :idMembre")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByCdeIndivNom", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.cdeIndivNom = :cdeIndivNom")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByIdCdeGroupee", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.idCdeGroupee = :idCdeGroupee")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByIdCdeIndiv", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.idCdeIndiv = :idCdeIndiv")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByCdeGroupeeIdStatus", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.cdeGroupeeIdStatus = :cdeGroupeeIdStatus")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByCdeGroupeeDateModification", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.cdeGroupeeDateModification = :cdeGroupeeDateModification")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByCdeGroupeePrenomModifieur", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.cdeGroupeePrenomModifieur = :cdeGroupeePrenomModifieur")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByCdeGroupeeNomModifieur", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.cdeGroupeeNomModifieur = :cdeGroupeeNomModifieur")
    , @NamedQuery(name = "VCdeGroupeeDetail.findByIdMembreParrain", query = "SELECT v FROM VCdeGroupeeDetail v WHERE v.idMembreParrain = :idMembreParrain")})
public class VCdeGroupeeDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 60)
    private String departementNom;
    @Size(max = 20)
    private String membreTel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String membreNom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String membrePrenom;
    @Size(max = 60)
    private String parrainNom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private BigDecimal cdeIndivMontant;
    @Basic(optional = false)
    @NotNull
    private BigDecimal montantAPayer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String cdeIndivEtat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String cdeIndivStatusCode;
    @Basic(optional = false)
    @NotNull
    private boolean isPaiementEffectue;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdeIndivDateCreation;
    private BigDecimal reduction;
    @Basic(optional = false)
    @NotNull
    private long idMembre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    private String cdeIndivNom;
    @Basic(optional = false)
    @NotNull
    private long idCdeGroupee;
    @Basic(optional = false)
    @NotNull
    private long idCdeIndiv;
    @Basic(optional = false)
    @NotNull
    private short cdeGroupeeIdStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdeGroupeeDateModification;
    @Size(max = 60)
    private String cdeGroupeePrenomModifieur;
    @Size(max = 60)
    private String cdeGroupeeNomModifieur;
    private BigInteger idMembreParrain;

    public VCdeGroupeeDetail() {
    }

    public String getDepartementNom() {
        return departementNom;
    }

    public void setDepartementNom(String departementNom) {
        this.departementNom = departementNom;
    }

    public String getMembreTel() {
        return membreTel;
    }

    public void setMembreTel(String membreTel) {
        this.membreTel = membreTel;
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

    public String getParrainNom() {
        return parrainNom;
    }

    public void setParrainNom(String parrainNom) {
        this.parrainNom = parrainNom;
    }

    public BigDecimal getCdeIndivMontant() {
        return cdeIndivMontant;
    }

    public void setCdeIndivMontant(BigDecimal cdeIndivMontant) {
        this.cdeIndivMontant = cdeIndivMontant;
    }

    public BigDecimal getMontantAPayer() {
        return montantAPayer;
    }

    public void setMontantAPayer(BigDecimal montantAPayer) {
        this.montantAPayer = montantAPayer;
    }

    public String getCdeIndivEtat() {
        return cdeIndivEtat;
    }

    public void setCdeIndivEtat(String cdeIndivEtat) {
        this.cdeIndivEtat = cdeIndivEtat;
    }

    public String getCdeIndivStatusCode() {
        return cdeIndivStatusCode;
    }

    public void setCdeIndivStatusCode(String cdeIndivStatusCode) {
        this.cdeIndivStatusCode = cdeIndivStatusCode;
    }

    public boolean getIsPaiementEffectue() {
        return isPaiementEffectue;
    }

    public void setIsPaiementEffectue(boolean isPaiementEffectue) {
        this.isPaiementEffectue = isPaiementEffectue;
    }

    public Date getCdeIndivDateCreation() {
        return cdeIndivDateCreation;
    }

    public void setCdeIndivDateCreation(Date cdeIndivDateCreation) {
        this.cdeIndivDateCreation = cdeIndivDateCreation;
    }

    public BigDecimal getReduction() {
        return reduction;
    }

    public void setReduction(BigDecimal reduction) {
        this.reduction = reduction;
    }

    public long getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(long idMembre) {
        this.idMembre = idMembre;
    }

    public String getCdeIndivNom() {
        return cdeIndivNom;
    }

    public void setCdeIndivNom(String cdeIndivNom) {
        this.cdeIndivNom = cdeIndivNom;
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

    public short getCdeGroupeeIdStatus() {
        return cdeGroupeeIdStatus;
    }

    public void setCdeGroupeeIdStatus(short cdeGroupeeIdStatus) {
        this.cdeGroupeeIdStatus = cdeGroupeeIdStatus;
    }

    public Date getCdeGroupeeDateModification() {
        return cdeGroupeeDateModification;
    }

    public void setCdeGroupeeDateModification(Date cdeGroupeeDateModification) {
        this.cdeGroupeeDateModification = cdeGroupeeDateModification;
    }

    public String getCdeGroupeePrenomModifieur() {
        return cdeGroupeePrenomModifieur;
    }

    public void setCdeGroupeePrenomModifieur(String cdeGroupeePrenomModifieur) {
        this.cdeGroupeePrenomModifieur = cdeGroupeePrenomModifieur;
    }

    public String getCdeGroupeeNomModifieur() {
        return cdeGroupeeNomModifieur;
    }

    public void setCdeGroupeeNomModifieur(String cdeGroupeeNomModifieur) {
        this.cdeGroupeeNomModifieur = cdeGroupeeNomModifieur;
    }

    public BigInteger getIdMembreParrain() {
        return idMembreParrain;
    }

    public void setIdMembreParrain(BigInteger idMembreParrain) {
        this.idMembreParrain = idMembreParrain;
    }
    
}
