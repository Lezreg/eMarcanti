/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "commande_groupee")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "CommandeGroupee.findAll", query = "SELECT c FROM CommandeGroupee c"),
		@NamedQuery(name = "CommandeGroupee.findByIdCdeGroupee", query = "SELECT c FROM CommandeGroupee c WHERE c.idCdeGroupee = :idCdeGroupee"),
		@NamedQuery(name = "CommandeGroupee.findByCdeGroupeeNom", query = "SELECT c FROM CommandeGroupee c WHERE c.cdeGroupeeNom = :cdeGroupeeNom"),
		@NamedQuery(name = "CommandeGroupee.findByDateCreation", query = "SELECT c FROM CommandeGroupee c WHERE c.dateCreation = :dateCreation"),
		@NamedQuery(name = "CommandeGroupee.findByNomCreateur", query = "SELECT c FROM CommandeGroupee c WHERE c.nomCreateur = :nomCreateur"),
		@NamedQuery(name = "CommandeGroupee.findByPrenomCreateur", query = "SELECT c FROM CommandeGroupee c WHERE c.prenomCreateur = :prenomCreateur"),
		@NamedQuery(name = "CommandeGroupee.findByDateLivraisonPrevue", query = "SELECT c FROM CommandeGroupee c WHERE c.dateLivraisonPrevue = :dateLivraisonPrevue"),
		@NamedQuery(name = "CommandeGroupee.findByDateLivraisonReelle", query = "SELECT c FROM CommandeGroupee c WHERE c.dateLivraisonReelle = :dateLivraisonReelle"),
		@NamedQuery(name = "CommandeGroupee.findByDateModification", query = "SELECT c FROM CommandeGroupee c WHERE c.dateModification = :dateModification"),
		@NamedQuery(name = "CommandeGroupee.findByNomModifieur", query = "SELECT c FROM CommandeGroupee c WHERE c.nomModifieur = :nomModifieur"),
		@NamedQuery(name = "CommandeGroupee.findByPrenomModifieur", query = "SELECT c FROM CommandeGroupee c WHERE c.prenomModifieur = :prenomModifieur"),
		@NamedQuery(name = "CommandeGroupee.findByIsPaiementEffectue", query = "SELECT c FROM CommandeGroupee c WHERE c.isPaiementEffectue = :isPaiementEffectue"),
		@NamedQuery(name = "CommandeGroupee.findByIsEnCours", query = "SELECT c FROM CommandeGroupee c WHERE c.isEnCours = :isEnCours"),
		@NamedQuery(name = "CommandeGroupee.findByCompteurDerniereMinute", query = "SELECT c FROM CommandeGroupee c WHERE c.compteurDerniereMinute = :compteurDerniereMinute"),
		@NamedQuery(name = "CommandeGroupee.findByCompteurDerniereMinuteMax", query = "SELECT c FROM CommandeGroupee c WHERE c.compteurDerniereMinuteMax = :compteurDerniereMinuteMax"),
		@NamedQuery(name = "CommandeGroupee.findByNumeroSuivi", query = "SELECT c FROM CommandeGroupee c WHERE c.numeroSuivi = :numeroSuivi"),
		@NamedQuery(name = "CommandeGroupee.findByNomTransporteur", query = "SELECT c FROM CommandeGroupee c WHERE c.nomTransporteur = :nomTransporteur"),
		@NamedQuery(name = "CommandeGroupee.findByCommentaire", query = "SELECT c FROM CommandeGroupee c WHERE c.commentaire = :commentaire") })
