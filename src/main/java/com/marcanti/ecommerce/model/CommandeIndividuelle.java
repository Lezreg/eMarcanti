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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Entity
@Table(name = "commande_individuelle")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "CommandeIndividuelle.findAll", query = "SELECT c FROM CommandeIndividuelle c"),
		@NamedQuery(name = "CommandeIndividuelle.findByIdCdeIndiv", query = "SELECT c FROM CommandeIndividuelle c WHERE c.idCdeIndiv = :idCdeIndiv"),
		@NamedQuery(name = "CommandeIndividuelle.findByCdeIndivNom", query = "SELECT c FROM CommandeIndividuelle c WHERE c.cdeIndivNom = :cdeIndivNom"),
		@NamedQuery(name = "CommandeIndividuelle.findByDateCreation", query = "SELECT c FROM CommandeIndividuelle c WHERE c.dateCreation = :dateCreation"),
		@NamedQuery(name = "CommandeIndividuelle.findByDateModification", query = "SELECT c FROM CommandeIndividuelle c WHERE c.dateModification = :dateModification"),
		@NamedQuery(name = "CommandeIndividuelle.findByIsPaiementEffectue", query = "SELECT c FROM CommandeIndividuelle c WHERE c.isPaiementEffectue = :isPaiementEffectue"),
		@NamedQuery(name = "CommandeIndividuelle.findByNomModifieur", query = "SELECT c FROM CommandeIndividuelle c WHERE c.nomModifieur = :nomModifieur"),
		@NamedQuery(name = "CommandeIndividuelle.findByPrenomModifieur", query = "SELECT c FROM CommandeIndividuelle c WHERE c.prenomModifieur = :prenomModifieur"),
		@NamedQuery(name = "CommandeIndividuelle.findByReduction", query = "SELECT c FROM CommandeIndividuelle c WHERE c.reduction = :reduction"),
		@NamedQuery(name = "CommandeIndividuelle.findByTotalAPayer", query = "SELECT c FROM CommandeIndividuelle c WHERE c.totalAPayer = :totalAPayer"),
		@NamedQuery(name = "CommandeIndividuelle.findByCommentaire", query = "SELECT c FROM CommandeIndividuelle c WHERE c.commentaire = :commentaire") })
public class CommandeIndividuelle implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Long idCdeIndiv;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	private String cdeIndivNom;
	@Basic(optional = false)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModification;
	@Basic(optional = false)
	@NotNull
	private boolean isPaiementEffectue;
	@Size(max = 60)
	private String nomModifieur;
	@Size(max = 60)
	private String prenomModifieur;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	private BigDecimal reduction;
	@Basic(optional = false)
	@NotNull
	private BigDecimal totalAPayer;
	@Size(max = 100)
	private String commentaire;
	@JoinColumn(name = "idCdeGroupee", referencedColumnName = "idCdeGroupee")
	@ManyToOne(optional = false)
	private CommandeGroupee idCdeGroupee;
	@JoinColumn(name = "idStatus", referencedColumnName = "idStatus")
	@ManyToOne(optional = false)
	private CommandeIndividuelleStatus idStatus;
	@JoinColumn(name = "idMembre", referencedColumnName = "idMembre")
	@ManyToOne(optional = false)
	private Membre idMembre;
	@JoinColumn(name = "idPanier", referencedColumnName = "idPanier")
	@OneToOne(optional = false)
	private Panier idPanier;
	@JoinColumn(name = "idTransactionPaiement", referencedColumnName = "idTransactionPaiement")
	@ManyToOne
	private TransactionPaiement idTransactionPaiement;

	public CommandeIndividuelle() {
	}

	public CommandeIndividuelle(Long idCdeIndiv) {
		this.idCdeIndiv = idCdeIndiv;
	}

	public CommandeIndividuelle(Long idCdeIndiv, String cdeIndivNom, Date dateCreation, boolean isPaiementEffectue,
			BigDecimal totalAPayer) {
		this.idCdeIndiv = idCdeIndiv;
		this.cdeIndivNom = cdeIndivNom;
		this.dateCreation = dateCreation;
		this.isPaiementEffectue = isPaiementEffectue;
		this.totalAPayer = totalAPayer;
	}

	public Long getIdCdeIndiv() {
		return idCdeIndiv;
	}

	public void setIdCdeIndiv(Long idCdeIndiv) {
		this.idCdeIndiv = idCdeIndiv;
	}

	public String getCdeIndivNom() {
		return cdeIndivNom;
	}

	public void setCdeIndivNom(String cdeIndivNom) {
		this.cdeIndivNom = cdeIndivNom;
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

	public boolean getIsPaiementEffectue() {
		return isPaiementEffectue;
	}

	public void setIsPaiementEffectue(boolean isPaiementEffectue) {
		this.isPaiementEffectue = isPaiementEffectue;
	}

	public String getNomModifieur() {
		return nomModifieur;
	}

	public void setNomModifieur(String nomModifieur) {
		this.nomModifieur = nomModifieur;
	}

	public String getPrenomModifieur() {
		return prenomModifieur;
	}

	public void setPrenomModifieur(String prenomModifieur) {
		this.prenomModifieur = prenomModifieur;
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

	public CommandeIndividuelle setTotalAPayer(BigDecimal totalAPayer) {
		this.totalAPayer = totalAPayer;
		return this;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public CommandeGroupee getIdCdeGroupee() {
		return idCdeGroupee;
	}

	public void setIdCdeGroupee(CommandeGroupee idCdeGroupee) {
		this.idCdeGroupee = idCdeGroupee;
	}

	public CommandeIndividuelleStatus getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(CommandeIndividuelleStatus idStatus) {
		this.idStatus = idStatus;
	}

	public Membre getIdMembre() {
		return idMembre;
	}

	public void setIdMembre(Membre idMembre) {
		this.idMembre = idMembre;
	}

	public Panier getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(Panier idPanier) {
		this.idPanier = idPanier;
	}

	public TransactionPaiement getIdTransactionPaiement() {
		return idTransactionPaiement;
	}

	public void setIdTransactionPaiement(TransactionPaiement idTransactionPaiement) {
		this.idTransactionPaiement = idTransactionPaiement;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idCdeIndiv != null ? idCdeIndiv.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof CommandeIndividuelle)) {
			return false;
		}
		CommandeIndividuelle other = (CommandeIndividuelle) object;
		if ((this.idCdeIndiv == null && other.idCdeIndiv != null)
				|| (this.idCdeIndiv != null && !this.idCdeIndiv.equals(other.idCdeIndiv))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.parfum.ce.model.CommandeIndividuelle[ idCdeIndiv=" + idCdeIndiv + " ]";
	}

}
