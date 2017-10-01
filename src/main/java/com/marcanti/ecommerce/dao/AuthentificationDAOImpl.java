/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcanti.ecommerce.dao;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marcanti.ecommerce.model.Membre;
import com.marcanti.ecommerce.view.bean.UserSessionBean;

@Repository("AuthentificationDAOImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class AuthentificationDAOImpl extends AbstractGenericDAO<Membre> implements AuthentificationDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}	

	public AuthentificationDAOImpl() {
		super(Membre.class);
	}

	public boolean emailExist(String email) {
		Query query = em.createNativeQuery("select idMembre from membre where membreEmail = ?").setParameter(1, email);
		return !query.getResultList().isEmpty();
	}

	@Override
	public boolean isAuthenticated(String email, String passwordSHA512) {
		Query query = em.createNativeQuery("select idMembre from membre where membreEmail=? and password=?").setParameter(1, email).setParameter(2, passwordSHA512);
		return !query.getResultList().isEmpty();
	}

	@Override
	public boolean[] isUserAndOrgaActif(String email) {
		boolean[] resu = new boolean[2];
		Query query = em.createNativeQuery("Select membre.isActif, orga.isActive from organisation orga, membre where membre.idOrga=orga.idOrga and membre.membreEmail=? ").setParameter(1, email);
		Object[] tab = (Object[])query.getResultList().get(0);
		resu[0] = (boolean)tab[0];
		resu[1] = (boolean)tab[1];
		return resu;
	}

	@Override
	public void updateCode(String email, int code) {
		Query query = em.createNativeQuery("update membre set codeVerificationPassword=? where membreEmail=? ").setParameter(1, code).setParameter(2, email);
		query.executeUpdate();
	}

	@Override
	public void updateGeneratedPassword(String email, String password, int isDefaultPassword) {
		Query query = em.createNativeQuery("update membre set password=?, isDefaultPassword=? where membreEmail=? ").setParameter(1, password).setParameter(2, isDefaultPassword).setParameter(3, email);
		query.executeUpdate();
		
	}

	@Override
	public boolean codeExist(String email, String code) {
		Query query = em.createNativeQuery("select idMembre from membre where membreEmail=? and codeVerificationPassword=?").setParameter(1, email).setParameter(2, code);
		return !query.getResultList().isEmpty();
	}

	@Override
	public boolean getIsDefaultPassword(String email) {
		Query query = em.createNativeQuery("select isDefaultPassword from membre where membreEmail=?").setParameter(1, email);
		return (boolean)query.getResultList().get(0);
	}

	@Override
	public UserSessionBean getUserSession(String email) {
		Query query = em.createNativeQuery("select idMembre, idProfil, m.idOrga, m.idDepartement, membreNom, membrePrenom, membreEmail, o.orgaNom, d.departementNom "
											+ "FROM membre as m "
											+ "LEFT JOIN organisation as o ON m.idOrga = o.idOrga "
											+ "LEFT JOIN departement as d ON m.idDepartement = d.idDepartement "
											+ "WHERE membreEmail=?").setParameter(1, email);
		Object[] tab = (Object[])query.getSingleResult();
		Long idDepartement;
		if((BigInteger)tab[3]!=null){
			idDepartement = ((BigInteger)tab[3]).longValue();
		}else {
			idDepartement = null;
		}
		return new UserSessionBean(((BigInteger)tab[0]).longValue(),(Short)tab[1],((BigInteger)tab[2]).longValue(),idDepartement,(String)tab[4],(String)tab[5],(String)tab[6],(String)tab[7],(String)tab[8]);
	}

	@Override
	public void updateLastConnectionDate(Date toDay, String email) {
		Query query = em.createNativeQuery("update membre set dateDerniereConnexion=? where membreEmail=? ").setParameter(1, toDay).setParameter(2, email);
		query.executeUpdate();
		
		
	}



}
