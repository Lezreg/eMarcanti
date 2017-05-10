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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "transaction_paiement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionPaiement.findAll", query = "SELECT t FROM TransactionPaiement t")
    , @NamedQuery(name = "TransactionPaiement.findByIdTransactionPaiement", query = "SELECT t FROM TransactionPaiement t WHERE t.idTransactionPaiement = :idTransactionPaiement")
    , @NamedQuery(name = "TransactionPaiement.findByNomSurCB", query = "SELECT t FROM TransactionPaiement t WHERE t.nomSurCB = :nomSurCB")
    , @NamedQuery(name = "TransactionPaiement.findByPrenomSurCB", query = "SELECT t FROM TransactionPaiement t WHERE t.prenomSurCB = :prenomSurCB")
    , @NamedQuery(name = "TransactionPaiement.findByTypeCB", query = "SELECT t FROM TransactionPaiement t WHERE t.typeCB = :typeCB")
    , @NamedQuery(name = "TransactionPaiement.findByDateExpirationCB", query = "SELECT t FROM TransactionPaiement t WHERE t.dateExpirationCB = :dateExpirationCB")
    , @NamedQuery(name = "TransactionPaiement.findByQuatreDerniersDigitsCB", query = "SELECT t FROM TransactionPaiement t WHERE t.quatreDerniersDigitsCB = :quatreDerniersDigitsCB")
    , @NamedQuery(name = "TransactionPaiement.findByDatePaiement", query = "SELECT t FROM TransactionPaiement t WHERE t.datePaiement = :datePaiement")
    , @NamedQuery(name = "TransactionPaiement.findByIdTransactionFournissPaiement", query = "SELECT t FROM TransactionPaiement t WHERE t.idTransactionFournissPaiement = :idTransactionFournissPaiement")
    , @NamedQuery(name = "TransactionPaiement.findByStatutTransactionFournissPaiement", query = "SELECT t FROM TransactionPaiement t WHERE t.statutTransactionFournissPaiement = :statutTransactionFournissPaiement")
    , @NamedQuery(name = "TransactionPaiement.findByMontantPaiement", query = "SELECT t FROM TransactionPaiement t WHERE t.montantPaiement = :montantPaiement")
    , @NamedQuery(name = "TransactionPaiement.findByEmailPaypal", query = "SELECT t FROM TransactionPaiement t WHERE t.emailPaypal = :emailPaypal")
    , @NamedQuery(name = "TransactionPaiement.findByCommentaire", query = "SELECT t FROM TransactionPaiement t WHERE t.commentaire = :commentaire")})
public class TransactionPaiement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    private String idTransactionPaiement;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String nomSurCB;
    @Size(max = 60)
    private String prenomSurCB;
    @Size(max = 45)
    private String typeCB;
    @Temporal(TemporalType.DATE)
    private Date dateExpirationCB;
    @Size(max = 10)
    private String quatreDerniersDigitsCB;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaiement;
    @Size(max = 60)
    private String idTransactionFournissPaiement;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String statutTransactionFournissPaiement;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    private BigDecimal montantPaiement;
    @Size(max = 120)
    private String emailPaypal;
    @Size(max = 120)
    private String commentaire;
    @OneToMany(mappedBy = "idTransactionPaiement")
    private Collection<CommandeIndividuelle> commandeIndividuelleCollection;

    public TransactionPaiement() {
    }

    public TransactionPaiement(String idTransactionPaiement) {
        this.idTransactionPaiement = idTransactionPaiement;
    }

    public TransactionPaiement(String idTransactionPaiement, String nomSurCB, Date datePaiement, String statutTransactionFournissPaiement, BigDecimal montantPaiement) {
        this.idTransactionPaiement = idTransactionPaiement;
        this.nomSurCB = nomSurCB;
        this.datePaiement = datePaiement;
        this.statutTransactionFournissPaiement = statutTransactionFournissPaiement;
        this.montantPaiement = montantPaiement;
    }

    public String getIdTransactionPaiement() {
        return idTransactionPaiement;
    }

    public void setIdTransactionPaiement(String idTransactionPaiement) {
        this.idTransactionPaiement = idTransactionPaiement;
    }

    public String getNomSurCB() {
        return nomSurCB;
    }

    public void setNomSurCB(String nomSurCB) {
        this.nomSurCB = nomSurCB;
    }

    public String getPrenomSurCB() {
        return prenomSurCB;
    }

    public void setPrenomSurCB(String prenomSurCB) {
        this.prenomSurCB = prenomSurCB;
    }

    public String getTypeCB() {
        return typeCB;
    }

    public void setTypeCB(String typeCB) {
        this.typeCB = typeCB;
    }

    public Date getDateExpirationCB() {
        return dateExpirationCB;
    }

    public void setDateExpirationCB(Date dateExpirationCB) {
        this.dateExpirationCB = dateExpirationCB;
    }

    public String getQuatreDerniersDigitsCB() {
        return quatreDerniersDigitsCB;
    }

    public void setQuatreDerniersDigitsCB(String quatreDerniersDigitsCB) {
        this.quatreDerniersDigitsCB = quatreDerniersDigitsCB;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getIdTransactionFournissPaiement() {
        return idTransactionFournissPaiement;
    }

    public void setIdTransactionFournissPaiement(String idTransactionFournissPaiement) {
        this.idTransactionFournissPaiement = idTransactionFournissPaiement;
    }

    public String getStatutTransactionFournissPaiement() {
        return statutTransactionFournissPaiement;
    }

    public void setStatutTransactionFournissPaiement(String statutTransactionFournissPaiement) {
        this.statutTransactionFournissPaiement = statutTransactionFournissPaiement;
    }

    public BigDecimal getMontantPaiement() {
        return montantPaiement;
    }

    public void setMontantPaiement(BigDecimal montantPaiement) {
        this.montantPaiement = montantPaiement;
    }

    public String getEmailPaypal() {
        return emailPaypal;
    }

    public void setEmailPaypal(String emailPaypal) {
        this.emailPaypal = emailPaypal;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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
        hash += (idTransactionPaiement != null ? idTransactionPaiement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionPaiement)) {
            return false;
        }
        TransactionPaiement other = (TransactionPaiement) object;
        if ((this.idTransactionPaiement == null && other.idTransactionPaiement != null) || (this.idTransactionPaiement != null && !this.idTransactionPaiement.equals(other.idTransactionPaiement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parfum.ce.model.TransactionPaiement[ idTransactionPaiement=" + idTransactionPaiement + " ]";
    }
    
}
