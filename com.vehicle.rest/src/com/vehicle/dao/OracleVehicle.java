package com.vehicle.dao;

import java.sql.Connection;
import javax.naming.*;
import javax.sql.*;

public class OracleVehicle {

	private static DataSource OracleVehicle = null; // Private variable 1
	private static Context context = null; 			// Private variable 2
	
	// Public method to call other methods
	public static DataSource OracleVehicleConn() throws Exception {
		// To avoid running the try handling again when OracleVehicle is not null
		/**
		 * check to see if the database object is already defined...
		 * if it is, then return the connection, no need to look it up again.
		 */
		
		if (OracleVehicle != null) { 
			return OracleVehicle;
		}
		
		// Error Handling
		try {
			/**
			 * This only needs to run one time to get the database object.
			 * context is used to lookup the database object in weblogic
			 * OracleVehicle will hold the database object
			 */
			
			if (context == null) {
				context = new InitialContext();
			}
			
			OracleVehicle = (DataSource) context.lookup("vehicleOracle"); // lookup JNDI name
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return OracleVehicle; // Method return database object
	}
	
	/**
	 * This method will return the connection to the Oracle Vehicle schema
	 * Note that the scope is protected which means only java class in the
	 * dao package can use this method.
	 * 
	 * @return Connection to Vehicle Oracle database.
	 */
	
	// Protected allows the method to be used by subclasses or any package member
	protected static Connection oracleVehicleRegsConnection() {
		Connection conn = null;
		try {
			conn = OracleVehicleConn().getConnection();
			return conn;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
