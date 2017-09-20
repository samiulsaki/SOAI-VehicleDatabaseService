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
	 * This method allows you to delete a row from VEHICLE_REGS table
	 * 
	 * If you need to do a delete, consider moving the data to a archive table, then
	 * delete. Or just make the data invisible to the user.  Delete data can be
	 * very dangerous.
	 * 
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	
	public int deleteVEHICLE_REGS(int pk) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			
			/*
			 * If this was a real application, you should do data validation here
			 * before deleting data.
			 */
			conn = oracleVehicleRegsConnection();
			query = conn.prepareStatement("delete from VEHICLE_REGS " +
											"where VEHICLE_REGS_REG = ? ");
			
			query.setInt(1, pk);
			query.executeUpdate();
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return 500;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return 200;
		
	}
	
}
