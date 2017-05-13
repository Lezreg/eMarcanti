/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
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

@Table(name = "v_export_excel_catalogue_avec_stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VExportExcelCatalogueAvecStock.findAll", query = "SELECT v FROM VExportExcelCatalogueAvecStock v")
    , @NamedQuery(name = "VExportExcelCatalogueAvecStock.findByMarque", query = "SELECT v FROM VExportExcelCatalogueAvecStock v WHERE v.marque = :marque")
    , @NamedQuery(name = "VExportExcelCatalogueAvecStock.findByDescription", query = "SELECT v FROM VExportExcelCatalogueAvecStock v WHERE v.description = :description")
    , @NamedQuery(name = "VExportExcelCatalogueAvecStock.findByGroupe", query = "SELECT v FROM VExportExcelCatalogueAvecStock v WHERE v.groupe = :groupe")
    , @NamedQuery(name = "VExportExcelCatalogueAvecStock.findByPrix", query = "SELECT v FROM VExportExcelCatalogueAvecStock v WHERE v.prix = :prix")
    , @NamedQuery(name = "VExportExcelCatalogueAvecStock.findByPrixSephoraouMarionnaud", query = "SELECT v FROM VExportExcelCatalogueAvecStock v WHERE v.prixSephoraouMarionnaud = :prixSephoraouMarionnaud")
    , @NamedQuery(name = "VExportExcelCatalogueAvecStock.findByIdProduit", query = "SELECT v FROM VExportExcelCatalogueAvecStock v WHERE v.idProduit = :idProduit")
    , @NamedQuery(name = "VExportExcelCatalogueAvecStock.findByCat\u00e9gorie", query = "SELECT v FROM VExportExcelCatalogueAvecStock v WHERE v.cat\u00e9gorie = :cat\u00e9gorie")})
public class VExportExcelCatalogueAvecStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    private String marque;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    private String description;
    @Size(max = 120)
    private String groupe;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    private BigDecimal prix;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Prix Sephora ou Marionnaud")
    private BigDecimal prixSephoraouMarionnaud;
    @Basic(optional = false)
    @NotNull
    private long idProduit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    private String catégorie;

    public VExportExcelCatalogueAvecStock() {
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public BigDecimal getPrixSephoraouMarionnaud() {
        return prixSephoraouMarionnaud;
    }

    public void setPrixSephoraouMarionnaud(BigDecimal prixSephoraouMarionnaud) {
        this.prixSephoraouMarionnaud = prixSephoraouMarionnaud;
    }

    public long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(long idProduit) {
        this.idProduit = idProduit;
    }

    public String getCatégorie() {
        return catégorie;
    }

    public void setCatégorie(String catégorie) {
        this.catégorie = catégorie;
    }
    
}
