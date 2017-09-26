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
			 * If this was a real application, we should do data validation here
			 * before deleting data.
			 */
			System.out.println("pk: " + pk);
			conn = oracleVehicleRegsConnection();
			query = conn.prepareStatement("delete from VEHICLE_REGS where VEHICLE_REGS_PK = ? ");
			
			query.setInt(1, pk);
			query.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			return 500;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return 200;
	}
	
	
	/**
	 * This method allows you to update VEHICLE_REGS table
	 * 
	 * @param pk
	 * @param fname
	 * @param lname
	 * @return
	 * @throws Exception
	 */
	public int updateVEHICLE_REGS(String fname, String lname, int pk) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		try {

			//System.out.println("fname: " + fname);
			//System.out.println("lname: " + lname);
			//System.out.println("pk: " + pk);
			conn = oracleVehicleRegsConnection();
			query = conn.prepareStatement("update VEHICLE_REGS set VEHICLE_REGS_OWNER_FIRST_NAME = ?, VEHICLE_REGS_OWNER_LAST_NAME = ? where VEHICLE_REGS_PK = ? ");
			
			query.setString(1, fname); //first ?
			query.setString(2, lname); //second ?
			query.setInt(3, pk); //third ?			
			query.executeUpdate();
			
			
		} catch(Exception e) {
			e.printStackTrace();
			return 500;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return 200;
	}
	
	/**
	 * This method will insert a record into the VEHICLE_REGS table. 
	 * 
	 * @param VEHICLE_REGS_REG
	 * @param VEHICLE_REGS_BRAND
	 * @param VEHICLE_REGS_MODEL
	 * @param VEHICLE_REGS_MANU_YEAR - integer column
	 * @param VEHICLE_REGS_OWNER_FIRST_NAME
	 * @param VEHICLE_REGS_OWNER_LAST_NAME
	 * @param VEHICLE_REGS_FIRST_REG - date column
	 * @return integer 200 for success, 500 for error
	 * @throws Exception
	 */
	public int insertIntoVEHICLE_REGS(String VEHICLE_REGS_REG, 
											String VEHICLE_REGS_BRAND, 
											String VEHICLE_REGS_MODEL, 
											String VEHICLE_REGS_MANU_YEAR, 
											String VEHICLE_REGS_OWNER_FIRST_NAME,
											String VEHICLE_REGS_OWNER_LAST_NAME,
											String VEHICLE_REGS_FIRST_REG) 
										throws Exception {

		PreparedStatement query = null;
		Connection conn = null;

		try {
			/*
			 * 
			 * Important: The primary key on VEHICLE_REGS table will auto increment.
			 * 		That means the VEHICLE_REGS_REG column does not need to be apart of the 
			 * 		SQL insert query below.
			 */
			conn = oracleVehicleRegsConnection();
			query = conn.prepareStatement("insert into VEHICLE_REGS " +
					"(VEHICLE_REGS_REG, VEHICLE_REGS_BRAND, VEHICLE_REGS_MODEL, VEHICLE_REGS_MANU_YEAR, VEHICLE_REGS_OWNER_FIRST_NAME, VEHICLE_REGS_OWNER_LAST_NAME, VEHICLE_REGS_FIRST_REG) " +
					"VALUES ( ?, ?, ?, ?, ? , ? , ?) ");

			query.setString(1, VEHICLE_REGS_REG);
			query.setString(2, VEHICLE_REGS_BRAND);
			query.setString(3, VEHICLE_REGS_MODEL);

			//VEHICLE_REGS_MANU_YEAR is a number column, so we need to convert the String into a integer
			int avilInt = Integer.parseInt(VEHICLE_REGS_MANU_YEAR);
			query.setInt(4, avilInt);

			query.setString(5, VEHICLE_REGS_OWNER_FIRST_NAME);
			query.setString(6,  VEHICLE_REGS_OWNER_LAST_NAME);
			
			//String[] splitArray = VEHICLE_REGS_FIRST_REG.split("-");
			//int day = Integer.parseInt(splitArray[0]);
			//int month = Integer.parseInt(splitArray[1]);
			//int year = Integer.parseInt(splitArray[2]);			

			query.setString(7,  VEHICLE_REGS_FIRST_REG);
			query.executeUpdate(); //note the new command for insert statement

		} catch(Exception e) {
			e.printStackTrace();
			return 500; //if an error occurs, return a 500
		}
		finally {
			if (conn != null) conn.close();
		}

		return 200;
	}
	

	/**
	 * This method will search for a specific registration from the VEHICLE_REGS table.
	 * By using prepareStatement and the ?, we are protecting against sql injection
	 * We never add parameter straight into the prepareStatement
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
	
	
	/**
	 * This method will search for the specific brand and the brands model and year in
	 * the VEHICLE_REGS table.
	 * By using prepareStatement and the ?, we are protecting against sql injection
	 * 
	 * @param brand - vehicle brand
	 * @param model - vehicle model
	 * @param year  - vehicle manufacturing year
	 * @return - json array of the results from the database
	 * @throws Exception
	 */
	public JSONArray queryReturnBrandModelYear(String brand, String model, int year) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = oracleVehicleRegsConnection();
			query = conn.prepareStatement("select VEHICLE_REGS_REG, VEHICLE_REGS_BRAND, VEHICLE_REGS_MODEL, VEHICLE_REGS_MANU_YEAR, VEHICLE_REGS_OWNER_FIRST_NAME, VEHICLE_REGS_OWNER_LAST_NAME, VEHICLE_REGS_FIRST_REG " +
											"from VEHICLE_REGS " +
											"where UPPER(VEHICLE_REGS_BRAND) = ? " +
											"and UPPER(VEHICLE_REGS_MODEL) = ?"	+
											"and VEHICLE_REGS_MANU_YEAR	= ?");
			
			/*
			 * protect against sql injection
			 * when you have more than one ?, it will go in chronological
			 * order.
			 */
			query.setString(1, brand.toUpperCase()); //first ?
			query.setString(2, model.toUpperCase()); //second ?
			query.setInt(3,	year); //third ?
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
	
	
	/**
	 * This method will search for the specific brand and the manufacturing year in
	 * the VEHICLE_REGS table.
	 * 
	 * 
	 * @param brand - vehicle brand
	 * @param year 	- vehicle registration number
	 * @return - json array of the results from the database
	 * @throws Exception
	 */
	public JSONArray queryReturnBrandReg(String brand, String reg) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = oracleVehicleRegsConnection();
			query = conn.prepareStatement("select VEHICLE_REGS_REG, VEHICLE_REGS_BRAND, VEHICLE_REGS_MODEL, VEHICLE_REGS_MANU_YEAR, VEHICLE_REGS_OWNER_FIRST_NAME, VEHICLE_REGS_OWNER_LAST_NAME, VEHICLE_REGS_FIRST_REG " +
											"from VEHICLE_REGS " +
											"where UPPER(VEHICLE_REGS_BRAND) = ? " +
											"and UPPER(VEHICLE_REGS_REG) = ?"	);
			

			query.setString(1, brand.toUpperCase()); //first ?
			query.setString(2, reg.toUpperCase()); //second ?
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
	

	/**
	 * This method will return all Vehicle items.
	 * 
	 * @return - all Vehicle items in json format
	 * @throws Exception
	 */
	public JSONArray queryAllVehicleRegs() throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = oracleVehicleRegsConnection();
			query = conn.prepareStatement("select VEHICLE_REGS_REG, VEHICLE_REGS_BRAND, VEHICLE_REGS_MODEL, VEHICLE_REGS_MANU_YEAR, VEHICLE_REGS_OWNER_FIRST_NAME, VEHICLE_REGS_OWNER_LAST_NAME, VEHICLE_REGS_FIRST_REG " +
											"from VEHICLE_REGS");
			
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
	
	/**
	 * This method will return a time/stamp from database.
	 * 
	 * @return time/stamp in json format
	 * @throws Exception
	 */
	public JSONArray queryCheckDbConnection() throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = oracleVehicleRegsConnection();
			query = conn.prepareStatement("select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') DATETIME " +
											"from sys.dual");
			
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
