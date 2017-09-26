package com.vehicle.rest.inventory;



import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.vehicle.dao.SchemaVehicle;


@Path("/v3/inventory")
public class V3_inventory {

	/**
	 * This method will allow you to insert data the VEHICLE_REGS table.
	 * This is a example of using JSONArray and JSONObject
	 * 
	 * This is apart of the Jersey version 1 but I'm not sure if its apart 
	 * of the version 2 Jersey.
	 * 
	 * @param incomingData
	 * @return
	 * @throws Exception
	 */
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response addVehicleRegs2(String incomingData) throws Exception {
		
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		SchemaVehicle dao = new SchemaVehicle();
		
		try {
			
			/*
			 * We can create a new instance and it will accept a JSON string
			 * By doing this, we can now access the data.
			 */
			JSONObject partsData = new JSONObject(incomingData);
			System.out.println( "jsonData: " + partsData.toString() );
			// VEHICLE_REGS_REG, VEHICLE_REGS_BRAND, VEHICLE_REGS_MODEL, VEHICLE_REGS_MANU_YEAR, VEHICLE_REGS_OWNER_FIRST_NAME, VEHICLE_REGS_OWNER_LAST_NAME, VEHICLE_REGS_FIRST_REG " +
			/*
			 * In order to access the data, you will need to use one of the method in JSONArray
			 * or JSONObject.  Using the optXXXX methods instead of the get method.
			 * 
			 * Example:
			 * partsData.get("VEHICLE_REGS_REG");
			 * The example above will get you the data, the problem is, if VEHICLE_REGS_REG does
			 * not exist, it will generate a java error.  If you are using get, you need to use
			 * the has method first partsData.has("VEHICLE_REGS_REG");. 
			 * 
			 */
			int http_code = dao.insertIntoVEHICLE_REGS(partsData.optString("VEHICLE_REGS_REG"), 
														partsData.optString("VEHICLE_REGS_BRAND"), 
														partsData.optString("VEHICLE_REGS_MODEL"), 
														partsData.optString("VEHICLE_REGS_MANU_YEAR"), 
														partsData.optString("VEHICLE_REGS_OWNER_FIRST_NAME"),
														partsData.optString("VEHICLE_REGS_OWNER_LAST_NAME"),
														partsData.optString("VEHICLE_REGS_FIRST_REG") );
			
			if( http_code == 200 ) {
				/*
				 * The put method allows you to add data to a JSONObject.
				 * The first parameter is the KEY (no spaces)
				 * The second parameter is the Value
				 */
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been entered successfully, Version 3");
				/*
				 * When we are dealing with JSONArrays, the put method is used to add
				 * JSONObjects into JSONArray.
				 */
				returnString = jsonArray.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Unable to enter Item").build();
			}
			
			System.out.println( "returnString: " + returnString );
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
	

	/**
	 * This method will allow you to update data in the VEHICLE_REGS table.
	 * In this example we are using both PathParms and the message body (payload).
	 * 
	 * @param brand
	 * @param reg
	 * @param incomingData
	 * @return
	 * @throws Exception
	 */
	@Path("/{brand}/{reg}")
	@PUT
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateItem(@PathParam("brand") String brand,
									@PathParam("reg") String reg,
									String incomingData) 
								throws Exception {
		
		//System.out.println("incomingData: " + incomingData);
		//System.out.println("brand: " + brand);
		//System.out.println("reg: " + reg);
		
		
		int pk;
		String fname;
		String lname;
		int http_code;
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		SchemaVehicle dao = new SchemaVehicle();
		
		try {
			
			JSONObject partsData = new JSONObject(incomingData); //we are using json objects to parse data
			
			fname = partsData.optString("VEHICLE_REGS_OWNER_FIRST_NAME");
			lname = partsData.optString("VEHICLE_REGS_OWNER_LAST_NAME");
			pk = partsData.optInt("VEHICLE_REGS_PK", 0);
			
			
			
			//call the correct sql method
			http_code = dao.updateVEHICLE_REGS(fname, lname, pk);
			
			if(http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been updated successfully");
			} else {
				return Response.status(500).entity("Server was not able to process your request").build();
			}
			
			returnString = jsonArray.put(jsonObject).toString();
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
	
	/**
	 * This method will allow you to delete data in the VEHICLE_REGS table.
	 * 
	 * We really only need the primary key from the message body but I kept
	 * the same URL path as the update (PUT) to let you see that we can use the same
	 * URL path for each http method (GET, POST, PUT, DELETE, HEAD)
	 * 
	 * @param brand
	 * @param reg
	 * @param incomingData
	 * @return
	 * @throws Exception
	 */
	@Path("/{brand}/{reg}")
	@DELETE
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItem(@PathParam("brand") String brand,
									@PathParam("reg") String reg,
									String incomingData) 
								throws Exception {
		
		System.out.println("incomingData: " + incomingData); //showing empty string for some reason
		System.out.println("brand: " + brand);
		System.out.println("reg: " + reg);
		
		int pk;
		int http_code;
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		SchemaVehicle dao = new SchemaVehicle();
		
		try {
			
			JSONObject partsData = new JSONObject(incomingData);
			pk = partsData.optInt("VEHICLE_REGS_PK", 0);
			
			http_code = dao.deleteVEHICLE_REGS(pk);
			
			
			if(http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been deleted successfully");
			} else {
				return Response.status(500).entity("Hello Server was not able to process your request").build();
			}
			
			returnString = jsonArray.put(jsonObject).toString();
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Hi Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
}
