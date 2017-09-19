package com.vehicle.dao;

import javax.naming.*;
import javax.sql.*;

public class OracleVehicle {

	private static DataSource OracleVehicle = null; // Private variable 1
	private static Context context = null; 			// Private variable 2
	
	// Public method to call other methods
	public static DataSource OracleVehicleConn() throws Exception {
		
		if (OracleVehicle != null) { // To avoid running the try handling again when OracleVehicle is not null
			return OracleVehicle;
		}
		
		// Error Handling
		try {
			if (context == null) {
				context = new InitialContext();
			}
			
			OracleVehicle = (DataSource) context.lookup("vehicleOracle"); // lookup jndID
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return OracleVehicle; // Method return database object
	}
}
