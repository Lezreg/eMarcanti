package com.test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class DBTEST {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/parfum";
	private static final String DB_USER = "parfum_user";
	private static final String DB_PASSWORD = "parfum123";

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}
	
	public static void isMembre(){
		boolean resu = false;

		PreparedStatement pst = null;
		ResultSet rs = null;

		String req = "";
		Connection mConn = null;
		try {

			mConn = getDBConnection();
			req = "select * from membre where membreEmail = ?";
			pst = mConn.prepareStatement(req);
			System.out.println("req : " + req);
			pst.setString(1, "cosmetick@gmail.com");
			rs = pst.executeQuery();
			resu = rs.next();
			System.out.println(rs.getInt(1));
			System.out.println(rs.getInt(2));
			System.out.println(rs.getInt(3));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (mConn != null)
						try {
							mConn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
		}
	}
	
	
	public static void isMembre2(){
		boolean resu = false;

		Statement pst = null;
		ResultSet rs = null;

		String req = "";
		Connection mConn = null;
		try {

			mConn = getDBConnection();
			req = "select * from membre where membreEmail = 'cosmetick@gmail.com'";
			pst = mConn.prepareStatement(req);
			System.out.println("req : " + req);
			rs = pst.executeQuery(req);
			resu = rs.next();
			System.out.println(rs.getInt(1));
			System.out.println(rs.getInt(2));
			System.out.println(rs.getInt(3));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (mConn != null)
						try {
							mConn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
		}
	}
	

	public static void main(String[] args) {

		//DBTEST.isMembre();
		
		 for(int i=0; i<20; ++i)    
	      {           
	         System.out.println("" + (new Random().nextInt(9999-1000)+1000));
	      }

	}

}
