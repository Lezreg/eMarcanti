package com.marcanti.ecommerce.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcanti.ecommerce.service.ServiceAuthentification;

public class DataManager {

	private static DataSource ds = null;
	private static DataManager instance = null;

	private final static Logger logger = LoggerFactory.getLogger(ServiceAuthentification.class);
	private static final String JNDI_DS_NAME = "java:/ParfumDS";

	private DataManager() throws Exception {

		if (ds == null) {
			try {
				ds = (DataSource) new InitialContext().lookup(DataManager.JNDI_DS_NAME);
			} catch (Exception e) {
				throw new Exception("Erreur lookup dataSource : " + e.getMessage());
			}
		}
	}

	public static DataManager getInstance() throws Exception {

		if (instance == null) {

			instance = new DataManager();
		}
		return instance;
	}

	public Connection openConnection() throws Exception {

		try {
			logger.info("Ouverture connexion DB");
			return ds.getConnection();
		} catch (Exception e) {
			throw new Exception("Erreur d'obtention de la connection : " + e.getMessage());
		}
	}

	public void closeConnection(Connection conn) throws Exception {

		try {
			if (conn != null) {
				conn.close();
				conn = null;
				logger.info("Fermeture connexion DB");
			}
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

}
