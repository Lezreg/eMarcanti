/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="organisation")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Organisation.findAll", query = "SELECT o FROM Organisation o ORDER BY o.orgaNom asc"),
		@NamedQuery(name = "Organisation.findByIdOrga", query = "SELECT o FROM Organisation o WHERE o.idOrga = :idOrga"),
		@NamedQuery(name = "Organisation.findByOrgaNom", query = "SELECT o FROM Organisation o WHERE o.orgaNom = :orgaNom"),
		@NamedQuery(name = "Organisation.findByOrgaAdresse", query = "SELECT o FROM Organisation o WHERE o.orgaAdresse = :orgaAdresse"),
		@NamedQuery(name = "Organisation.findByOrgaCodePostal", query = "SELECT o FROM Organisation o WHERE o.orgaCodePostal = :orgaCodePostal"),
		@NamedQuery(name = "Organisation.findByOrgaVille", query = "SELECT o FROM Organisation o WHERE o.orgaVille = :orgaVille"),
		@NamedQuery(name = "Organisation.findByOrgaPays", query = "SELECT o FROM Organisation o WHERE o.orgaPays = :orgaPays"),
		@NamedQuery(name = "Organisation.findByOrgaAdresseLivraison", query = "SELECT o FROM Organisation o WHERE o.orgaAdresseLivraison = :orgaAdresseLivraison"),
		@NamedQuery(name = "Organisation.findByOrgaAlias", query = "SELECT o FROM Organisation o WHERE o.orgaAlias = :orgaAlias"),
		@NamedQuery(name = "Organisation.findByIsAlivrerAvantPaiement", query = "SELECT o FROM Organisation o WHERE o.isAlivrerAvantPaiement = :isAlivrerAvantPaiement"),
		@NamedQuery(name = "Organisation.findByDateCreation", query = "SELECT o FROM Organisation o WHERE o.dateCreation = :dateCreation"),
		@NamedQuery(name = "Organisation.findByDateModification", query = "SELECT o FROM Organisation o WHERE o.dateModification = :dateModification"),
		@NamedQuery(name = "Organisation.findByIsActive", query = "SELECT o FROM Organisation o WHERE o.isActive = :isActive"),
		@NamedQuery(name = "Organisation.findByAccesCatalogueComplet", query = "SELECT o FROM Organisation o WHERE o.accesCatalogueComplet = :accesCatalogueComplet"),
		@NamedQuery(name = "Organisation.findByEnvoiMailConfirmation", query = "SELECT o FROM Organisation o WHERE o.envoiMailConfirmation = :envoiMailConfirmation") })
