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
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Entity
@Table(name = "v_reduction")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "VReduction.findAll", query = "SELECT v FROM VReduction v") })
public class VReduction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 60)
	private String cdeGroupeeNom;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 60)
	private String membreNom;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	private String cdeIndivNom;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 60)
	private String cdeIndivEtat;

	@Basic
	@NotNull
	private BigDecimal cdeGroupeeMontantTotal;

	@Basic
	@NotNull
	private BigDecimal cdeIndivMontant;

	@Basic
	@NotNull
	private BigDecimal reduction;

	@Basic(optional = false)
	@NotNull
	private BigDecimal totalAPayer;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 60)
	private String cdeIndivStatusCode;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 60)
	private String cdeGoupeeEtat;

	@Id
	@Column(name = "idCdeIndiv", updatable = false, nullable = false)
	@Basic(optional = false)
	private Long idCdeIndiv;

	@Basic(optional = false)
	private Long idCdeGroupee;

	public VReduction() {
	}

	public String getCdeGroupeeNom() {
		return cdeGroupeeNom;
	}

	public void setCdeGroupeeNom(String cdeGroupeeNom) {
		this.cdeGroupeeNom = cdeGroupeeNom;
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

	public String getCdeIndivEtat() {
		return cdeIndivEtat;
	}

	public void setCdeIndivEtat(String cdeIndivEtat) {
		this.cdeIndivEtat = cdeIndivEtat;
	}

	public BigDecimal getCdeGroupeeMontantTotal() {
		return cdeGroupeeMontantTotal;
	}

	public void setCdeGroupeeMontantTotal(BigDecimal cdeGroupeeMontantTotal) {
		this.cdeGroupeeMontantTotal = cdeGroupeeMontantTotal;
	}

	public BigDecimal getCdeIndivMontant() {
		return cdeIndivMontant;
	}

	public void setCdeIndivMontant(BigDecimal cdeIndivMontant) {
		this.cdeIndivMontant = cdeIndivMontant;
	}

	public BigDecimal getReduction() {
		if (reduction != null) {
			return reduction.multiply(new BigDecimal(100));
		}
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

	public String getCdeIndivStatusCode() {
		return cdeIndivStatusCode;
	}

	public void setCdeIndivStatusCode(String cdeIndivStatusCode) {
		this.cdeIndivStatusCode = cdeIndivStatusCode;
	}

	public String getCdeGoupeeEtat() {
		return cdeGoupeeEtat;
	}

	public void setCdeGoupeeEtat(String cdeGoupeeEtat) {
		this.cdeGoupeeEtat = cdeGoupeeEtat;
	}

	public Long getIdCdeIndiv() {
		return idCdeIndiv;
	}

	public void setIdCdeIndiv(Long idCdeIndiv) {
		this.idCdeIndiv = idCdeIndiv;
	}

	public Long getIdCdeGroupee() {
		return idCdeGroupee;
	}

	public void setIdCdeGroupee(Long idCdeGroupee) {
		this.idCdeGroupee = idCdeGroupee;
	}

}
