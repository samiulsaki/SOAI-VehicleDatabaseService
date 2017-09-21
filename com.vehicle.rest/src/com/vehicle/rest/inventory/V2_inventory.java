package com.vehicle.rest.inventory;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import com.vehicle.dao.SchemaVehicle;

@Path("/v2/inventory")
public class V2_inventory {
	
	/**
	 * This method will return the specific brand of PC parts the user is looking for.  
	 * It uses a QueryParam bring in the data to the method.
	 * 
	 * Example would be:
	 * http://localhost:7001/com.vehicle.rest/api/v2/inventory?brand=Audi
	 * 
	 * @param brand - product brand name
	 * @return - json array results list from the database
	 * @throws Exception
	 */
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnVehicleRegs(
				@QueryParam("reg") String reg)
				throws Exception {
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			
			if(reg == null) {
				return Response.status(400).entity("Error: please specify registration for this search").build();
				
			}
			
			SchemaVehicle dao = new SchemaVehicle();
			
			json = dao.queryReturnVehicleRegs(reg);
			returnString = json.toString();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
	/*
	 * This method can be used if the method returnVehicleRegs is not used.
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnErrorOnBrand() throws Exception {
		
		return Response.status(400).entity("Error: please specify brand for this search").build();
	}
	*/
	
	/**
	 * This method will return the specific brand of PC parts the user is looking for.
	 * It is very similar to the method returnBrandParts except this method uses the 
	 * PathParam to bring in the data.
	 * 
	 * Example would be:
	 * http://localhost:7001/com.vehicle.rest/api/v2/inventory/<registration>
	 * 
	 * @param reg - vehicle registration number
	 * @return - json array results list from the database
	 * @throws Exception
	 */
	@Path("/{reg}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnRegs(
				@PathParam("reg") String reg) 
				throws Exception {
		
		String returnString = null;
		
		JSONArray json = new JSONArray();
		
		try {
			
			SchemaVehicle dao = new SchemaVehicle();
			
			json = dao.queryReturnVehicleRegs(reg);
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	

	/**
	 * This method does a search on both product and the product item number.
	 * It uses PathParam to bring in both parameters.
	 * 
	 * Example:
	 * http://localhost:7001/com.vehicle.rest/api/v2/inventory/<brand>/<model>/<year>
	 * 
	 * @param brand - vehicle brand
	 * @param model - vehicle model
	 * @param year 	- vehicle manufacturing year
	 * @return - json array results list from the database
	 * @throws Exception
	 */
	@Path("/{brand}/{model}/{year}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnSpecificBrandModelYear (
				@PathParam("brand") String brand,
				@PathParam("model") String model,
				@PathParam("year") int year) 
				throws Exception {
		
		String returnString = null;
		
		JSONArray json = new JSONArray();
		
		try {
			
			SchemaVehicle dao = new SchemaVehicle();
			
			json = dao.queryReturnBrandModelYear(brand, model, year);
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	

	/**
	 * This method does a search on both product and the product item number.
	 * It uses PathParam to bring in both parameters.
	 * 
	 * Example:
	 * http://localhost:7001/com.vehicle.rest/api/v2/inventory/<brand>/<reg>
	 * 
	 * @param brand - vehicle brand
	 * @param reg 	- vehicle registration number
	 * @return - json array results list from the database
	 * @throws Exception
	 */
	@Path("/{brand}/{reg}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnSpecificBrandModelYear (
				@PathParam("brand") String brand,
				@PathParam("reg") String reg) 
				throws Exception {
		
		String returnString = null;
		
		JSONArray json = new JSONArray();
		
		try {
			
			SchemaVehicle dao = new SchemaVehicle();
			
			json = dao.queryReturnBrandReg(brand, reg);
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
	/**
	 * This method will allow you to insert data the VEHICLE_REGS table.  
	 * This is a example of using the Jackson Processor
	 * 
	 * Note: If you look, this method addVehicleRegs using the same URL as a GET method returnVehicleRegs.
	 * 			We can do this because we are using different HTTP methods for the same URL string.
	 * 
	 * @param incomingData - must be in JSON format
	 * @return String
	 * @throws Exception
	 */
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON}) // for both web form and application.JSON 
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 							  // for only web forms
	@Produces(MediaType.APPLICATION_JSON)
	public Response addVehicleRegs(String incomingData) throws Exception {
		
		String returnString = null;
		//JSONArray jsonArray = new JSONArray(); //not needed
		SchemaVehicle dao = new SchemaVehicle();
		
		try {
			System.out.println("incomingData: " + incomingData);
			
			/*
			 * Using the readValue method, you can parse the json from the http request
			 * and data bind it to a Java Class.
			 */
			ObjectMapper mapper = new ObjectMapper();  
			ItemEntry itemEntry = mapper.readValue(incomingData, ItemEntry.class);
			
			int http_code = dao.insertIntoVEHICLE_REGS(itemEntry.VEHICLE_REGS_REG, 
													itemEntry.VEHICLE_REGS_BRAND, 
													itemEntry.VEHICLE_REGS_MODEL, 
													itemEntry.VEHICLE_REGS_MANU_YEAR, 
													itemEntry.VEHICLE_REGS_OWNER_FIRST_NAME,
													itemEntry.VEHICLE_REGS_OWNER_LAST_NAME,
													itemEntry.VEHICLE_REGS_FIRST_REG );
			
			if( http_code == 200 ) {
				//returnString = jsonArray.toString();
				returnString = "Item inserted";
			} else {
				return Response.status(500).entity("Unable to process Item").build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
}


/*
 * This is a class used by the addVehicleRegs method.
 * Used by the Jackson Processor
 * 
 * Note: for re-usability you should place this in its own package.
 */

class ItemEntry {
	public String VEHICLE_REGS_REG;
	public String VEHICLE_REGS_BRAND;
	public String VEHICLE_REGS_MODEL;
	public String VEHICLE_REGS_MANU_YEAR;
	public String VEHICLE_REGS_OWNER_FIRST_NAME;
	public String VEHICLE_REGS_OWNER_LAST_NAME;
	public String VEHICLE_REGS_FIRST_REG;

}