public class CommandeGroupee implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Long idCdeGroupee;
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
	private String nomCreateur;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 60)
	private String prenomCreateur;
	@Temporal(TemporalType.DATE)
	private Date dateLivraisonPrevue;
	@Temporal(TemporalType.DATE)
	private Date dateLivraisonReelle;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModification;
	@Size(max = 60)
	private String nomModifieur;
	@Size(max = 60)
	private String prenomModifieur;
	@Basic(optional = false)
	@NotNull
	private boolean isPaiementEffectue;
	@Basic(optional = false)
	@NotNull
	private boolean isEnCours;
	private Short compteurDerniereMinute;
	private Short compteurDerniereMinuteMax;
	@Size(max = 45)
	private String numeroSuivi;
	@Size(max = 45)
	private String nomTransporteur;
	@Size(max = 100)
	private String commentaire;
	@JoinColumn(name = "idStatus", referencedColumnName = "idStatus")
	@ManyToOne(optional = false)
	private CommandeGroupeeStatus idStatus;
	@JoinColumn(name = "idOrga", referencedColumnName = "idOrga")
	@ManyToOne(optional = false)
	private Organisation idOrga;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idCdeGroupee")
	private Collection<CommandeIndividuelle> commandeIndividuelleCollection;

	@Transient
	private BigDecimal sommeCommandeGroupee;

	public BigDecimal getSommeCommandeGroupee() {
		return sommeCommandeGroupee;
	}

	public void setSommeCommandeGroupee(BigDecimal sommeCommandeGroupee) {
		this.sommeCommandeGroupee = sommeCommandeGroupee;
	}

	public CommandeGroupee() {
	}

	public CommandeGroupee(Long idCdeGroupee) {
		this.idCdeGroupee = idCdeGroupee;
	}

	public CommandeGroupee(Long idCdeGroupee, String cdeGroupeeNom, Date dateCreation, String nomCreateur,
			String prenomCreateur, boolean isPaiementEffectue, boolean isEnCours) {
		this.idCdeGroupee = idCdeGroupee;
		this.cdeGroupeeNom = cdeGroupeeNom;
		this.dateCreation = dateCreation;
		this.nomCreateur = nomCreateur;
		this.prenomCreateur = prenomCreateur;
		this.isPaiementEffectue = isPaiementEffectue;
		this.isEnCours = isEnCours;
	}

	public Long getIdCdeGroupee() {
		return idCdeGroupee;
	}

	public void setIdCdeGroupee(Long idCdeGroupee) {
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

	public String getNomCreateur() {
		return nomCreateur;
	}

	public void setNomCreateur(String nomCreateur) {
		this.nomCreateur = nomCreateur;
	}

	public String getPrenomCreateur() {
		return prenomCreateur;
	}

	public void setPrenomCreateur(String prenomCreateur) {
		this.prenomCreateur = prenomCreateur;
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

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
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

	public boolean getIsPaiementEffectue() {
		return isPaiementEffectue;
	}

	public void setIsPaiementEffectue(boolean isPaiementEffectue) {
		this.isPaiementEffectue = isPaiementEffectue;
	}

	public boolean getIsEnCours() {
		return isEnCours;
	}

	public void setIsEnCours(boolean isEnCours) {
		this.isEnCours = isEnCours;
	}

	public Short getCompteurDerniereMinute() {
		return compteurDerniereMinute;
	}

	public void setCompteurDerniereMinute(Short compteurDerniereMinute) {
		this.compteurDerniereMinute = compteurDerniereMinute;
	}

	public Short getCompteurDerniereMinuteMax() {
		return compteurDerniereMinuteMax;
	}

	public void setCompteurDerniereMinuteMax(Short compteurDerniereMinuteMax) {
		this.compteurDerniereMinuteMax = compteurDerniereMinuteMax;
	}

	public String getNumeroSuivi() {
		return numeroSuivi;
	}

	public void setNumeroSuivi(String numeroSuivi) {
		this.numeroSuivi = numeroSuivi;
	}

	public String getNomTransporteur() {
		return nomTransporteur;
	}

	public void setNomTransporteur(String nomTransporteur) {
		this.nomTransporteur = nomTransporteur;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public CommandeGroupeeStatus getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(CommandeGroupeeStatus idStatus) {
		this.idStatus = idStatus;
	}

	public Organisation getIdOrga() {
		return idOrga;
	}

	public void setIdOrga(Organisation idOrga) {
		this.idOrga = idOrga;
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
		hash += (idCdeGroupee != null ? idCdeGroupee.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof CommandeGroupee)) {
			return false;
		}
		CommandeGroupee other = (CommandeGroupee) object;
		if ((this.idCdeGroupee == null && other.idCdeGroupee != null)
				|| (this.idCdeGroupee != null && !this.idCdeGroupee.equals(other.idCdeGroupee))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.parfum.ce.model.CommandeGroupee[ idCdeGroupee=" + idCdeGroupee + " ]";
	}

}
