package com.vehicle.dao;

import java.sql.*;

import org.codehaus.jettison.json.JSONArray;

import com.vehicle.util.ToJSON;


/**
 * This java class will hold all the sql queries.
 * V1_inventory.java and V1_status.java will not use this class for its sql code
 * since they were created before episode 5.
 * 
 * Having all sql/database code in one package makes it easier to maintain and audit
 * but increase complexity.
 * 
 * Note: we also used the extends OracleVehicle on this java class to inherit all
 * the methods in OracleVehicle.java
 * 
 * @author samiulsaki
 */

public class SchemaVehicle extends OracleVehicle {

	
	
	
	/**
	 * This method will search for a specific brand from the VEHICLE_REG table.
	 * By using prepareStatement and the ?, we are protecting against sql injection
	 * 
	 * Never add parameter straight into the prepareStatement
	 * 
	 * @param reg - vehicle registration
	 * @return - json array of the results from the database
	 * @throws Exception
	 */
	public JSONArray queryReturnVehicleRegs(String reg) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = oracleVehicleRegsConnection();
			query = conn.prepareStatement("select VEHICLE_REGS_REG, VEHICLE_REGS_BRAND, VEHICLE_REGS_MODEL, VEHICLE_REGS_MANU_YEAR, VEHICLE_REGS_OWNER_FIRST_NAME, VEHICLE_REGS_OWNER_LAST_NAME, VEHICLE_REGS_FIRST_REG " +
											"from VEHICLE_REGS " +
											"where UPPER(VEHICLE_REGS_REG) = ? ");
			
			query.setString(1, reg.toUpperCase()); //protect against sql injection
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	}
	
	
	
}
