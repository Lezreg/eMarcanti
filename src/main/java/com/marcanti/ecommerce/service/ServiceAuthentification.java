package com.marcanti.ecommerce.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.utils.DataManager;
import com.marcanti.ecommerce.utils.ParfumUtils;

public class ServiceAuthentification {

	private final static Logger logger = LoggerFactory.getLogger(ServiceAuthentification.class);

	public ServiceAuthentification() {
	}
	
	public static boolean emailExist(String email) throws Exception {
		
		DataManager dtm = DataManager.getInstance();
		boolean resu = false;

		if (dtm != null) {
			PreparedStatement pst = null;
			ResultSet rs = null;

			String req = "";
			Connection mConn = null;
			try {

				mConn = dtm.openConnection();
				req = "select idMembre from membre where membreEmail = ?";
				pst = mConn.prepareStatement(req);
				pst.setString(1, email);
				logger.info("Recuperation email : " + req);
				rs = pst.executeQuery();
				resu = rs.next();

			} catch (Exception e) {
				logger.error("Erreur emailExist", e);
				throw new Exception(e.getMessage());
			} finally {
				if (pst != null)
					try {
						pst.close();
					} catch (SQLException e) {
						logger.error("Erreur emailExist", e);
						throw new Exception(e.getMessage());
					} finally {
						if (mConn != null)
							dtm.closeConnection(mConn);
					}
			}
			return resu;

		} else {
			logger.error("Erreur d'acces à la base de donnée");
			throw new Exception("Erreur d'acces à la base de donnée");
		}
	}	

	public static boolean isAuthenticated(String email, String password) throws Exception {
		
		DataManager dtm = DataManager.getInstance();
		boolean resu = false;

		if (dtm != null) {
			PreparedStatement pst = null;
			ResultSet rs = null;

			String req = "";
			Connection mConn = null;
			try {

				mConn = dtm.openConnection();
				String passwordSHA512 = DigestUtils.sha512Hex(password);
				req = "select idMembre from membre where membreEmail=? and password=?  ";
				pst = mConn.prepareStatement(req);
				pst.setString(1, email);
				pst.setString(2, passwordSHA512);
				
				logger.info("Recuperation idMembre : " + req);
				rs = pst.executeQuery();
				resu = rs.next();

			} catch (Exception e) {
				logger.error("Erreur isAuthenticated", e);
				throw new Exception(e.getMessage());
			} finally {
				if (pst != null)
					try {
						pst.close();
					} catch (SQLException e) {
						logger.error("Erreur isAuthenticated", e);
						throw new Exception(e.getMessage());
					} finally {
						if (mConn != null)
							dtm.closeConnection(mConn);
					}
			}
			return resu;

		} else {
			logger.error("Erreur d'acces à la base de donnée");
			throw new Exception("Erreur d'acces à la base de donnée");
		}
	}

	public static boolean[] isUserAndOrgaActif(String email) throws Exception {
		
		DataManager dtm = DataManager.getInstance();
		boolean[] resu = new boolean[2];

		if (dtm != null) {
			PreparedStatement pst = null;
			ResultSet rs = null;

			String req = "";
			Connection mConn = null;
			try {

				mConn = dtm.openConnection();
				req = "Select membre.isActif, orga.isActive from organisation orga, membre where membre.idOrga=orga.idOrga and membre.membreEmail=? ";
				pst = mConn.prepareStatement(req);
				pst.setString(1, email);
				
				logger.info("Recuperation isActif : " + req);
				rs = pst.executeQuery();
				rs.next();
				resu[0]=rs.getBoolean(1);
				resu[1]=rs.getBoolean(2);

			} catch (Exception e) {
				logger.error("Erreur isUserAndOrgaActif", e);
				throw new Exception(e.getMessage());
			} finally {
				if (pst != null)
					try {
						pst.close();
					} catch (SQLException e) {
						logger.error("Erreur isUserAndOrgaActif", e);
						throw new Exception(e.getMessage());
					} finally {
						if (mConn != null)
							dtm.closeConnection(mConn);
					}
			}
			return resu;

		} else {
			logger.error("Erreur d'acces à la base de donnée");
			throw new Exception("Erreur d'acces à la base de donnée");
		}
	}
	
