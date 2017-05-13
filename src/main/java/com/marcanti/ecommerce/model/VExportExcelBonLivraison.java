/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Basic;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */

@Table(name = "v_export_excel_bon_livraison")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VExportExcelBonLivraison.findAll", query = "SELECT v FROM VExportExcelBonLivraison v")
    , @NamedQuery(name = "VExportExcelBonLivraison.findByIdCdeGroupee", query = "SELECT v FROM VExportExcelBonLivraison v WHERE v.idCdeGroupee = :idCdeGroupee")
    , @NamedQuery(name = "VExportExcelBonLivraison.findByIdCdeIndiv", query = "SELECT v FROM VExportExcelBonLivraison v WHERE v.idCdeIndiv = :idCdeIndiv")
    , @NamedQuery(name = "VExportExcelBonLivraison.findByIdMembre", query = "SELECT v FROM VExportExcelBonLivraison v WHERE v.idMembre = :idMembre")
    , @NamedQuery(name = "VExportExcelBonLivraison.findByCdeIndivNom", query = "SELECT v FROM VExportExcelBonLivraison v WHERE v.cdeIndivNom = :cdeIndivNom")
    , @NamedQuery(name = "VExportExcelBonLivraison.findByMembreNom", query = "SELECT v FROM VExportExcelBonLivraison v WHERE v.membreNom = :membreNom")
    , @NamedQuery(name = "VExportExcelBonLivraison.findByDepartementNom", query = "SELECT v FROM VExportExcelBonLivraison v WHERE v.departementNom = :departementNom")
    , @NamedQuery(name = "VExportExcelBonLivraison.findByDescription", query = "SELECT v FROM VExportExcelBonLivraison v WHERE v.description = :description")
    , @NamedQuery(name = "VExportExcelBonLivraison.findByPrixUnitaire", query = "SELECT v FROM VExportExcelBonLivraison v WHERE v.prixUnitaire = :prixUnitaire")
    , @NamedQuery(name = "VExportExcelBonLivraison.findByQteSouhaitee", query = "SELECT v FROM VExportExcelBonLivraison v WHERE v.qteSouhaitee = :qteSouhaitee")
    , @NamedQuery(name = "VExportExcelBonLivraison.findBySousTotal", query = "SELECT v FROM VExportExcelBonLivraison v WHERE v.sousTotal = :sousTotal")
    , @NamedQuery(name = "VExportExcelBonLivraison.findByParrainNom", query = "SELECT v FROM VExportExcelBonLivraison v WHERE v.parrainNom = :parrainNom")
    , @NamedQuery(name = "VExportExcelBonLivraison.findByIdMembreParrain", query = "SELECT v FROM VExportExcelBonLivraison v WHERE v.idMembreParrain = :idMembreParrain")})
public class VExportExcelBonLivraison implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    private long idCdeGroupee;
    @Basic(optional = false)
    @NotNull
    private long idCdeIndiv;
    @Basic(optional = false)
    @NotNull
    private long idMembre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    private String cdeIndivNom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String membreNom;
    @Size(max = 60)
    private String departementNom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    private BigDecimal prixUnitaire;
    @Basic(optional = false)
    @NotNull
    private short qteSouhaitee;
    @Basic(optional = false)
    @NotNull
    private BigDecimal sousTotal;
    @Size(max = 60)
    private String parrainNom;
    private BigInteger idMembreParrain;

    public VExportExcelBonLivraison() {
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

    public String getMembreNom() {
        return membreNom;
    }

    public void setMembreNom(String membreNom) {
        this.membreNom = membreNom;
    }

    public String getDepartementNom() {
        return departementNom;
    }

    public void setDepartementNom(String departementNom) {
        this.departementNom = departementNom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public short getQteSouhaitee() {
        return qteSouhaitee;
    }

    public void setQteSouhaitee(short qteSouhaitee) {
        this.qteSouhaitee = qteSouhaitee;
    }

    public BigDecimal getSousTotal() {
        return sousTotal;
    }

    public void setSousTotal(BigDecimal sousTotal) {
        this.sousTotal = sousTotal;
    }

    public String getParrainNom() {
        return parrainNom;
    }

    public void setParrainNom(String parrainNom) {
        this.parrainNom = parrainNom;
    }

    public BigInteger getIdMembreParrain() {
        return idMembreParrain;
    }

    public void setIdMembreParrain(BigInteger idMembreParrain) {
        this.idMembreParrain = idMembreParrain;
    }
    
}
