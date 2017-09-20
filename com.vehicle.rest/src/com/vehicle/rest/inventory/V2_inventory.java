package com.vehicle.rest.inventory;


import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

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
	public Response returnVehicleBrands(
				@QueryParam("brand") String brand)
				throws Exception {
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
}