	public static void updateCode(String email, int code) throws Exception {
		
		DataManager dtm = DataManager.getInstance();

		if (dtm != null) {
			PreparedStatement pst = null;

			String req = "";
			Connection mConn = null;
			try {

				mConn = dtm.openConnection();
				req = "update membre set codeVerificationPassword=? where membreEmail=? ";
				pst = mConn.prepareStatement(req);
				pst.setInt(1, code);
				pst.setString(2, email);
				
				logger.info("Update code for : " + email);
				pst.executeUpdate();

			} catch (Exception e) {
				logger.error("Erreur updateCode", e);
				throw new Exception(e.getMessage());
			} finally {
				if (pst != null)
					try {
						pst.close();
					} catch (SQLException e) {
						logger.error("Erreur updateCode", e);
						throw new Exception(e.getMessage());
					} finally {
						if (mConn != null)
							dtm.closeConnection(mConn);
					}
			}

		} else {
			logger.error("Erreur d'acces à la base de donnée");
			throw new Exception("Erreur d'acces à la base de donnée");
		}
	}	
	
	public static void updateGeneratedPassword(String email, String password, int isDefaultPassword) throws Exception {
		
		DataManager dtm = DataManager.getInstance();

		if (dtm != null) {
			PreparedStatement pst = null;

			String req = "";
			Connection mConn = null;
			try {

				mConn = dtm.openConnection();
				req = "update membre set password=?, isDefaultPassword=? where membreEmail=? ";
				pst = mConn.prepareStatement(req);
				String passwordSHA512 = DigestUtils.sha512Hex(password);
				pst.setString(1, passwordSHA512);
				pst.setInt(2, isDefaultPassword);
				pst.setString(3, email);
				
				logger.info("Update codeVerificationPassword for : " + email);
				pst.executeUpdate();

			} catch (Exception e) {
				logger.error("Erreur updateGeneratedPassword", e);
				throw new Exception(e.getMessage());
			} finally {
				if (pst != null)
					try {
						pst.close();
					} catch (SQLException e) {
						logger.error("Erreur updateGeneratedPassword", e);
						throw new Exception(e.getMessage());
					} finally {
						if (mConn != null)
							dtm.closeConnection(mConn);
					}
			}

		} else {
			logger.error("Erreur d'acces à la base de donnée");
			throw new Exception("Erreur d'acces à la base de donnée");
		}
	}
	
	public static boolean codeExist(String email, String code) throws Exception {
		
		DataManager dtm = DataManager.getInstance();
		boolean resu = false;

		if (dtm != null) {
			PreparedStatement pst = null;
			ResultSet rs = null;

			String req = "";
			Connection mConn = null;
			try {

				mConn = dtm.openConnection();
				req = "select idMembre from membre where membreEmail=? and codeVerificationPassword=?";
				pst = mConn.prepareStatement(req);
				pst.setString(1, email);
				pst.setInt(2, Integer.valueOf(code));
				logger.info("Verification  code : " + req);
				rs = pst.executeQuery();
				resu = rs.next();

			} catch (Exception e) {
				logger.error("Erreur codeExist", e);
				throw new Exception(e.getMessage());
			} finally {
				if (pst != null)
					try {
						pst.close();
					} catch (SQLException e) {
						logger.error("Erreur codeExist", e);
						throw new Exception(e.getMessage());
					} finally {
						if (mConn != null)
							dtm.closeConnection(mConn);
					}
			}
			return resu;

		} else {
			logger.error("Erreur d'acces à la base de donnée");
			throw new Exception("Erreur d'acces à la base de donnée");
		}
	}

	public static boolean getIsDefaultPassword(String email) throws Exception {
		
		DataManager dtm = DataManager.getInstance();
		boolean resu = false;

		if (dtm != null) {
			PreparedStatement pst = null;
			ResultSet rs = null;

			String req = "";
			Connection mConn = null;
			try {

				mConn = dtm.openConnection();
				req = "select isDefaultPassword from membre where membreEmail=?";
				pst = mConn.prepareStatement(req);
				pst.setString(1, email);
				logger.info("Verification  code : " + req);
				rs = pst.executeQuery();
				rs.next();
				resu = rs.getBoolean(1);

			} catch (Exception e) {
				logger.error("Erreur isDefaultPassword", e);
				throw new Exception(e.getMessage());
			} finally {
				if (pst != null)
					try {
						pst.close();
					} catch (SQLException e) {
						logger.error("Erreur isDefaultPassword", e);
						throw new Exception(e.getMessage());
					} finally {
						if (mConn != null)
							dtm.closeConnection(mConn);
					}
			}
			return resu;

		} else {
			logger.error("Erreur d'acces à la base de donnée");
			throw new Exception("Erreur d'acces à la base de donnée");
		}
	}
	
	
	
	

}
