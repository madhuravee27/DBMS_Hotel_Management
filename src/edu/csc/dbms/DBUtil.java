package edu.csc.dbms;

import java.sql.*;

public class DBUtil {

	//URLs
    final static String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    final static String DB_URL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/araja2";

    //  Database credentials
    final static String USER = "araja2";
    final static String PASS = "200203475";
	
    //Method to execute query on Maria DB
    public static ResultSet executeQuery(String query) {
     
        Connection conn;
        Statement stmt;
        ResultSet result = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();

            result = stmt.executeQuery(query);

        } catch (SQLException se) {
            System.out.println("SQL Exception " + se.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println("Exception " + e.getLocalizedMessage());
        }

        return result;

    }

    //Method to get connection without autocommit
    //Used in transactions
    public static Connection getConnection() {
    	
    	Connection conn;
    	
    	try {
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);
            
            return conn;
            
        } catch (SQLException se) {
            System.out.println("SQL Exception " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    	return null;
    }
}
