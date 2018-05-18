package com.marcanti.ecommerce.utils;

import java.util.ArrayList;
import java.util.List;

import com.marcanti.ecommerce.beans.ProduitBean;
import com.marcanti.ecommerce.model.VCatalogueAvecStock;
import com.marcanti.ecommerce.model.VCatalogueRestreintAvecStock;

public class ProduitConvertor {

	public static ProduitBean convert(VCatalogueAvecStock catalogue) {
		ProduitBean produitBean = new ProduitBean();
		produitBean.setIdProduit(catalogue.getIdProduit());
		produitBean.setProduitPhotoURL(catalogue.getProduitPhotoURL());
		// marque
		produitBean.setMarqueNom(catalogue.getMarqueNom());
		// description
		produitBean.setProduitDescription(catalogue.getProduitDescription());
		produitBean.setDescriptionADecouvrir(catalogue.getDescriptionADecouvrir());
		produitBean.setFamilleOlfactive(catalogue.getFamilleOlfactive());
		produitBean.setProduitDetail(catalogue.getProduitDetail());
		// les notes
		produitBean.setNoteDeCoeur(catalogue.getNoteDeCoeur());
		produitBean.setNoteDeFond(catalogue.getNoteDeFond());
		produitBean.setNoteDeTete(catalogue.getNoteDeTete());
		// prix
		produitBean.setPetitPrix(catalogue.isPetitPrix());
		produitBean.setNotrePrix(catalogue.getNotrePrix());
		produitBean.setPrixPublic(catalogue.getPrixPublic());
		// categorie
		produitBean.setCategorieNom(catalogue.getCategorieNom());
		// sous-categorie
		produitBean.setSousCategorieNom(catalogue.getSousCategorieNom());

		// decouvrir
		produitBean.setADecouvrirSurHomePage(catalogue.isADecouvrirSurHomePage());
		produitBean.setDescriptionADecouvrir(catalogue.getDescriptionADecouvrir());
		produitBean.setTitreADecouvrir(catalogue.getTitreADecouvrir());

		// categorie css
		produitBean.setCategorieCodeCss(catalogue.getCategorieCodeCss());
		produitBean.setCategorieNomCss(catalogue.getCategorieNomCss());

		// nouveau css
		produitBean.setNouveauteSurHomePage(catalogue.isNouveauteSurHomePage());
		produitBean.setIsNouveauteSurHomePageCss(catalogue.getIsNouveauteSurHomePageCss());
		produitBean.setNouveauteSurHomePageIndexCss(catalogue.getNouveauteSurHomePageIndexCss());

		// promo css
		produitBean.setPromoSurHomePage(catalogue.isPromoSurHomePage());
		produitBean.setIsPromoSurHomePageCss(catalogue.getIsPromoSurHomePageCss());
		produitBean.setPromoSurHomePageIndexCss(catalogue.getPromoSurHomePageIndexCss());
		
		// Best Seller
		produitBean.setBestSellerFemme(catalogue.isBestSellerFemme());
		produitBean.setBestSellerHomme(catalogue.isBestSellerHomme());

		produitBean.setProduitPhotoURL(catalogue.getProduitPhotoURL());
		produitBean.setProduitPhotoTailleMediumURL(catalogue.getProduitPhotoTailleMediumURL());
		produitBean.setProduitPhotoTailleLargeURL(catalogue.getProduitPhotoTailleLargeURL());

		return produitBean;
	}

	public static ProduitBean convert(VCatalogueRestreintAvecStock catalogue) {
		ProduitBean produitBean = new ProduitBean();
		produitBean.setIdProduit(catalogue.getIdProduit());
		produitBean.setProduitPhotoURL(catalogue.getProduitPhotoURL());
		// marque
		produitBean.setMarqueNom(catalogue.getMarqueNom());
		// description
		produitBean.setProduitDescription(catalogue.getProduitDescription());
		produitBean.setDescriptionADecouvrir(catalogue.getDescriptionADecouvrir());
		produitBean.setFamilleOlfactive(catalogue.getFamilleOlfactive());
		produitBean.setProduitDetail(catalogue.getProduitDetail());
		// les notes
		produitBean.setNoteDeCoeur(catalogue.getNoteDeCoeur());
		produitBean.setNoteDeFond(catalogue.getNoteDeFond());
		produitBean.setNoteDeTete(catalogue.getNoteDeTete());
		// prix
		produitBean.setPetitPrix(catalogue.isPetitPrix());
		produitBean.setNotrePrix(catalogue.getNotrePrix());
		produitBean.setPrixPublic(catalogue.getPrixPublic());
		// categorie
		produitBean.setCategorieNom(catalogue.getCategorieNom());
		// sous-categorie
		produitBean.setSousCategorieNom(catalogue.getSousCategorieNom());

		// decouvrir
		produitBean.setADecouvrirSurHomePage(catalogue.isADecouvrirSurHomePage());
		produitBean.setDescriptionADecouvrir(catalogue.getDescriptionADecouvrir());
		produitBean.setTitreADecouvrir(catalogue.getTitreADecouvrir());

		// categorie css
		produitBean.setCategorieCodeCss(catalogue.getCategorieCodeCss());
		produitBean.setCategorieNomCss(catalogue.getCategorieNomCss());

		// nouveau css
		produitBean.setNouveauteSurHomePage(catalogue.isNouveauteSurHomePage());
		produitBean.setIsNouveauteSurHomePageCss(catalogue.getIsNouveauteSurHomePageCss());
		produitBean.setNouveauteSurHomePageIndexCss(catalogue.getNouveauteSurHomePageIndexCss());

		// promo css
		produitBean.setPromoSurHomePage(catalogue.isPromoSurHomePage());
		produitBean.setIsPromoSurHomePageCss(catalogue.getIsPromoSurHomePageCss());
		produitBean.setPromoSurHomePageIndexCss(catalogue.getPromoSurHomePageIndexCss());

		// Best Seller
		produitBean.setBestSellerFemme(catalogue.isBestSellerFemme());
		produitBean.setBestSellerHomme(catalogue.isBestSellerHomme());
		
		produitBean.setProduitPhotoURL(catalogue.getProduitPhotoURL());
		produitBean.setProduitPhotoTailleMediumURL(catalogue.getProduitPhotoTailleMediumURL());
		produitBean.setProduitPhotoTailleLargeURL(catalogue.getProduitPhotoTailleLargeURL());

		return produitBean;
	}

	public static List<ProduitBean> convertVCatalogueRestreintAvecStock(List<VCatalogueRestreintAvecStock> catalogues) {
		List<ProduitBean> produits = new ArrayList<>();
		for (VCatalogueRestreintAvecStock catalogue : catalogues) {
			produits.add(convert(catalogue));
		}
		return produits;
	}

	public static List<ProduitBean> convertVCatalogueAvecStock(List<VCatalogueAvecStock> catalogues) {
		List<ProduitBean> produits = new ArrayList<>();
		for (VCatalogueAvecStock catalogue : catalogues) {
			produits.add(convert(catalogue));
		}
		return produits;
	}

}