public class Organisation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Long idOrga;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	private String orgaNom;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	private String orgaAdresse;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 10)
	private String orgaCodePostal;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	private String orgaVille;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	private String orgaPays;

	@Size(max = 20)
	private String orgaTel;

	@Size(max = 140)
	private String orgaAdresseLivraison;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 30)
	private String orgaAlias;

	@Basic(optional = false)
	@NotNull
	private boolean isAlivrerAvantPaiement;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModification;

	@Basic(optional = false)
	@NotNull
	private boolean isActive;

	@Basic(optional = false)
	@NotNull
	private boolean accesCatalogueComplet;

	@Basic(optional = false)
	@NotNull
	private boolean envoiMailConfirmation;

	@Basic(optional = false)
	private int nbreMaxProduitParItem;

	@Transient
	private long nbrMembre;

	public int getNbreMaxProduitParItem() {
		return nbreMaxProduitParItem;
	}

	public void setNbreMaxProduitParItem(int nbreMaxProduitParItem) {
		this.nbreMaxProduitParItem = nbreMaxProduitParItem;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrga")
	private Collection<Departement> departementCollection;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrga")
	private Collection<CommandeGroupee> commandeGroupeeCollection;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrga")
	private Collection<Membre> membreCollection;

	public Organisation() {
	}

	public Organisation(Long idOrga) {
		this.idOrga = idOrga;
	}

	public Organisation(Long idOrga, String orgaNom, String orgaAdresse, String orgaCodePostal, String orgaVille,
			String orgaPays, String orgaTel, String orgaAlias, boolean isAlivrerAvantPaiement, boolean isActive,
			boolean accesCatalogueComplet, boolean envoiMailConfirmation) {
		this.idOrga = idOrga;
		this.orgaNom = orgaNom;
		this.orgaAdresse = orgaAdresse;
		this.orgaCodePostal = orgaCodePostal;
		this.orgaVille = orgaVille;
		this.orgaPays = orgaPays;
		this.orgaTel = orgaTel;
		this.orgaAlias = orgaAlias;
		this.isAlivrerAvantPaiement = isAlivrerAvantPaiement;
		this.isActive = isActive;
		this.accesCatalogueComplet = accesCatalogueComplet;
		this.envoiMailConfirmation = envoiMailConfirmation;
	}

	public Long getIdOrga() {
		return idOrga;
	}

	public void setIdOrga(Long idOrga) {
		this.idOrga = idOrga;
	}

	public String getOrgaNom() {
		return orgaNom;
	}

	public void setOrgaNom(String orgaNom) {
		this.orgaNom = orgaNom;
	}

	public String getOrgaAdresse() {
		return orgaAdresse;
	}

	public void setOrgaAdresse(String orgaAdresse) {
		this.orgaAdresse = orgaAdresse;
	}

	public String getOrgaCodePostal() {
		return orgaCodePostal;
	}

	public void setOrgaCodePostal(String orgaCodePostal) {
		this.orgaCodePostal = orgaCodePostal;
	}

	public String getOrgaVille() {
		return orgaVille;
	}

	public void setOrgaVille(String orgaVille) {
		this.orgaVille = orgaVille;
	}

	public String getOrgaPays() {
		return orgaPays;
	}

	public void setOrgaPays(String orgaPays) {
		this.orgaPays = orgaPays;
	}

	public String getOrgaTel() {
		return orgaTel;
	}

	public void setOrgaTel(String orgaTel) {
		this.orgaTel = orgaTel;
	}

	public String getOrgaAdresseLivraison() {
		return orgaAdresseLivraison;
	}

	public void setOrgaAdresseLivraison(String orgaAdresseLivraison) {
		this.orgaAdresseLivraison = orgaAdresseLivraison;
	}

	public String getOrgaAlias() {
		return orgaAlias;
	}

	public void setOrgaAlias(String orgaAlias) {
		this.orgaAlias = orgaAlias;
	}

	public boolean getIsAlivrerAvantPaiement() {
		return isAlivrerAvantPaiement;
	}

	public String getIsAlivrerAvantPaiementStr() {
		// TODO : apeller le fichier local
		if (isAlivrerAvantPaiement)
			return "oui";
		else
			return "non";
	}

	public void setIsAlivrerAvantPaiement(boolean isAlivrerAvantPaiement) {
		this.isAlivrerAvantPaiement = isAlivrerAvantPaiement;
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

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getIsActiveStr() {
		if (isActive)
			return "y";
		else
			return "n";
	}

	public String getIsActiveStyle() {
		if (isActive)
			return "greenclass fa fa-check";
		else
			return "redclass fa fa-times";
	}

	public boolean getAccesCatalogueComplet() {
		return accesCatalogueComplet;
	}

	public void setAccesCatalogueComplet(boolean accesCatalogueComplet) {
		this.accesCatalogueComplet = accesCatalogueComplet;
	}

	public boolean getEnvoiMailConfirmation() {
		return envoiMailConfirmation;
	}

	public void setEnvoiMailConfirmation(boolean envoiMailConfirmation) {
		this.envoiMailConfirmation = envoiMailConfirmation;
	}

	public long getNbrMembre() {
		return nbrMembre;
	}

	public void setNbrMembre(long nbrMembre) {
		this.nbrMembre = nbrMembre;
	}

	@XmlTransient
	public Collection<Departement> getDepartementCollection() {
		return departementCollection;
	}

	public void setDepartementCollection(Collection<Departement> departementCollection) {
		this.departementCollection = departementCollection;
	}

	@XmlTransient
	public Collection<CommandeGroupee> getCommandeGroupeeCollection() {
		return commandeGroupeeCollection;
	}

	public void setCommandeGroupeeCollection(Collection<CommandeGroupee> commandeGroupeeCollection) {
		this.commandeGroupeeCollection = commandeGroupeeCollection;
	}

	@XmlTransient
	public Collection<Membre> getMembreCollection() {
		return membreCollection;
	}

	public void setMembreCollection(Collection<Membre> membreCollection) {
		this.membreCollection = membreCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idOrga != null ? idOrga.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Organisation)) {
			return false;
		}
		Organisation other = (Organisation) object;
		if ((this.idOrga == null && other.idOrga != null)
				|| (this.idOrga != null && !this.idOrga.equals(other.idOrga))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.parfum.ce.model.Organisation[ idOrga=" + idOrga + " ]";
	}

}
