/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.marcanti.ecommerce.dao.AbstractGenericDAO;
import com.marcanti.ecommerce.dao.ProduitDAO;
import com.marcanti.ecommerce.model.Produit;
import com.marcanti.ecommerce.model.VCatalogueAvecStock;
import com.marcanti.ecommerce.model.VCatalogueRestreintAvecStock;

/**
 *
 * @author lezreg
 */
@Repository
public class ProduitDAOImpl extends AbstractGenericDAO<Produit> implements ProduitDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProduitDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	public ProduitDAOImpl() {
		super(Produit.class);
	}

	public void create(Produit entity) {
		super.create(entity);
	}

	public void edit(Long id, Produit entity) {
		super.edit(entity);
	}

	public void remove(Long id) {
		super.remove(super.find(id));
	}

	public Produit find(Long id) {
		return super.find(id);
	}

	public List<Produit> findAll() {
		return super.findAll();
	}

	public List<Produit> findRange(Integer from, Integer to) {
		return super.findRange(new int[] { from, to });
	}

	public List<Produit> findProduitByCategorie(String CatCOde) {
		Query query = em
				.createQuery("SELECT p FROM Produit p WHERE p.qteEnStock>0 and p.idCategorie.categorieCode = :CatCOde")
				.setParameter("CatCOde", CatCOde);
		return query.getResultList();
	}

	public String countREST() {
		return String.valueOf(super.count());
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Produit> getAllProduits() {
		return super.findAll();
	}

	@Override
	public List<VCatalogueAvecStock> getNewProducts() {
		LOGGER.info("----------getNewProducts--------");
		List<VCatalogueAvecStock> newProducts = em.createQuery(
				"SELECT v FROM VCatalogueAvecStock v WHERE v.dateDebutNouveaute <= :date AND v.dateFinNouveaute >= :date",
				VCatalogueAvecStock.class).setParameter("date", new Date()).getResultList();
		return newProducts;
	}

	@Override
	public List<VCatalogueRestreintAvecStock> getRestrictedNewProduit() {
		LOGGER.info("----------getRestrictedNewProduit--------VCatalogueRestreintAvecStock");
		List<VCatalogueRestreintAvecStock> restrictedNewProducts = em.createQuery(
				"SELECT v FROM VCatalogueRestreintAvecStock v WHERE v.dateDebutNouveaute <= :date AND v.dateFinNouveaute >= :date",
				VCatalogueRestreintAvecStock.class).setParameter("date", new Date()).getResultList();
		return restrictedNewProducts;
	}

	@Override
	public List<VCatalogueAvecStock> getProductsByCategorie(String codeCategorie) {
		LOGGER.info("----------getProductsByCategorie--------");
		//List<VCatalogueAvecStock> newProducts = em.createQuery("SELECT v FROM VCatalogueAvecStock v WHERE v.qteEnStock > 0 AND v.categorieCode = :CatCOde",VCatalogueAvecStock.class).setParameter("CatCOde", codeCategorie).getResultList();
		//return newProducts;
		return em.createNamedQuery("VCatalogueAvecStock.findByCategorieCode", VCatalogueAvecStock.class).setParameter("categorieCode", codeCategorie).getResultList();
	}

	@Override
	public List<VCatalogueRestreintAvecStock> getRestrictedProduitsByCategorie(String codeCategorie) {
		LOGGER.info("----------getRestrictedProduitsByCategorie--------VCatalogueRestreintAvecStock");
		//List<VCatalogueRestreintAvecStock> restrictedNewProducts = em.createQuery("SELECT v FROM VCatalogueRestreintAvecStock v WHERE v.qteEnStock > 0 AND v.categorieCode = :CatCOde",VCatalogueRestreintAvecStock.class).setParameter("CatCOde", codeCategorie).getResultList();
		//return restrictedNewProducts;
		return em.createNamedQuery("VCatalogueRestreintAvecStock.findByCategorieCode", VCatalogueRestreintAvecStock.class).setParameter("categorieCode", codeCategorie).getResultList();
		
	}

	@Override
	public List<VCatalogueAvecStock> getPromoProducts() {
		LOGGER.info("----------getPromoProducts--------VCatalogueAvecStock");
		List<VCatalogueAvecStock> newProducts = em.createQuery(
				"SELECT v FROM VCatalogueAvecStock v WHERE v.dateDebutPromo <= :date AND v.dateFinPromo >= :date",
				VCatalogueAvecStock.class).setParameter("date", new Date()).getResultList();
		return newProducts;
	}

	@Override
	public List<VCatalogueRestreintAvecStock> getRestrictedPromoProduit() {
		LOGGER.info("----------getRestrictedPromoProduit--------VCatalogueRestreintAvecStock");
		List<VCatalogueRestreintAvecStock> restrictedNewProducts = em.createQuery(
				"SELECT v FROM VCatalogueRestreintAvecStock v WHERE v.dateDebutPromo <= :date AND v.dateFinPromo >= :date",
				VCatalogueRestreintAvecStock.class).setParameter("date", new Date()).getResultList();
		return restrictedNewProducts;
	}

	@Override
	public List<VCatalogueAvecStock> getDecouvProducts() {
		LOGGER.info("----------getDecouvProducts--------VCatalogueAvecStock");
		List<VCatalogueAvecStock> newProducts = em.createQuery(
				"SELECT v FROM VCatalogueAvecStock v WHERE v.dateDebutADecouvrir <= :date AND v.dateFinADecouvrir >= :date",
				VCatalogueAvecStock.class).setParameter("date", new Date()).getResultList();
		return newProducts;
	}

	@Override
	public List<VCatalogueRestreintAvecStock> getRestrictedDecouvProduit() {
		LOGGER.info("----------getRestrictedDecouvProduit--------VCatalogueRestreintAvecStock");
		List<VCatalogueRestreintAvecStock> restrictedNewProducts = em.createQuery(
				"SELECT v FROM VCatalogueRestreintAvecStock v WHERE v.dateDebutADecouvrir <= :date AND v.dateFinADecouvrir >= :date",
				VCatalogueRestreintAvecStock.class).setParameter("date", new Date()).getResultList();
		return restrictedNewProducts;
	}

	@Override
	public Produit getProduit(Long idProduit) {
		return em.createNamedQuery("Produit.findByIdProduit", Produit.class).setParameter("idProduit", idProduit).getSingleResult();
	}

	@Override
	public void updateProduit(Produit produit) {
		Query query = em.createNativeQuery("UPDATE produit SET idMarque=?, "
				+ "idCategorie=? "
				+ "idSousCategorie=? "
				+ "produitDescription=? "
				+ "produitDetail=? "
				+ "notrePrix=? "
				+ "prixPublic=? "
				+ "qteEnStock=? "
				+ "produitPhotoURL=? "
				+ "produitPhotoTailleMediumURL=? "
				+ "produitPhotoTailleLargeURL=? "
				+ "dateDebutPromo=? "
				+ "dateFinPromo=? "
				+ "dateDebutNouveaute=? "
				+ "dateFinNouveaute=? "
				+ "dateDebutADecouvrir=? "
				+ "dateFinADecouvrir=? "
				+ "titreADecouvrir=? "
				+ "noteDeTete=? "
				+ "noteDeCoeur=? "
				+ "noteDeFond=? "
				+ "familleOlfactive=? "
				+ "descriptionADecouvrir=? "
				+ "motCleADecouvrir=? "
				+ "codeEAN=? "
				+ "isBestSellerHomme=? "
				+ "isBestSellerFemme=? "
				+ "isPetitPrix=? "
				+ "isNouveauteSurHomePage=? "
				+ "isPromoSurHomePage=? "
				+ "isADecouvrirSurHomePage=? "
				+ "produitVideoURL=? "
				+ "WHERE idProduit=?")
				.setParameter(1, produit.getIdMarque())
				.setParameter(2, produit.getIdCategorie().getIdCategorie())
				.setParameter(3, produit.getIdSousCategorie().getIdSousCategorie())
				.setParameter(4, produit.getProduitDescription())
				.setParameter(5, produit.getProduitDetail())
				.setParameter(6, produit.getNotrePrix())
				.setParameter(7, produit.getPrixPublic())
				.setParameter(8, produit.getQteEnStock())
				.setParameter(9, produit.getProduitPhotoURL())
				.setParameter(10, produit.getProduitPhotoTailleMediumURL())
				.setParameter(11, produit.getProduitPhotoTailleLargeURL())
				.setParameter(12, produit.getDateDebutPromo())
				.setParameter(13, produit.getDateFinPromo())
				.setParameter(14, produit.getDateDebutNouveaute())
				.setParameter(15, produit.getDateFinNouveaute())
				.setParameter(16, produit.getDateDebutADecouvrir())
				.setParameter(17, produit.getDateFinADecouvrir())
				.setParameter(18, produit.getTitreADecouvrir())
				.setParameter(19, produit.getNoteDeTete())
				.setParameter(20, produit.getNoteDeCoeur())
				.setParameter(21, produit.getNoteDeFond())
				.setParameter(22, produit.getFamilleOlfactive())
				.setParameter(23, produit.getDescriptionADecouvrir())
				.setParameter(24, produit.getMotCleADecouvrir())
				.setParameter(25, produit.getCodeEAN())
				.setParameter(26, produit.isBestSellerHomme())
				.setParameter(27, produit.isBestSellerFemme())
				.setParameter(28, produit.isPetitPrix())
				.setParameter(29, produit.isNouveauteSurHomePage())
				.setParameter(30, produit.isPromoSurHomePage())
				.setParameter(31, produit.isADecouvrirSurHomePage())
				.setParameter(32, produit.getProduitVideoURL())
				.setParameter(33, produit.getIdProduit());
		query.executeUpdate();
		//em.persist(sousCategorie);
		
	}

	@Override
	public void insertProduit(Produit produit) {
		Query query = em.createNativeQuery("INSERT INTO produit (idMarque,idCategorie,idSousCategorie,produitDescription,produitDetail,notrePrix,prixPublic,qteEnStock,produitPhotoURL,produitPhotoTailleMediumURL,produitPhotoTailleLargeURL,dateDebutPromo,dateFinPromo,dateDebutNouveaute,dateFinNouveaute,dateDebutADecouvrir,dateFinADecouvrir,titreADecouvrir,noteDeTete,noteDeCoeur,noteDeFond,familleOlfactive,descriptionADecouvrir,motCleADecouvrir,codeEAN,isBestSellerHomme,isBestSellerFemme,isPetitPrix,isNouveauteSurHomePage,isPromoSurHomePage,isADecouvrirSurHomePage,produitVideoURL) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
				.setParameter(1, produit.getIdMarque())
				.setParameter(2, produit.getIdCategorie().getIdCategorie())
				.setParameter(3, produit.getIdSousCategorie().getIdSousCategorie())
				.setParameter(4, produit.getProduitDescription())
				.setParameter(5, produit.getProduitDetail())
				.setParameter(6, produit.getNotrePrix())
				.setParameter(7, produit.getPrixPublic())
				.setParameter(8, produit.getQteEnStock())
				.setParameter(9, produit.getProduitPhotoURL())
				.setParameter(10, produit.getProduitPhotoTailleMediumURL())
				.setParameter(11, produit.getProduitPhotoTailleLargeURL())
				.setParameter(12, produit.getDateDebutPromo())
				.setParameter(13, produit.getDateFinPromo())
				.setParameter(14, produit.getDateDebutNouveaute())
				.setParameter(15, produit.getDateFinNouveaute())
				.setParameter(16, produit.getDateDebutADecouvrir())
				.setParameter(17, produit.getDateFinADecouvrir())
				.setParameter(18, produit.getTitreADecouvrir())
				.setParameter(19, produit.getNoteDeTete())
				.setParameter(20, produit.getNoteDeCoeur())
				.setParameter(21, produit.getNoteDeFond())
				.setParameter(22, produit.getFamilleOlfactive())
				.setParameter(23, produit.getDescriptionADecouvrir())
				.setParameter(24, produit.getMotCleADecouvrir())
				.setParameter(25, produit.getCodeEAN())
				.setParameter(26, produit.isBestSellerHomme())
				.setParameter(27, produit.isBestSellerFemme())
				.setParameter(28, produit.isPetitPrix())
				.setParameter(29, produit.isNouveauteSurHomePage())
				.setParameter(30, produit.isPromoSurHomePage())
				.setParameter(31, produit.isADecouvrirSurHomePage())
				.setParameter(32, produit.getProduitVideoURL());
		query.executeUpdate();
		//em.persist(sousCategorie);
		
	}
}
