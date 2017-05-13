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

@Table(name = "v_export_excel_catalogue_restreint_avec_stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VExportExcelCatalogueRestreintAvecStock.findAll", query = "SELECT v FROM VExportExcelCatalogueRestreintAvecStock v")
    , @NamedQuery(name = "VExportExcelCatalogueRestreintAvecStock.findByMarque", query = "SELECT v FROM VExportExcelCatalogueRestreintAvecStock v WHERE v.marque = :marque")
    , @NamedQuery(name = "VExportExcelCatalogueRestreintAvecStock.findByDescription", query = "SELECT v FROM VExportExcelCatalogueRestreintAvecStock v WHERE v.description = :description")
    , @NamedQuery(name = "VExportExcelCatalogueRestreintAvecStock.findByGroupe", query = "SELECT v FROM VExportExcelCatalogueRestreintAvecStock v WHERE v.groupe = :groupe")
    , @NamedQuery(name = "VExportExcelCatalogueRestreintAvecStock.findByPrix", query = "SELECT v FROM VExportExcelCatalogueRestreintAvecStock v WHERE v.prix = :prix")
    , @NamedQuery(name = "VExportExcelCatalogueRestreintAvecStock.findByPrixSephoraouMarionnaud", query = "SELECT v FROM VExportExcelCatalogueRestreintAvecStock v WHERE v.prixSephoraouMarionnaud = :prixSephoraouMarionnaud")
    , @NamedQuery(name = "VExportExcelCatalogueRestreintAvecStock.findByIdProduit", query = "SELECT v FROM VExportExcelCatalogueRestreintAvecStock v WHERE v.idProduit = :idProduit")
    , @NamedQuery(name = "VExportExcelCatalogueRestreintAvecStock.findByCat\u00e9gorie", query = "SELECT v FROM VExportExcelCatalogueRestreintAvecStock v WHERE v.cat\u00e9gorie = :cat\u00e9gorie")})
public class VExportExcelCatalogueRestreintAvecStock implements Serializable {

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

    public VExportExcelCatalogueRestreintAvecStock() {
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
