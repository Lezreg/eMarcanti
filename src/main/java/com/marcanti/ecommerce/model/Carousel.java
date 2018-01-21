/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author rlezreg
 *
 */
@Entity
@Table(name="carousel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carousel.findAll", query = "SELECT c FROM Carousel c")
    , @NamedQuery(name = "Carousel.findByIdCarousel", query = "SELECT c FROM Carousel c WHERE c.idCarousel = :idCarousel")
    , @NamedQuery(name = "Carousel.findByElementNom", query = "SELECT c FROM Carousel c WHERE c.elementNom = :elementNom")
    , @NamedQuery(name = "Carousel.findByElementImageURL", query = "SELECT c FROM Carousel c WHERE c.elementImageURL = :elementImageURL")
    , @NamedQuery(name = "Carousel.findByElementLienURL", query = "SELECT c FROM Carousel c WHERE c.elementLienURL = :elementLienURL")
    , @NamedQuery(name = "Carousel.findByElementRang", query = "SELECT c FROM Carousel c WHERE c.elementRang = :elementRang")
    , @NamedQuery(name = "Carousel.findByIsVisibleOrderBy", query = "SELECT c FROM Carousel c WHERE c.isVisible = :isVisible ORDER BY elementRang asc")
    , @NamedQuery(name = "Carousel.findByIsVisible", query = "SELECT c FROM Carousel c WHERE c.isVisible = :isVisible")})
public class Carousel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idCarousel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String elementNom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    private String elementImageURL;
    @Size(max = 512)
    private String elementLienURL;
    @Basic(optional = false)
    @NotNull
    private short elementRang;
    @Basic(optional = false)
    @NotNull
    private boolean isVisible;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    private String produitMarque;   
    @Transient
    private Short idMarque;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    private String produitPrix;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String produitNom; 
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String produitSousTitre;  
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String boutonLibelle;    
    
    public Carousel() {
    }

    public Carousel(Integer idCarousel) {
        this.idCarousel = idCarousel;
    }

    public Carousel(Integer idCarousel, String elementNom, String elementImageURL, short elementRang, boolean isVisible, String produitMarque, String produitPrix, String produitNom, String produitSousTitre, String boutonLibelle) {
        this.idCarousel = idCarousel;
        this.elementNom = elementNom;
        this.elementImageURL = elementImageURL;
        this.elementRang = elementRang;
        this.isVisible = isVisible;
        this.produitMarque = produitMarque;
        this.produitPrix = produitPrix;
        this.produitNom = produitNom;
        this.produitSousTitre = produitSousTitre;
        this.boutonLibelle = boutonLibelle;
    }

    public Integer getIdCarousel() {
        return idCarousel;
    }

    public void setIdCarousel(Integer idCarousel) {
        this.idCarousel = idCarousel;
    }

    public String getElementNom() {
        return elementNom;
    }

    public void setElementNom(String elementNom) {
        this.elementNom = elementNom;
    }

    public String getElementImageURL() {
        return elementImageURL;
    }

    public void setElementImageURL(String elementImageURL) {
        this.elementImageURL = elementImageURL;
    }

    public String getElementLienURL() {
        return elementLienURL;
    }

    public void setElementLienURL(String elementLienURL) {
        this.elementLienURL = elementLienURL;
    }

    public short getElementRang() {
        return elementRang;
    }

    public void setElementRang(short elementRang) {
        this.elementRang = elementRang;
    }

    public boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    public String getIsVisibleStr() {
    	if (isVisible) return "y";
    	else return "n";
    }    
    
    public String getIsVisibleStyle() {
    	if (isVisible) return "greenclass fa fa-check";
    	else return "redclass fa fa-times";
    }   
    
    public String getProduitMarque() {
		return produitMarque;
	}

	public void setProduitMarque(String produitMarque) {
		this.produitMarque = produitMarque;
	}
	
	public Short getIdMarque() {
		return idMarque;
	}

	public void setIdMarque(Short idMarque) {
		this.idMarque = idMarque;
	}

	public String getProduitPrix() {
		return produitPrix;
	}

	public void setProduitPrix(String produitPrix) {
		this.produitPrix = produitPrix;
	}

	public String getProduitNom() {
		return produitNom;
	}

	public void setProduitNom(String produitNom) {
		this.produitNom = produitNom;
	}

	public String getProduitSousTitre() {
		return produitSousTitre;
	}

	public void setProduitSousTitre(String produitSousTitre) {
		this.produitSousTitre = produitSousTitre;
	}

	public String getBoutonLibelle() {
		return boutonLibelle;
	}

	public void setBoutonLibelle(String boutonLibelle) {
		this.boutonLibelle = boutonLibelle;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarousel != null ? idCarousel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carousel)) {
            return false;
        }
        Carousel other = (Carousel) object;
        if ((this.idCarousel == null && other.idCarousel != null) || (this.idCarousel != null && !this.idCarousel.equals(other.idCarousel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parfum.ce.model.Carousel[ idCarousel=" + idCarousel + " ]";
    }
    
}
